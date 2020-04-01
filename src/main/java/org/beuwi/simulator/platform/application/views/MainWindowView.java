package org.beuwi.simulator.platform.application.views;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.views.parts.*;
import org.beuwi.simulator.platform.ui.window.WindowScene;

public class MainWindowView extends BorderPane
{
	private static Stage stage = null;

	// Application Stage
	public MainWindowView(Stage stage)
	{
		setTop(ToolBarPart.getRoot());
		setLeft(ActiveAreaPart.getRoot());
		setCenter(EditorAreaPart.getRoot());
		setBottom(StatusBarPart.getRoot());

		getStyleClass().add("window");

		stage.getIcons().add(new Image(getClass().getResource("/images/program.png").toExternalForm()));
		// stage.setMinWidth(625);
		// stage.setMinHeight(435);
		stage.setWidth(1300);
		stage.setHeight(900);
		// stage.setMaxWidth(Double.MAX_VALUE);
		// stage.setMaxHeight(Double.MAX_VALUE);
		stage.setTitle("Messenger Bot Simulator");
		stage.setScene(new WindowScene(this));
		stage.toFront();

		stage.widthProperty().addListener((observable, oldValue, newValue) ->
		{

		});

		stage.heightProperty().addListener((observable, oldValue, newValue) ->
		{

		});

		this.stage = stage;
	}

	public void display()
	{
		// toFront가 안먹히는 문제점 수정해야됨
		stage.toFront();
		stage.show();
	}

	public static Stage getStage()
	{
		return stage;
	}
}
