package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.beuwi.msgbots.platform.app.view.MainView.MainWindow;
import org.beuwi.msgbots.platform.app.view.actions.AddChatMessageAction;
import org.beuwi.msgbots.platform.app.view.actions.AddMainAreaTabAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenScriptTabAction;
import org.beuwi.msgbots.platform.app.view.actions.RefreshBotListAction;
import org.beuwi.msgbots.platform.app.view.actions.ToggleDebugAreaAction;
import org.beuwi.msgbots.platform.app.view.actions.ToggleMenuBarAction;
import org.beuwi.msgbots.platform.app.view.actions.ToggleSideAreaAction;
import org.beuwi.msgbots.platform.app.view.actions.UpdateStatusBarAction;
import org.beuwi.msgbots.platform.app.view.parts.DebugAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.EditorAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MenuBarPart;
import org.beuwi.msgbots.platform.app.view.parts.SideAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class Launcher extends Application
{
	@Override
	public void init()
	{
		// Text Anti Aliasing
		/* System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");
		System.setProperty("prism.subpixeltext", "false"); */

		// Load Fonts
		Font.loadFont(ResourceUtils.getFont("consola"),       0); // Family : "Consolas"
		Font.loadFont(ResourceUtils.getFont("consola-bold"),  0); // Family : "Consolas Bold"
		Font.loadFont(ResourceUtils.getFont("d2coding"),      0); // Family : "D2Coding"
		Font.loadFont(ResourceUtils.getFont("d2coding-bold"), 0); // Family : "D2Coding"
		Font.loadFont(ResourceUtils.getFont("roboto"), 	    0); // Family : "Roboto"
		Font.loadFont(ResourceUtils.getFont("roboto-bold"),   0); // Family : "Roboto Bold"
		Font.loadFont(ResourceUtils.getFont("roboto-medium"), 0); // Family : "Roboto Medium"

		// Set Use Agent Style
		Application.setUserAgentStylesheet(ResourceUtils.getTheme("base"));
	}

	@Override
	public void start(Stage stage)
	{
		try
		{
			new DebugAreaPart().init();
			new EditorAreaPart().init();
			new MenuBarPart().init();
			new SideAreaPart().init();
			new StatusBarPart().init();
			new MainAreaPart().init();

			new AddChatMessageAction().init();
			new AddMainAreaTabAction().init();
			new OpenScriptTabAction().init();
			new RefreshBotListAction().init();
			new ToggleDebugAreaAction().init();
			new ToggleMenuBarAction().init();
			new ToggleSideAreaAction().init();
			new UpdateStatusBarAction().init();

			new MainWindow(stage).create();

			RefreshBotListAction.execute();

			UpdateStatusBarAction.execute(new String[] { "Test Tab Name", "Test Line Position", "Test Line Encoding", "Test File Encoding" });
			AddMainAreaTabAction.execute(new Tab("Test"));

			OpenScriptTabAction.execute("TEST");
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void stop()
	{
		// 프로그램을 종료해도 프로세서가 계속 실행되는 현상 해결 해야됨. (해결 완료)
		Platform.exit();
		System.exit(0);
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}