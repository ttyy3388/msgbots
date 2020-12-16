package org.beuwi.msgbots.platform.app.view;

import javafx.stage.Stage;
import org.beuwi.msgbots.platform.app.view.parts.DebugAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MenuBarPart;
import org.beuwi.msgbots.platform.app.view.parts.ToastAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.SideAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;
import org.beuwi.msgbots.platform.gui.layout.AnchorPanel;
import org.beuwi.msgbots.platform.gui.layout.BorderPanel;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;
import org.beuwi.msgbots.platform.gui.window.WindowType;
import org.beuwi.msgbots.platform.gui.window.WindowWrap;
import org.beuwi.msgbots.platform.util.SharedValues;

public class MainView extends StackPanel
{
	// Outer Area ( Vertical ) : Menu Bar, Inner Panel, Status Bar
	private static final BorderPanel outer = new BorderPanel();

	// Inner Area ( Horizontal ) : Side Bar, Main Area, Debug Area
	private static final StackPanel panel = new StackPanel();
	private static final BorderPanel inner = new BorderPanel();

	// Primary Stage
	private static Stage stage;

	/* Layouts */
	private static final StackPanel menubar = MenuBarPart.getRoot();
	private static final AnchorPanel statusbar = StatusBarPart.getRoot();
	private static final StackPanel mainarea = MainAreaPart.getRoot();
	private static final AnchorPanel sidearea = SideAreaPart.getRoot();
	private static final AnchorPanel debugarea = DebugAreaPart.getRoot();

	private static final AnchorPanel toastarea = ToastAreaPart.getRoot();

	public MainView(Stage stage)
	{
		this.stage = stage;

		outer.setTop(menubar);
		outer.setCenter(panel);
		outer.setBottom(statusbar);

		panel.setItem(inner);

		inner.setLeft(sidearea);
		inner.setCenter(mainarea);
		inner.setRight(debugarea);

		panel.addItem(toastarea);

		setMinWidth(800);
		setMinHeight(600);
		setPrefWidth(1400);
		setPrefHeight(900);

		// 추후 윈도우 프레임으로 이전
		stage.setMinWidth(800);
		stage.setMinHeight(600);
		// stage.setMaxWidth(1920);
		stage.setMaxHeight(1080);

		setItems(outer);
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

	public static Stage getStage()
	{
		return stage;
	}
}