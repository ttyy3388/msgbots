package org.beuwi.simulator;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.actions.*;
import org.beuwi.simulator.platform.application.views.MainWindowView;
import org.beuwi.simulator.platform.application.views.parts.*;
import org.beuwi.simulator.platform.application.views.tabs.ChatRoomTab;
import org.beuwi.simulator.platform.application.views.tabs.GlobalLogTab;

public class Launcher extends Application
{
	@Override
	public void start(Stage stage)
	{
		try
		{
			// Text Anti Aliasing
			System.setProperty("prism.text", "t2k");
			System.setProperty("prism.lcdtext", "false");
			System.setProperty("prism.subpixeltext", "false");

			// Load Fonts
			Font.loadFont(getClass().getResourceAsStream("/fonts/Consola.ttf"),      0); // Family : "Consolas"
			Font.loadFont(getClass().getResourceAsStream("/fonts/ConsolaBold.ttf"),  0); // Family :
			Font.loadFont(getClass().getResourceAsStream("/fonts/D2Coding.ttf"),     0); // Family : "D2Coding"
			Font.loadFont(getClass().getResourceAsStream("/fonts/D2codingBold.ttf"), 0); // Family : "D2Coding"
			Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto.ttf"), 	   0); // Family : "Roboto"
			Font.loadFont(getClass().getResourceAsStream("/fonts/RobotoBold.ttf"),   0); // Family : "Roboto Bold"
			Font.loadFont(getClass().getResourceAsStream("/fonts/RobotoMedium.ttf"), 0); // Family : "Roboto Medium"

			// 기본 스타일 지정
			Application.setUserAgentStylesheet(getClass().getResource("/themes/default.css").toExternalForm());

			// Initialize Views
			ToolBarPart.initialize();
			ActiveAreaPart.initialize();
			EditorAreaPart.initialize();
			StatusBarPart.initialize();
			ChatRoomTab.initialize();
			GlobalLogTab.initialize();

			new MainWindowView(stage).display();

			// Initialize Actions
			AddEditorTabAction.initialize();
			AddExplorerItemAction.initialize();
			HideSideBarAction.initialize();
			OpenChatRoomTabAction.initialize();
			OpenGlobalLogTabAction.initialize();
			ResizeSideBarAction.initialize();
			SelectActivityTabAction.initialize();
			SendChatMessageAction.initialize();
			ShowExplorerOptionAction.initialize();

			for (int i = 0 ; i < 7 ; i ++)
			{
				AddExplorerItemAction.update("TEST : " + (i + 1));
				AddEditorTabAction.update("TEST : " + (i + 1));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void stop()
	{

	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
