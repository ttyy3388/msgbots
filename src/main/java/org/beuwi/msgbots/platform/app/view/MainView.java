package org.beuwi.msgbots.platform.app.view;

import javafx.stage.Stage;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MenuBarPart;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.gui.control.VBox;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.platform.win.WindowType;
import org.beuwi.msgbots.platform.win.WindowWrap;

// Top : Menu Bar, Center : Main Area, Bottom : Status Bar
public class MainView extends VBox
{
	private static final VBox root = new VBox();
	private static Stage stage;

	// Application Stage
	public MainView(Stage stage)
	{
		this.stage = stage;

		setMinWidth(800);
		setMinHeight(600);
		setPrefWidth(1400);
		setPrefHeight(900);
		// this.setMaxWidth(1920);
		// this.setMaxHeight(1080);

		addItem(MenuBarPart.getRoot());
		addItem(MainAreaPart.getRoot());
		addItem(StatusBarPart.getRoot());

		// 추후 윈도우 프레임으로 이전
		stage.setMinWidth(800);
		stage.setMinHeight(600);
	}

	public static class MainWindow extends WindowWrap
	{
		public MainWindow(Stage stage)
		{
			super(stage);

			setContent(new MainView(stage));
			setTitle(SharedValues.DEFAULT_PROGRAM_TITLE);
			setTheme(ThemeType.DARK);
			setType(WindowType.WINDOW);
		}
	}

	public static VBox getRoot()
	{
		return root;
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