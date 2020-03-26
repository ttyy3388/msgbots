package org.beuwi.simulator.platform.application.views;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.application.views.parts.StatusBarPart;
import org.beuwi.simulator.platform.application.views.parts.ToolBarPart;
import org.beuwi.simulator.platform.ui.window.WindowScene;

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

		stage.getIcons().add(new Image(getClass().getResource("/images/program_dark.png").toExternalForm()));
		stage.setMinWidth(600);
		stage.setMinHeight(400);
		stage.setWidth(1300);
		stage.setHeight(900);
		// stage.setMaxWidth(Double.MAX_VALUE);
		// stage.setMaxHeight(Double.MAX_VALUE);
		stage.setTitle("Messenger Bot Simulator");
		stage.setScene(new WindowScene(this));
		stage.toFront();

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
