package org.beuwi.msgbots.platform.app.view;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.beuwi.msgbots.platform.app.view.parts.ActiveAreaPart;
import org.beuwi.msgbots.platform.win.WindowType;
import org.beuwi.msgbots.platform.win.WindowView;

public class MainView extends BorderPane
{
	private static Stage stage;

	// Application Stage
	public MainView(Stage stage)
	{
		this.stage = stage;

		setLeft(ActiveAreaPart.getRoot());
		// setCenter(EditorAreaPart.getRoot());
		// setBottom(StatusBarPart.getRoot());

		setMinWidth(800);
		setMinHeight(600);
		setPrefWidth(1400);
		setPrefHeight(900);
		// setMaxWidth(1920);
		// setMaxHeight(1080);
	}

	public void display()
	{
		new MainWindow(stage).show();
	}

	private class MainWindow extends WindowView
	{
		public MainWindow(Stage stage)
		{
			super(stage);

			setContent(new MainView(stage));
			setType(WindowType.WINDOW);
			create();
		}
	}

	public static Stage getStage()
	{
		return stage;
	}
}