package org.beuwi.msgbots.platform.app.view;

import javafx.stage.Stage;

import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.parts.DebugAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MenuBarPart;
import org.beuwi.msgbots.platform.app.view.parts.SideAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.app.view.parts.ToolAreaPart;
import org.beuwi.msgbots.platform.gui.layout.BorderPane;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.window.WindowType;
import org.beuwi.msgbots.platform.gui.window.WindowWrap;
import org.beuwi.msgbots.platform.util.SharedValues;

public class MainView implements View {
	private static final BorderPane innerArea = new BorderPane();
	private static final BorderPane outerArea = new BorderPane();
	private static final StackPane rootArea = new StackPane();

	// Primary Stage
	private static Stage stage;

	public MainView(Stage stage) {
		this.stage = stage;
	}

	public void init() {
		innerArea.setLeft(SideAreaPart.getRoot());
		innerArea.setCenter(MainAreaPart.getRoot());
		innerArea.setBottom(ToolAreaPart.getRoot());

		outerArea.setTop(MenuBarPart.getRoot());
		outerArea.setRight(DebugAreaPart.getRoot());
		outerArea.setCenter(innerArea);
		outerArea.setBottom(StatusBarPart.getRoot());

		rootArea.setMinWidth(800);
		rootArea.setMinHeight(600);
		rootArea.setPrefWidth(1400);
		rootArea.setPrefHeight(900);

		rootArea.getChildren().setAll(outerArea);

		// 추후 윈도우 프레임으로 이전
		stage.setMinWidth(800);
		stage.setMinHeight(600);
		// stage.setMaxWidth(1920);
		stage.setMaxHeight(1080);
	}

	public static class MainWindow extends WindowWrap {
		public MainWindow(Stage stage) {
			super(MainView.getStage());

			setContent(MainView.getRoot());
			setTitle(SharedValues.DEFAULT_PROGRAM_TITLE);
			// setTheme(ThemeType.DARK);
			setType(WindowType.WINDOW);
		}
	}

	public static Stage getStage() {
		return stage;
	}

	public static StackPane getRoot() {
		return rootArea;
	}
}