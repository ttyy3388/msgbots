package org.beuwi.msgbots.view.gui.editor;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import org.beuwi.msgbots.actions.CopyClipboardAction;
import org.beuwi.msgbots.keyboard.KeyBinding;
import org.beuwi.msgbots.view.gui.layout.WebPane;
import org.beuwi.msgbots.utils.ResourceUtils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class Monaco extends WebPane {
	private static final String RESOURCE_LOCATION = ResourceUtils.getURL("/monaco/index.html");

	private final BooleanProperty editableProperty = new SimpleBooleanProperty(true);
	private final StringProperty languageProperty = new SimpleStringProperty("javascript");
	private final StringProperty themeProperty = new SimpleStringProperty("dark");
	private final StringProperty textProperty = new SimpleStringProperty(null);
	private final ObjectProperty<Position> cursorPositionProperty = new SimpleObjectProperty(null);
	private final DoubleProperty scrollLeftProperty = new SimpleDoubleProperty(0);
	private final DoubleProperty scrollTopProperty = new SimpleDoubleProperty(0);
	private final BooleanProperty lineNumberEnabledProperty = new SimpleBooleanProperty(true);

	private JSObject window = null; // Monaco Window
	private JSObject editor = null; // Monaco Editor

	private final WebView browser;
	private final WebEngine engine;
	private final Worker worker;

	// 전역 변수로 해야 사라지지 않고 남아있음
	private final JFunction scrollChangeListener = new JFunction(args -> {
		scrollTopProperty.set(getScrollTop());
		scrollLeftProperty.set(getScrollLeft());
		return null;
	});
	private final JFunction cursorChangeListener = new JFunction(args -> {
		cursorPositionProperty.set(getCursorPosition());
		return null;
	});
	private final JFunction contentChangeListener = new JFunction(args -> {
		String content = (String) editor.call("getValue");
		if (content != null) {
			textProperty.set(content);
		}
		return null;
	});

	public Monaco() {
		browser = getWebView();
		engine = getWebEngine();
		worker = getLoadWorker();

		engine.load(RESOURCE_LOCATION);
		engine.onAlertProperty().set(event -> {
			System.out.println(event.getData());
		});
		engine.setJavaScriptEnabled(true);
		// engine.setUserStyleSheetLocation(null);
		browser.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			switch (KeyBinding.matching(event)) {
				case REDO -> redo();
				case UNDO -> undo();
				case CUT -> cut();
				case COPY -> copy();
				case SELECT_ALL -> selectAll();
			}
		});

		worker.stateProperty().addListener(event -> {
			State state = worker.getState();
			if (state.equals(State.SUCCEEDED)) {
				window = (JSObject) engine.executeScript("window");

				AtomicBoolean jsdone = new AtomicBoolean(false);
				AtomicInteger attempts = new AtomicInteger(0);

				Thread thread = new Thread(() -> {
					while (!jsdone.get()) {
						try {
							Thread.sleep(500);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}

						// check if JS execution is done.
						Platform.runLater(() -> {
							Object object = window.call("getEditor");
							if (object instanceof JSObject) {
								jsdone.set(true);

								editor = (JSObject) object;
								// Initialize Text
								if (getText() != null) {
									editor.call("setValue", getText());
								}

								// window.setMember("autoCompleteContent", autoCompleteContent);
								window.setMember("cursorChangeListener", cursorChangeListener);
								window.setMember("scrollChangeListener", scrollChangeListener);
								window.setMember("contentChangeListener", contentChangeListener);

								// 초반에 값을 설정해 놓을 경우가 있는 것들을 적어둠
								// 프로펄티는 값이 변경돼야 호출되는데 초기값들은 값 변경으로 인식이 안되기 때문에
								/* execute("monaco.editor.setTheme('" + getTheme() + "')");
								execute("monaco.editor.setModelLanguage(editor.getModel(),'" + getLanguage() + "')");
								// 자동완성 기능을 위해서 넣음
								// execute("monaco.languages.typescript.javascriptDefaults.addExtraLib(" + autoCompleteContent + ")");
								// 어떤건 monaco.editor고 어떤건 editor로 접근해야 함 (updateOptions)
								execute("editor.updateOptions({readOnly:" + !isEditable() + "})");
								execute("editor.updateOptions({lineNumbers:'" + (isLineNumberEnabled() ? "on" : "off") + "'})");
								execute("editor.updateOptions({folding:" + isLineNumberEnabled() + "})"); */

								// 외부에서 파일을 변경하거나 텍스트 값을 변경했을 때 발생
								/* textProperty().addListener(change -> {
									// setValue()를 하면 초기화처럼 redo, undo 스택과 같은 메모리가 사라지기 때문에 다른 방법 사용
									window.call("setText", textProperty().get());
								});
								languageProperty().addListener(change -> {
									execute("monaco.editor.setModelLanguage(editor.getModel(),'" + getLanguage() + "')");
								});
								themeProperty().addListener(change -> {
									execute("monaco.editor.setTheme('" + getTheme() + "')");
								});
								editableProperty().addListener(change -> {
									execute("editor.updateOptions({readOnly:" + isEditable() + "})");
								});
								scrollTopProperty().addListener(change -> {
									execute("editor.setScrollPosition({scrollTop:" + getScrollTop() + "})");
								});
								scrollLeftProperty().addListener(change -> {
									execute("editor.setScrollPosition({scrollLeft:" + getScrollLeft() + "})");
								});
								lineNumberEnabledProperty().addListener(change -> {
									execute("editor.updateOptions({lineNumbers:'" + (isLineNumberEnabled() ? "on" : "off") + "'})");
									execute("editor.updateOptions({folding:" + isLineNumberEnabled() + "})");
								}); */
							}
						});

						if (attempts.getAndIncrement() > 10) {
							throw new RuntimeException("Cannot initialize editor (JS execution not complete). Max number of attempts reached.");
						}
					}
				});

				thread.start();
			}
		});

	}

	public void copy() {
		CopyClipboardAction.getInstance().execute(getSelectedText());
	}
	public void cut() {
		// Cut action 만 실행하면 클립보드 복사가 안되기에 copy도 실행 즉, copy and delete
		trigger("cut"); copy();
	}
	public void redo() {
		trigger("redo");
	}
	public void undo() {
		trigger("undo");
	}
	public void paste() {
		trigger("paste");
	}
	public void selectAll() {
		execute("editor.setSelection(editor.getModel().getFullModelRange())");
	}

	protected Object trigger(String action) {
		return execute("editor.trigger('', '" + action + "')");
	}
	protected Object execute(String action) {
		/* if (engine == null) {
			throw new NullPointerException("not initialized engine");
		} */

		try {
			engine.executeScript("editor");
		}
		catch (JSException e) {
			// 해당 에러는 무시해도 상관 X
			throw new NullPointerException("not initialized editor");
		}

		return engine.executeScript(action);
				// engine.executeScript("editor.trigger('', '" + action + "');");
		/*
			alert(editor.getActions().map(a => a.id).join('\n'));

			editor.action.toggleHighContrast
			editor.action.setSelectionAnchor
			editor.action.goToSelectionAnchor
			editor.action.selectFromAnchorToCursor
			editor.action.cancelSelectionAnchor
			editor.action.moveCarretLeftAction
			editor.action.moveCarretRightAction
			editor.action.transposeLetters
			editor.action.clipboardCopyWithSyntaxHighlightingAction
			editor.action.commentLine
			editor.action.addCommentLine
			editor.action.removeCommentLine
			editor.action.blockComment
			editor.action.showContextMenu
			cursorUndo
			cursorRedo
			editor.action.fontZoomIn
			editor.action.fontZoomOut
			editor.action.fontZoomReset
			editor.action.formatDocument
			editor.action.formatSelection
			editor.action.indentationToSpaces
			editor.action.indentationToTabs
			editor.action.indentUsingTabs
			editor.action.indentUsingSpaces
			editor.action.detectIndentation
			editor.action.reindentlines
			editor.action.reindentselectedlines
			editor.action.copyLinesUpAction
			editor.action.copyLinesDownAction
			editor.action.duplicateSelection
			editor.action.moveLinesUpAction
			editor.action.moveLinesDownAction
			editor.action.sortLinesAscending
			editor.action.sortLinesDescending
			editor.action.trimTrailingWhitespace
			editor.action.deleteLines
			editor.action.indentLines
			editor.action.outdentLines
			editor.action.insertLineBefore
			editor.action.insertLineAfter
			deleteAllLeft
			deleteAllRight
			editor.action.joinLines
			editor.action.transpose
			editor.action.transformToUppercase
			editor.action.transformToLowercase
			editor.action.transformToTitlecase
			editor.action.smartSelect.expand
			editor.action.smartSelect.shrink
			editor.action.forceRetokenize
			editor.action.toggleTabFocusMode
			editor.action.quickCommand
			editor.action.inPlaceReplace.up
			editor.action.inPlaceReplace.down
			editor.action.gotoLine
			editor.action.quickOutline
			editor.action.diffReview.next
			editor.action.diffReview.prev
			editor.action.selectToBracket
			editor.action.jumpToBracket
			codelens.showLensesInCurrentLine
			actions.find
			actions.findWithSelection
			editor.action.nextMatchFindAction
			editor.action.previousMatchFindAction
			editor.action.nextSelectionMatchFindAction
			editor.action.previousSelectionMatchFindAction
			editor.action.startFindReplaceAction
			editor.unfold
			editor.unfoldRecursively
			editor.fold
			editor.foldRecursively
			editor.foldAll
			editor.unfoldAll
			editor.foldAllBlockComments
			editor.foldAllMarkerRegions
			editor.unfoldAllMarkerRegions
			editor.toggleFold
			editor.foldLevel1
			editor.foldLevel2
			editor.foldLevel3
			editor.foldLevel4
			editor.foldLevel5
			editor.foldLevel6
			editor.foldLevel7
			editor.action.openLink
			editor.action.quickFix
			editor.action.refactor
			editor.action.sourceAction
			editor.action.organizeImports
			editor.action.autoFix
			editor.action.fixAll
			editor.action.triggerParameterHints
			editor.action.onTypeRename
			editor.action.rename
			editor.action.wordHighlight.next
			editor.action.wordHighlight.prev
			editor.action.wordHighlight.trigger
			editor.action.showAccessibilityHelp
			editor.action.inspectTokens
			editor.action.marker.next
			editor.action.marker.prev
			editor.action.marker.nextInFiles
			editor.action.marker.prevInFiles
			editor.action.revealDefinition
			editor.action.revealDefinitionAside
			editor.action.peekDefinition
			editor.action.revealDeclaration
			editor.action.peekDeclaration
			editor.action.goToTypeDefinition
			editor.action.peekTypeDefinition
			editor.action.goToImplementation
			editor.action.peekImplementation
			editor.action.goToReferences
			editor.action.referenceSearch.trigger
			editor.action.showHover
			editor.action.showDefinitionPreviewHover
			editor.action.insertCursorAbove
			editor.action.insertCursorBelow
			editor.action.insertCursorAtEndOfEachLineSelected
			editor.action.addSelectionToNextFindMatch
			editor.action.addSelectionToPreviousFindMatch
			editor.action.moveSelectionToNextFindMatch
			editor.action.moveSelectionToPreviousFindMatch
			editor.action.selectHighlights
			editor.action.changeAll
			editor.action.addCursorsToBottom
			editor.action.addCursorsToTop
			editor.action.triggerSuggest
		 */

		// engine.executeScript("editor.trigger('', 'editor.action." + action + "')");
		// engine.executeScript("editorView.trigger('source','editor.action.clipboardCopyAction')");
		// engine.executeScript("document.execCommand('" + action + "')");
	}

	public void setText(String text) {
		textProperty.set(text);
	}
	public void setTheme(String theme) {
		themeProperty.set(theme);
	}
	public void setLanguage(String language) {
		languageProperty.set(language);
	}
	public void setEditable(boolean editable) {
		editableProperty.set(editable);
	}
	public void setScrollTop(double value) {
		scrollTopProperty.set(value);
	}
	public void setScrollLeft(double value) {
		scrollLeftProperty.set(value);
	}
	public void setLineNumberEnabled(boolean value) {
		lineNumberEnabledProperty.set(value);
	}

	public void scrollToLine(double value) {
		execute("editor.revealLine(" + value + ")");
	}
	/* public void scrollToLine(int line) {
		editor.call("revealLine", line);
	}
	public void scrollToLineCenter(int line) {
		editor.call("revealLineInCenter", line);
	} */

	public void appendText(String text) {
		setText(getText() + text);
		scrollToLine(getLineCount()); // Last Line Scroll
	}

	// 반환할 때 textProperty.get()이 아닌 contentProperty()을 하는 이유는 상단에 설명
	public String getText() {
		return textProperty.get();
	}
	public String getTheme() {
		return themeProperty.get();
	}
	public String getLanguage() {
		return languageProperty.get();
	}
	public boolean isEditable() {
		return editableProperty.get();
	}
	public double getLineCount() {
		return (int) execute("editor.getModel().getLineCount()");
	}
	public boolean isLineNumberEnabled() {
		return lineNumberEnabledProperty.get();
	}
	public int getScrollTop() {
		return (int) execute("editor.getScrollTop()");
	}
	public int getScrollLeft() {
		return (int) execute("editor.getScrollLeft()");
	}
	public Position getCursorPosition() {
		int lineNumber = (int) execute("editor.getPosition().lineNumber");
		int column = (int) execute("editor.getPosition().column");
		return new Position(lineNumber, column);
	}
	public String getSelectedText() {
		return (String) this.execute("editor.getModel().getValueInRange(editor.getSelection())");
	}

	public StringProperty textProperty() {
		return textProperty;
	}
	public StringProperty languageProperty() {
		return languageProperty;
	}
	public StringProperty themeProperty() {
		return themeProperty;
	}
	public BooleanProperty editableProperty() {
		return editableProperty;
	}
	public ReadOnlyObjectProperty<Position> cursorPositionProperty() {
		return cursorPositionProperty;
	}
	public DoubleProperty scrollTopProperty() {
		return scrollTopProperty;
	}
	public DoubleProperty scrollLeftProperty() {
		return scrollLeftProperty;
	}
	public BooleanProperty lineNumberEnabledProperty() {
		return lineNumberEnabledProperty;
	}
}
