package org.beuwi.msgbots.platform.app.view;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.parts.*;
import org.beuwi.msgbots.platform.gui.layout.BorderPane;
import org.beuwi.msgbots.platform.gui.layout.GridPane;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.layout.VBox;
import org.beuwi.msgbots.platform.gui.window.WindowType;
import org.beuwi.msgbots.platform.gui.window.WindowWrap;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.util.List;

public class MainView implements View {

	private static ObservableMap<String, Object> namespace;
	private static StackPane root;
	private static FormLoader loader;

	@FXML private StackPane stpMenuBar;
	@FXML private StackPane stpInnerArea;
	@FXML private StackPane stpStatusBar;

	@FXML private StackPane stpSideArea;
	@FXML private StackPane stpMainArea;
	@FXML private StackPane stpToolArea;
	@FXML private StackPane stpDebugArea;

	// Primary Stage
	private static Stage stage;

	public MainView(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void init() {
		loader = new FormLoader("layout", "main-view-layout", this);
		namespace = loader.getNamespace();
		root = loader.getRoot();

		// Add parts
		stpMenuBar.getChildren().add(MenuBarPart.getRoot());
		// stpInnerArea.getChildren().add(ToastListPart.getRoot());
		stpSideArea.getChildren().add(SideAreaPart.getRoot());
		stpMainArea.getChildren().add(MainAreaPart.getRoot());
		stpToolArea.getChildren().add(ToolAreaPart.getRoot());
		stpDebugArea.getChildren().add(DebugAreaPart.getRoot());
		stpStatusBar.getChildren().add(StatusBarPart.getRoot());

		// List<Node> children = root.getChildren();

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

	public static StackPane getRoot() {
		return root;
	}
}