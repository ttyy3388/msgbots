package org.beuwi.msgbots.platform.app.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.parts.DebugAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MenuBarPart;
import org.beuwi.msgbots.platform.app.view.parts.SideAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.win.WindowType;
import org.beuwi.msgbots.platform.win.WindowWrap;

public class MainView extends StackPane implements View
{
	private static BorderPane pane = new BorderPane();

	private static Stage stage;

	// Application Stage
	public MainView(Stage stage)
	{
		this.stage = stage;

		this.setMinWidth(800);
		this.setMinHeight(600);
		this.setPrefWidth(1400);
		this.setPrefHeight(900);
		// this.setMaxWidth(1920);
		// this.setMaxHeight(1080);

		this.getChildren().add(MainView.getRoot());
		// this.getChildren().add(ToastAreaPart.getRoot());
		// this.getChildren().add(PopupAreaPart.getRoot());
	}

	@Override
	public void init()
	{
		pane.setTop(MenuBarPart.getRoot());
		pane.setLeft(SideAreaPart.getRoot());
		pane.setRight(DebugAreaPart.getRoot());
		pane.setCenter(MainAreaPart.getRoot());
		pane.setBottom(StatusBarPart.getRoot());
		// pane.setBottom(InputAreaPart.getRoot());
	}

	// Apply Final Style (High Priority)
	/* public void apply(Pane pane)
	{
		for (Node node : pane.getChildren())
		{
			try
			{
				if (((Pane) node).getChildren() != null)
				{
					apply((Pane) node);
				}

				((Pane) node).getStylesheets().add(ResourceUtils.getTheme("final"));
			}
			catch (Exception e) { }
		}
	} */

	public static class MainWindow extends WindowWrap
	{
		public MainWindow(Stage stage)
		{
			super(stage);

			setContent(new MainView(stage));
			setTitle("Messenger Bot Simulator");
			setType(WindowType.WINDOW);
		}
	}

	public static BorderPane getRoot()
	{
		return pane;
	}

	/* public static BorderPane getContent()
	{
		return pane;
	} */

	public static Stage getStage()
	{
		return stage;
	}
}