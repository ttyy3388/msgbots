package org.beuwi.msgbots.view.app;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import org.beuwi.msgbots.view.app.views.MainView;
import org.beuwi.msgbots.view.gui.layout.StackPane;
import org.beuwi.msgbots.view.gui.window.WindowBuilder;
import org.beuwi.msgbots.shared.SharedValues;

public class MainWindow extends WindowBuilder {

	private static MainWindow instance = null;

	// Stage : Primary Stage
	private MainWindow(Stage stage) {
		super(stage);

		setContent(MainView.getInstance());
		setTitle(SharedValues.getString("program.title"));
		// setTheme(ThemeType.DARK);

		final StackPane pane = MainView.getInstance();
		// Compute Root Sizes
		// pane.setMinWidth(800);
		// pane.setMinHeight(600);
		// Root 노드는 절대 Stage 사이즈를 넘을 수 없음
		/* stage.widthProperty().addListener(change -> {
			pane.setMaxWidth(stage.getWidth());
		});
		stage.heightProperty().addListener(change -> {
			pane.setMaxHeight(stage.getHeight());
		}); */

		// Compute Stage Sizes
		stage.setMinWidth(800);
		stage.setMinHeight(600);
		// stage.setMaxHeight(1920);
		// stage.setMaxWidth(1080);
		stage.setWidth(1400);
		stage.setHeight(900);
	}

	public static void launch() {
		if (instance == null) {
			throw new RuntimeException("not initialized");
		}
		// Start Windows
		instance.create();
	}

	public static void init(Stage stage) {
		if (instance != null) {
			throw new RuntimeException("already initialized");
		}
		instance = new MainWindow(stage);
	}

	// [MainWindow]는 현재 인스턴스 접근을 허용하지 않음
	// 추후 꼭 해야 할 일이 생긴다면 그 때 검토함
	/* public static MainWindow getInstance() {
		if (instance == null) {
			throw new RuntimeException("not initialized");
		}
		return instance;
	} */
}
