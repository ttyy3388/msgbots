package org.beuwi.msgbots.platform.gui.editor;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.platform.gui.control.ContextMenu;
import org.beuwi.msgbots.platform.gui.control.MenuItem;
import org.beuwi.msgbots.platform.gui.control.Separator;
import org.beuwi.msgbots.platform.util.ResourceUtils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class Monaco {
	private static final String DEFAULT_RESOURCE_LOCATION = ResourceUtils.getURL("/monaco/index.html");

	private final BooleanProperty minimapEnabledProperty = new SimpleBooleanProperty(true);
	private final BooleanProperty editableProperty = new SimpleBooleanProperty(true);
	private final StringProperty languageProperty = new SimpleStringProperty(null);
	private final StringProperty themeProperty = new SimpleStringProperty(null);
	// 모나코에서 텍스트 변경 시 텍스트
	// private final StringProperty contentProperty = new SimpleStringProperty(null);
	private final StringProperty textProperty = new SimpleStringProperty(null);
	// 원래는 cursor로 해도 되지만 cursorProperty랑 겹치기 때문에 caretProperty로 함
	private final ObjectProperty<Position> cursorPositionProperty = new SimpleObjectProperty(null);
	private final DoubleProperty scrollLeftProperty = new SimpleDoubleProperty(0);
	private final DoubleProperty scrollTopProperty = new SimpleDoubleProperty(0);

	// Monaco Window
	private JSObject window = null;

	// Monaco Editor
	private JSObject editor = null;

	// 에디터 메뉴를 사용하고 싶었으나.. 못찾아서 임시로 프로그램 메뉴 사용
	private final ContextMenu menu = new ContextMenu(
		new MenuItem("Undo", "Ctrl + Z" , event -> undo()),
		new MenuItem("Redo", "Ctrl + Y" , event -> redo()),
		new Separator(),
		new MenuItem("Cut", "Ctrl + X", event -> cut()),
		new MenuItem("Copy", "Ctrl + C", event -> copy()),
		new MenuItem("Paste", "Ctrl + V", event -> paste()),
		new Separator(),
		new MenuItem("Select All", "Ctrl + A", event -> selectAll())
	);

	private final WebView browser = new WebView();
	private final WebEngine engine;
	private final Worker worker;

	// ...
	/* public class final Bridge {
		public String getClipboard() {

		}
	} */

	// 전역 변수로 해야 사라지지 않고 남아있음
	private final JFunction scrollChangeListener = new JFunction(args -> {
		scrollTopProperty().set(getScrollTop());
		scrollLeftProperty().set(getScrollLeft());
		return null;
	});
	private final JFunction cursorChangeListener = new JFunction(args -> {
		cursorPositionProperty().set(getCursorPosition());
		return null;
	});
	private final JFunction contentChangeListener = new JFunction(args -> {
		String content = (String) editor.call("getValue");
		if (content != null) {
			textProperty().set(content);
		}
		return null;
	});

	public Monaco() {
		engine = browser.getEngine();
		worker = engine.getLoadWorker();

		// Registry Custom Key Codes
		menu.setNode(browser);
		browser.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.isControlDown()) {
				switch (event.getCode()) {
					// Cut
					case X : cut(); break;
					// Copy
					case C : copy(); break;
					// Paste
					/* case V :
					System.out.println("Paste");
					try  { final Clipboard a = Toolkit.getDefaultToolkit().getSystemClipboard();
					String b = (String) a.getData(DataFlavor.plainTextFlavor);
						System.out.println(b); } catch (Exception e) {
						e.printStackTrace();
					}
					// this.action("editor.paste(\"" + b + "\"");
					break; */
				}
			}
		});
		// view.setContextMenuEnabled();

		engine.load(DEFAULT_RESOURCE_LOCATION);
		engine.onAlertProperty().set(event -> {
			System.out.println(event.getData());
		});

		worker.stateProperty().addListener(event -> {
			State state = worker.getState();

			if (state.equals(State.SUCCEEDED)) {
				window = (JSObject) engine.executeScript("window");

				AtomicBoolean jsdone = new AtomicBoolean(false);
				AtomicInteger attempts = new AtomicInteger();

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
								editor = (JSObject) object;

								// Initialize Text
								if (getText() != null) {
									editor.call("setValue", getText());
								}

								window.setMember("cursorChangeListener", cursorChangeListener);
								window.setMember("scrollChangeListener", scrollChangeListener);
								window.setMember("contentChangeListener", contentChangeListener);

								// 초반에 값을 설정해 놓을 경우가 있는 것들을 적어둠
								// 프로펄티는 값이 변경돼야 호출되는데 초기값들은 값 변경으로 인식이 안되기 때문에
								this.execute("monaco.editor.setTheme('" + getTheme() + "')");
								this.execute("monaco.editor.setModelLanguage(editor.getModel(),'" + getLanguage() + "')");
								// 어떤건 [monaco.editor]고 어떤건 [editor]로 접근해야 함 (updateOptions)
								this.execute("editor.updateOptions({readOnly:" + !isEditable() + "})");
								this.execute("editor.updateOptions({minimap:{enabled:" + isMinimapEnabled() + "}})");

								// 외부에서 파일을 변경하거나 텍스트 값을 변경했을 때 발생
								textProperty().addListener(change -> {
									// setValue()를 하면 초기화처럼 redo, undo 스택과 같은 메모리가 사라지기 때문에 다른 방법 사용
									window.call("changeText", textProperty().get());
									/* engine.executeScript("(function(){" +
										"var selection =  editor.getModel().getFullModelRange();" +
										"var id = {major: 1, minor: 1};" +
										"var text = \"" + textProperty().get() + "\";" +
										"var data = {identifier: id, range: selection, text: text, forceMoveMarkers: true};" +
										"editor.executeEdits(\"my-source\", [data])}());");
									/* engine.executeScript(
										"var selection = editor.getModel().getFullModelRange();" +
										"var id = {major: 1, minor: 1};" +
										"var text = \"" + textProperty().get() + "\";" +
										"var data = {identifier: id, range: selection, text: text, forceMoveMarkers: true};" +
										"editor.executeEdits(\"my-source\", [data]);"
									);
									// this.action("changeText", "");
									/* engine.executeScript(
										"var selection =  editor.getModel().getFullModelRange();" + // All Select
										"var id = {major: 1, minor: 1};" +
										"var text = \"" + textProperty().get() + "\";" +
										"var data = {" +
											"identifier: id," +
											"range: selection," +
											"text: text," +
											"forceMoveMarkers:true" +
										"};" +
										"editor.executeEdits(\"" + getText() + "\", [data]"
									); */
									// Selection selection = this.action("editor.get")
									// this.action("var selection = editor.getSelection();" +
									// "editor.executeEdits(" + getText() + ")");
									// editor.call("setValue", getText());
								});

								languageProperty().addListener(change -> {
									this.execute("monaco.editor.setModelLanguage(editor.getModel(),'" + getLanguage() + "')");
								});
								themeProperty().addListener(change -> {
									this.execute("monaco.editor.setTheme('" + getTheme() + "')");
								});
								editableProperty().addListener(change -> {
									this.execute("editor.updateOptions({readOnly:" + isEditable() + "})");
								});
								scrollTopProperty().addListener(change -> {
									this.execute("editor.setScrollPosition({scrollTop:" + getScrollTop() + "})");
								});
								scrollLeftProperty().addListener(change -> {
									this.execute("editor.setScrollPosition({scrollLeft:" + getScrollLeft() + "})");
								});
								minimapEnabledProperty().addListener(change -> {
									this.execute("editor.updateOptions({minimap:{enabled:" + isMinimapEnabled() + "}})");
								});

								jsdone.set(true);
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

	protected void copy() {
		CopyStringAction.execute(getSelectedText());
	}
	protected void cut() {
		// Cut action 만 실행하면 클립보드 복사가 안되기에 copy도 실행 즉, copy and delete
		copy(); trigger("cut");
	}
	protected void redo() {
		trigger("redo");
	}
	protected void undo() {
		trigger("undo");
	}
	protected void paste() {
		trigger("paste");
	}
	protected void selectAll() {
		execute("editor.setSelection(editor.getModel().getFullModelRange())");
	}

	protected Object trigger(String action) {
		return execute("editor.trigger('', '" + action + "')");
	}

	protected Object execute(String action) {
		if (engine == null) {
			throw new NullPointerException("not initialized engine");
		}

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

	protected void setText(String text) {
		textProperty.set(text);
	}

	protected void setTheme(String theme) {
		themeProperty.set(theme);
	}

	protected void setLanguage(String language) {
		languageProperty.set(language);
	}

	protected void setEditable(boolean editable) {
		editableProperty.set(editable);
	}

	protected void setScrollTop(double value) {
		scrollTopProperty.set(value);
	}
	protected void setScrollLeft(double value) {
		scrollLeftProperty.set(value);
	}

	protected void setMinimapEnabled(boolean enabled) {
		minimapEnabledProperty.set(enabled);
	}

	protected void scrollToLine(double value) {
		execute("editor.revealLine(" + value + ")");
	}

	/* protected void setMinimap(boolean enabled) {
		minimapProperty.set(enabled);
	} */

	/* protected void useMiniMap(boolean value) {
		execute("editor.updateOptions({minimap:{enabled:" + value + ";}})");
	} */

	/* public void scrollToLine(int line) {
		editor.call("revealLine", line);
	}

	public void scrollToLineCenter(int line) {
		editor.call("revealLineInCenter", line);
	} */

	protected void appendText(String text) {
		setText(getText() + text);
		scrollToLine(getLineCount()); // Last Line Scroll
	}

	protected WebView getView() {
		return browser;
	}

	// 반환할 때 textProperty.get()이 아닌 contentProperty()을 하는 이유는 상단에 설명
	protected String getText() {
		return textProperty.get();
	}

	protected String getTheme() {
		return themeProperty.get();
	}

	protected String getLanguage() {
		return languageProperty.get();
	}

	protected boolean isEditable() {
		return editableProperty.get();
	}

	/* protected boolean isMinimap() {
		return minimapProperty.get();
	} */

	protected double getLineCount() {
		return (int) execute("editor.getModel().getLineCount()");
	}

	protected boolean isMinimapEnabled() {
		return minimapEnabledProperty.get();
	}

	protected int getScrollTop() {
		return (int) execute("editor.getScrollTop()");
	}

	protected int getScrollLeft() {
		return (int) execute("editor.getScrollLeft()");
	}

	protected Position getCursorPosition() {
		int lineNumber = (int) execute("editor.getPosition().lineNumber");
		int column = (int) execute("editor.getPosition().column");
		return new Position(lineNumber, column);
	}

	protected String getSelectedText() {
		return (String) this.execute("editor.getModel().getValueInRange(editor.getSelection())");
	}

	protected StringProperty textProperty() {
		return textProperty;
	}

	protected StringProperty languageProperty() {
		return languageProperty;
	}

	protected StringProperty themeProperty() {
		return themeProperty;
	}

	/* public StringProperty contentProperty() {
		return contentProperty;
	} */

	public BooleanProperty editableProperty() {
		return editableProperty;
	}

	protected ObjectProperty<Position> cursorPositionProperty() {
		return cursorPositionProperty;
	}

	public DoubleProperty scrollTopProperty() {
		return scrollTopProperty;
	}
	public DoubleProperty scrollLeftProperty() {
		return scrollLeftProperty;
	}

	protected BooleanProperty minimapEnabledProperty() {
		return minimapEnabledProperty;
	}
}
