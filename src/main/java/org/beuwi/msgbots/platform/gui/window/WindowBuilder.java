package org.beuwi.msgbots.platform.gui.window;

import javafx.css.PseudoClass;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.beuwi.msgbots.utils.SharedValues;

public class WindowBuilder {
	private static final PseudoClass FOCUSED_PSEUDO_CLASS = PseudoClass.getPseudoClass("focused");

	private final Stage stage;

	private Scene scene;
	private Region content;
	private String title;

	public WindowBuilder(Stage stage) {
		this.stage = stage;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public void setContent(Region content) {
		this.content = content;
	}

	public Stage getStage() {
		return stage;
	}
	public Region getContent() {
		return content;
	}
	public String getTitle() {
		return title;
	}

	public void create() {
		if (scene == null) { scene = new Scene(content); }
		// 기본적으로 부모 이벤트는 자식에게 전달되나, 반대의 경우는 안됨
		// 그래서 자식 이벤트 발생 시, 최상위 부모(ROOT)에게 전달하도록 구현함
		/* scene.focusOwnerProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				Event.fireEvent(scene, new Event(newValue, scene, EventType.ROOT));
			}
		}); */

		// super.setContent(content);

		stage.focusedProperty().addListener(change -> {
			content.pseudoClassStateChanged(FOCUSED_PSEUDO_CLASS, stage.isFocused());
		});

		stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			/* if (event.getCode().equals(KeyCode.ALT)) {
				ToggleMenuBarAction.execute();
			} */
		});

		/* String style = FileManager.read(SharedValues.BASE_THEME_FILE);
		String data = style.replace(" ", "").split(".text\\{")[1].split("}")[0];
		data.split("-fx-font-smoothing-type:")[1].split(";")[0]
		scene.getStylesheets().addAll(".text {-fx-font-smoothing-type: " +
		GlobalSettings.getString("program:text_rendering") + "};"); */
		scene.setFill(Color.TRANSPARENT);

		stage.getIcons().add(
			SharedValues.getImage("program.image")
		);
		// stage.setAlwaysOnTop();
		stage.setScene(scene);
		stage.setTitle(title);
		stage.toFront();
		stage.show();
		// 테마를 프로그램 실행 도중 바꿀 일이 없으므로 해당 기능 필요없음
		/* finally {
			if (getTheme() != null) {
				ChangeThemeAction.execute(getStage().getScene(), getTheme());
			}
		} */
	}
}