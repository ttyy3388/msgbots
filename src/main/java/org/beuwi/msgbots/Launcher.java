package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.stage.Stage;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.actions.ShowNotificationAction;
import org.beuwi.msgbots.platform.app.view.parts.ActiveAreaPart;
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
			/* Font.loadFont(ResourceUtils.getFont("Consola"),      0); // Family : "Consolas"
			Font.loadFont(ResourceUtils.getFont("ConsolaBold"),  0); // Family :
			Font.loadFont(ResourceUtils.getFont("D2Coding"),     0); // Family : "D2Coding"
			Font.loadFont(ResourceUtils.getFont("D2codingBold"), 0); // Family : "D2Coding"
			Font.loadFont(ResourceUtils.getFont("Roboto"), 	   0); // Family : "Roboto"
			Font.loadFont(ResourceUtils.getFont("RobotoBold"),   0); // Family : "Roboto Bold"
			Font.loadFont(ResourceUtils.getFont("RobotoMedium"), 0); // Family : "Roboto Medium" */

			// 기본 스타일 지정
			Application.setUserAgentStylesheet(ResourceUtils.getTheme("base"));

			new ActiveAreaPart().init();
			// new StatusBarPart().init();

			new MainView(stage).display();

			new ShowNotificationAction("Test", "Hello World!").execute();
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