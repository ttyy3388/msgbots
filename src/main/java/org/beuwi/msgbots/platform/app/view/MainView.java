package org.beuwi.msgbots.platform.app.view;

import javafx.stage.Stage;

import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.parts.DebugAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MenuBarPart;
import org.beuwi.msgbots.platform.app.view.parts.ActiveAreaPart;
import org.beuwi.msgbots.platform.gui.layout.BorderPanel;
import org.beuwi.msgbots.platform.gui.window.WindowType;
import org.beuwi.msgbots.platform.gui.window.WindowWrap;
import org.beuwi.msgbots.platform.util.SharedValues;

public class MainView implements View {
	private static final BorderPanel root = new BorderPanel();

	// Primary Stage
	private static Stage stage;

	public MainView(Stage stage) {
		this.stage = stage;
	}

	public void init() {
		root.setTop(MenuBarPart.getRoot());
		root.setLeft(ActiveAreaPart.getRoot());
		root.setCenter(MainAreaPart.getRoot());
		root.setRight(DebugAreaPart.getRoot());
		// root.setBottom(StatusBarPart.getRoot());

		root.setMinWidth(800);
		root.setMinHeight(600);
		root.setPrefWidth(1400);
		root.setPrefHeight(900);

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

	public static BorderPanel getRoot() {
		return root;
	}
}