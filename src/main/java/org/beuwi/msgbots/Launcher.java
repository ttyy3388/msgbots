package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.actions.AddChatMessageAction;
import org.beuwi.msgbots.platform.app.view.actions.AddEditorTabAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDebugRoomAction;
import org.beuwi.msgbots.platform.app.view.actions.ShowNotificationAction;
import org.beuwi.msgbots.platform.app.view.parts.ActiveAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.EditorAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MenuBarPart;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.app.view.tabs.DebugRoomTab;
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
			Font.loadFont(ResourceUtils.getFont("consola"),      0);  // Family : "Consolas"
			Font.loadFont(ResourceUtils.getFont("consola-bold"),  0); // Family : "Consolas Bold"
			Font.loadFont(ResourceUtils.getFont("d2Coding"),     0);  // Family : "D2Coding"
			Font.loadFont(ResourceUtils.getFont("d2coding-bold"), 0); // Family : "D2Coding"
			Font.loadFont(ResourceUtils.getFont("roboto"), 	   0);  // Family : "Roboto"
			Font.loadFont(ResourceUtils.getFont("roboto-bold"),   0); // Family : "Roboto Bold"
			Font.loadFont(ResourceUtils.getFont("roboto-medium"), 0); // Family : "Roboto Medium"

			// 기본 스타일 지정
			Application.setUserAgentStylesheet(ResourceUtils.getTheme("base"));

			new MenuBarPart().init();
			new ActiveAreaPart().init();
			new EditorAreaPart().init();
			new StatusBarPart().init();

			new DebugRoomTab().init();

			new AddChatMessageAction().init();
			new AddEditorTabAction().init();
			new OpenDebugRoomAction().init();
			new ShowNotificationAction().init();

			new MainView(stage).display();

			OpenDebugRoomAction.execute();
			// ShowToastMessageAction.execute("Test Toast Message");
			// ShowNotificationAction.execute("Test", "Hello World!");
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