package org.beuwi.msgbots.platform.gui.window;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.beuwi.msgbots.platform.util.SharedValues;

public class WindowFrame {
	private final Stage stage;

	private WindowType type;
	private Region content;
	private String title;

	public WindowFrame(Stage stage) {
		this.stage = stage;
	}

	public WindowType getType() {
		return type;
	}

	/* public ThemeType getTheme() {
		return theme;
	} */

	public Stage getStage() {
		return stage;
	}

	public Region getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public void setType(WindowType type) {
		this.type = type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(Region content) {
		this.content = content;
	}

	/* public void setTheme(ThemeType theme) {
		this.theme = theme;
	} */

	public void create() {
		final WindowScene scene = new WindowScene(content);

		try {
			if (type.equals(WindowType.DIALOG)) {
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.initOwner(MainView.getStage());
			}

			// super.setContent(content);

			stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
				/* if (event.getCode().equals(KeyCode.ALT)) {
					ToggleMenuBarAction.execute();
				} */
			});

			scene.getStylesheets().setAll(ResourceUtils.getTheme("dark"));
			/* String style = FileManager.read(SharedValues.BASE_THEME_FILE);
			String data = style.replace(" ", "").split(".text\\{")[1].split("}")[0];
			data.split("-fx-font-smoothing-type:")[1].split(";")[0]
			scene.getStylesheets().addAll(".text {-fx-font-smoothing-type: " +
					GlobalSettings.getString("program:text_rendering") + "};"); */
			// stage.getIcons().add(SharedValues.DEFAULT_PROGRAM_IMAGE);
			// stage.setAlwaysOnTop();
			stage.setScene(scene);
			stage.setTitle(title);
			stage.toFront();
			stage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		// 테마를 프로그램 실행 도중 바꿀 일이 없으므로 해당 기능 필요없음
		/* finally {
			if (getTheme() != null) {
				ChangeThemeAction.execute(getStage().getScene(), getTheme());
			}
		} */
	}

	public void open() {
		stage.show();
	}
}