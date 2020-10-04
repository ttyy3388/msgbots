package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.MainView.MainWindow;
import org.beuwi.msgbots.platform.app.view.actions.AddBotLogItemAction;
import org.beuwi.msgbots.platform.app.view.actions.AddChatMessageAction;
import org.beuwi.msgbots.platform.app.view.actions.AddMainAreaTabAction;
import org.beuwi.msgbots.platform.app.view.actions.AddToastMessageAction;
import org.beuwi.msgbots.platform.app.view.actions.RefreshBotListAction;
import org.beuwi.msgbots.platform.app.view.actions.SaveScriptTabAction;
import org.beuwi.msgbots.platform.app.view.actions.ShowNotificationAction;
import org.beuwi.msgbots.platform.app.view.actions.ToggleDebugAreaAction;
import org.beuwi.msgbots.platform.app.view.actions.ToggleMenuBarAction;
import org.beuwi.msgbots.platform.app.view.actions.ToggleSideAreaAction;
import org.beuwi.msgbots.platform.app.view.parts.DebugAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.InputAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MenuBarPart;
import org.beuwi.msgbots.platform.app.view.parts.SideAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalSettingTab;
import org.beuwi.msgbots.platform.gui.control.Log;
import org.beuwi.msgbots.platform.gui.control.Log.Type;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class Launcher extends Application
{
	@Override
	public void start(Stage stage)
	{
		try
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

			// 기본 스타일 지정
			Application.setUserAgentStylesheet(ResourceUtils.getTheme("base"));

			new DebugAreaPart().init();
			new InputAreaPart().init();
			new MainAreaPart().init();
			new MenuBarPart().init();
			new SideAreaPart().init();
			new StatusBarPart().init();
			// new ToastAreaPart().init();

			// new DebugRoomTab().init();
			// new GlobalLogTab().init();
			new GlobalSettingTab().init();

			new AddBotLogItemAction().init();
			new AddChatMessageAction().init();
			new AddMainAreaTabAction().init();
			new RefreshBotListAction().init();
			new SaveScriptTabAction().init();
			new ShowNotificationAction().init();
			new AddToastMessageAction().init();
			new ToggleDebugAreaAction().init();
			new ToggleMenuBarAction().init();
			new ToggleSideAreaAction().init();

			new MainView(stage).init();
			new MainWindow(stage).create();

			RefreshBotListAction.execute();

			for (int i = 1 ; i < 20 ; i ++)
			{
				AddBotLogItemAction.execute(new Log("TEST", "TEST", Type.EVENT));
				AddMainAreaTabAction.execute(new Tab("TEST" + i));
			}

			AddMainAreaTabAction.execute(GlobalSettingTab.getRoot());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void stop()
	{
		// 프로그램을 종료해도 프로세서가 계속 실행되는 현상 해결 해야됨.
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}