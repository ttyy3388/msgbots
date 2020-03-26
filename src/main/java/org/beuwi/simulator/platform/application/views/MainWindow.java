package org.beuwi.simulator.platform.application.views;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.application.views.parts.StatusBarPart;
import org.beuwi.simulator.platform.application.views.parts.ToolBarPart;

public class MainWindow extends BorderPane
{
	private static Stage stage = null;

	// Application Stage
	public MainWindow(Stage stage)
	{
		setTop(ToolBarPart.getRoot());
		setCenter(EditorAreaPart.getRoot());
		setBottom(StatusBarPart.getRoot());
		setLeft(ActiveAreaPart.getRoot());

		setMinSize(1100, 700);
		setPrefSize(1300, 900);

		getStylesheets().add(getClass().getResource("/themes/default.css").toExternalForm());
		getStylesheets().add(getClass().getResource("/themes/dark.css").toExternalForm());

		stage.setMinWidth(600);
		stage.setMinHeight(400);
		stage.setWidth(1300);
		stage.setHeight(900);
		// stage.setMaxWidth(Double.MAX_VALUE);
		// stage.setMaxHeight(Double.MAX_VALUE);
		stage.setScene(new Scene(this));

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
