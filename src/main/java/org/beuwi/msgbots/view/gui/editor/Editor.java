package org.beuwi.msgbots.view.gui.editor;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.KeyEvent;

import org.beuwi.msgbots.keyboard.KeyBinding;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.setting.GlobalSettings;

import java.io.File;

public class Editor extends Monaco {
	private boolean check = false;

	private final ObjectProperty<File> fileProperty = new SimpleObjectProperty<>();
	private final InvalidationListener fileListener = change -> {
		File file = this.fileProperty.get();
		// 파일이 존재하는 경우
		if (file != null || file.exists()) {
			// 한 파일만 지정 가능함
			if (!check) {
				setText(FileManager.read(file));
				FileManager.link(file, () -> {
					// 삭제됐을 때 읽어오기 방지
					if (!file.exists()) {
						// FileManager.unlink(file);
						return ;
					}
					setText(FileManager.read(file));
				});
				check = true;
			}
			else {
				throw new RuntimeException("File already initialized");
			}
		}
		// 파일이 존재하지 않다면 새로 쓸지 아니면 에러만 띄울지 무시할지 등 결정해야함
		else {
			// throw new RuntimeException();
			return ;
		}
	};

	public Editor() {
		this(null);
	}

	public Editor(File file) {
		/* loadedProperty().addListener((observable, oldValue, newValue) -> {
			// 스타일 적용까지 모든 로딩이 완료돼야 뷰에 추가함
			if (newValue) {
				getChildren().setAll(monaco);
			}
		}); */
		fileProperty().addListener(fileListener);
		/* focusedProperty().addListener(change -> {
			requestFocus();
		}); */

		addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			switch (KeyBinding.matching(event)) {
				case SAVE -> save();
			}
		});

		if (file != null) {
			setFile(file);
		}

		String theme = GlobalSettings.getString("program.colorTheme");
		setTheme(theme.toLowerCase());
		setLanguage("javascript");
		getStyleClass().add("editor");
	}

	/* --------------------------------------------------------- */

	public void save() {
		File file = getFile();
		if (file != null) {
			FileManager.write(file, getText());
		}
	}

	/* --------------------------------------------------------- */

	public void setFile(File file) {
		this.fileProperty.set(file);
	}
	public File getFile() {
		return fileProperty.get();
	}
	public ObjectProperty<File> fileProperty() {
		return fileProperty;
	}
}
