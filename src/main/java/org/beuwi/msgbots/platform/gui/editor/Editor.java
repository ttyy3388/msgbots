package org.beuwi.msgbots.platform.gui.editor;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.KeyEvent;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.util.ProgramData;
import org.beuwi.msgbots.setting.GlobalSettings;

import java.io.File;

public final class Editor extends StackPane {
	private static final String DEFAULT_STYLE_CLASS = "editor";

	private final Monaco monaco = new Monaco();

	private boolean check = false;

	private final FileProperty fileProperty = new FileProperty(change -> {
		File file = this.fileProperty.get();

		// 파일이 존재하는 경우
		if (file != null || file.exists()) {
			// 한 파일만 지정 가능함
			if (!check) {
				setText(FileManager.read(file));
				FileManager.link(file, () -> {
					setText(FileManager.read(file));
				});

				check = true;
			}
			else {
				throw new RuntimeException("File already setted");
			}
		}
		// 파일이 존재하지 않다면 새로 쓸지 아니면 에러만 띄울지 무시할지 등 결정해야함
		else {
			// throw new RuntimeException();
			return ;
		}
	});

	public Editor() {
		this(null);
	}

	public Editor(File file) {
		if (file != null) {
			setFile(file);
		}

		/* focusedProperty().addListener(change -> {
			// Status Bar Update
			String fileName = getFile().getName();
			String linePosition = "";
			String lineEncoding = "CRLF";
			String fileEncoding = "UTF-8"; // 파일 인코딩은 현재 UTF-8만 지원

			// Content가 에디터 일 경우
			Position position = getCaretPosition();
			if (position != null){
				linePosition = position.getLineNumber() + ":" + position.getColumn();
			}

			UpdateStatusBarAction.execute(new String[] { fileName, linePosition, lineEncoding, fileEncoding});
		}); */

		// 텍스트 변경 감지 기능을 지원할 순 있으나 구현상의 문제로 더이상 진행하지 않음
		/* sceneProperty().addListener(change -> {
			// 프로그램 포커스 시
			final Stage stage = MainView.getStage();

			stage.focusedProperty().addListener(event -> {
				if (stage.isFocused()) {
					System.out.println(1);
					String changedText = FileManager.read(file);
					String currentText = getText();

					// 에디터에 입력된 텍스트랑 파일에서 읽은 텍스트랑 다를경우
					if (!currentText.equals(changedText)) {
						setText(changedText);
					}
				};
			});
		}); */

		focusedProperty().addListener(change -> {
			monaco.getView().requestFocus();
		});

		addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.isControlDown()) {
				switch (event.getCode()) {
					case S :
						if (getFile() != null) {
							FileManager.save(getFile(), getText());
						}
						break;
					// case S : save(); break;
					// case C : copy(); break;
					// case Z : redo(); break;
					// case Y : undo(); break;
				}
			}
		});

		ThemeType theme = ThemeType.parse(GlobalSettings.getString("program:color_theme"));
		String value = theme.equals(ThemeType.DARK) ? "vs-dark" : "vs-light";

		setTheme(value);
		// Default Theme
		// setTheme("vs-dark");
		// Default Language
		setLanguage("javascript");
		getStyleClass().add("editor");
		getChildren().setAll(monaco.getView());
	}

	public void cut() {
		monaco.cut();
	}
	public void copy() {
		monaco.copy();
	}
	public void undo() {
		monaco.undo();
	}
	public void redo() {
		monaco.redo();
	}
	public void save() {
		File file = getFile();
		if (file != null) {
			FileManager.save(file, getText());
		}
	}

	/* --------------------------------------------------------- */

	public void execute(String action) {
		monaco.execute(action);
	}

	/* --------------------------------------------------------- */

	public void setFile(File file) {
		this.fileProperty.set(file);
	}

	public void setText(String text) {
		monaco.setText(text);
	}

	public void setTheme(String theme) {
		monaco.setTheme(theme);
	}

	public void setLanguage(String language) {
		monaco.setLanguage(language);
	}

	public void setEditable(boolean editable) {
		monaco.setEditable(editable);
	}

	public void setScrollTop(double value) {
		monaco.setScrollTop(value);
	}
	public void setScrollLeft(double value) {
		monaco.setScrollLeft(value);
	}

	public void setMinimapEnabled(boolean enabled) {
		monaco.setMinimapEnabled(enabled);
	}

	public void appendText(String text) {
		monaco.appendText(text);
	}

	public File getFile() {
		return fileProperty.get();
	}

	public String getText() {
		return monaco.getText();
	}

	public String getTheme() {
		return monaco.getTheme();
	}

	public String getLanguage() {
		return monaco.getLanguage();
	}

	public Position getCursorPosition() {
		return cursorPositionProperty().get();
	}

	public double getScrollTop() {
		return monaco.getScrollTop();
	}
	public double getScrollLeft() {
		return monaco.getScrollLeft();
	}

	public boolean isEditable() {
		return monaco.isEditable();
	}

	public boolean isMinimapEnabled() {
		return monaco.isMinimapEnabled();
	}

	public FileProperty fileProperty() {
		return fileProperty;
	}

	public ReadOnlyStringProperty textProperty() {
		return monaco.textProperty();
	}

	public ReadOnlyBooleanProperty editableProperty() {
		return monaco.editableProperty();
	}

	public ReadOnlyStringProperty themeProperty() {
		return monaco.themeProperty();
	}

	public ReadOnlyStringProperty languageProperty() {
		return monaco.languageProperty();
	}

	public ReadOnlyObjectProperty<Position> cursorPositionProperty() {
		return monaco.cursorPositionProperty();
	}

	public ReadOnlyDoubleProperty scrollTopProperty() {
		return monaco.scrollTopProperty();
	}
	public ReadOnlyDoubleProperty scrollLeftProperty() {
		return monaco.scrollLeftProperty();
	}

	public ReadOnlyBooleanProperty minimapEnabledProperty() {
		return monaco.minimapEnabledProperty();
	}

	private class FileProperty extends SimpleObjectProperty<File> {
		public FileProperty(InvalidationListener listener) {
			addListener(listener);
		}
	}
}
