package org.beuwi.simulator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.actions.*;
import org.beuwi.simulator.platform.application.parts.*;
import org.beuwi.simulator.platform.ui.components.ITabType;

public class Launcher extends Application
{
	@Override
	public void start(Stage stage)
	{
		try
		{
			/* Text Anti Aliasing */
			System.setProperty("prism.text", "t2k");
			System.setProperty("prism.lcdtext", "false");
			System.setProperty("prism.subpixeltext", "false");

			/* Load Fonts */
			/* Font.loadFont(getClass().getResourceAsStream("/fonts/Consola.ttf"), 0);
			Font.loadFont(getClass().getResourceAsStream("/fonts/ConsolaBold.ttf"), 0);
			Font.loadFont(getClass().getResourceAsStream("/fonts/D2Coding.ttf"), 0);
			Font.loadFont(getClass().getResourceAsStream("/fonts/D2codingBold.ttf"), 0); */
			Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto.ttf"), 0);
			Font.loadFont(getClass().getResourceAsStream("/fonts/RobotoBold.ttf"), 0);
			Font.loadFont(getClass().getResourceAsStream("/fonts/RobotoMedium.ttf"), 0);

			// WindowStage.setPrimaryStage(stage);

			AnchorPane root = new AnchorPane();

			BorderPane pane = new BorderPane();

			ToolBarPart.initialize();
			ActiveAreaPart.initialize();
			EditorAreaPart.initialize();
			StatusBarPart.initialize();

			pane.setTop(ToolBarPart.getRoot());
			pane.setLeft(ActiveAreaPart.getRoot());
			pane.setCenter(EditorAreaPart.getRoot());
			pane.setBottom(StatusBarPart.getRoot());

			pane.setMinSize(1100, 700);
			pane.setPrefSize(1300, 900);

			// pane.getStyleClass().add("main");
			// root.getStyleClass().add("window");

			root.getStylesheets().add(getClass().getResource("/themes/default.css").toExternalForm());
			root.getStylesheets().add(getClass().getResource("/themes/DarkTheme.css").toExternalForm());

			root.getChildren().add(pane);

			AnchorPane.setTopAnchor(pane, .0);
			AnchorPane.setRightAnchor(pane, .0);
			AnchorPane.setBottomAnchor(pane, .0);
			AnchorPane.setLeftAnchor(pane, .0);

			// new WindowView(stage, pane, WindowType.WINDOW);

			stage.setMinWidth(600);
			stage.setMinHeight(400);
			stage.setWidth(1300);
			stage.setHeight(900);
			// stage.setMaxWidth(Double.MAX_VALUE);
			// stage.setMaxHeight(Double.MAX_VALUE);

			stage.setScene(new Scene(root));
			stage.show();

			// toFront가 안먹히는 문제점 수정해야됨
			stage.toFront();

			// lookup은 stage를 show해야 작동하므로 action들을 마지막에 초기화

			AddEditorTabAction.initialize();
			AddExplorerItemAction.initialize();
			ShowExplorerOptionAction.initialize();
			HideSideBarAction.initialize();
			ResizeSideBarAction.initialize();

			for (int i = 0 ; i < 10 ; i ++)
			{
				AddEditorTabAction.update(null, "test" + i, null, ITabType.SCRIPT);
				AddExplorerItemAction.update("test" + i);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void stop()
	{

	}


	public static void main(String[] args)
	{
		launch(args);
	}
}
