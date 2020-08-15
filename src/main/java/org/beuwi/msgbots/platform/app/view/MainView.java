package org.beuwi.msgbots.platform.app.view;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.beuwi.msgbots.platform.app.view.parts.ActiveAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.EditorAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MenuBarPart;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.win.WindowType;
import org.beuwi.msgbots.platform.win.WindowWrap;

public class MainView
{
	private static final BorderPane pane = new BorderPane();
	private static Stage stage;

	// Application Stage
	public MainView(Stage stage)
	{
		this.stage = stage;

		pane.setTop(MenuBarPart.getRoot());
		pane.setLeft(ActiveAreaPart.getRoot());
		pane.setCenter(EditorAreaPart.getRoot());
		pane.setBottom(StatusBarPart.getRoot());

		pane.setMinWidth(800);
		pane.setMinHeight(600);
		pane.setPrefWidth(1400);
		pane.setPrefHeight(900);
		// pane.setMaxWidth(1920);
		// pane.setMaxHeight(1080);
	}

	public void display()
	{
		new MainWindow(stage).display();
	}

	public static BorderPane getRoot()
	{
		return pane;
	}

	private class MainWindow extends WindowWrap
	{
		public MainWindow(Stage stage)
		{
			super(stage);

			setContent(new MainView(stage).getRoot());
			setTitle("Messenger Bot Simulator");
			setType(WindowType.WINDOW);
			create();
		}
	}

	public static Stage getStage()
	{
		return stage;
	}
}