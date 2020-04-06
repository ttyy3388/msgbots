package org.beuwi.simulator;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.actions.*;
import org.beuwi.simulator.platform.application.views.MainWindowView;
import org.beuwi.simulator.platform.application.views.dialogs.*;
import org.beuwi.simulator.platform.application.views.parts.*;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;
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

			DebugRoomTab.initialize();
			GlobalLogTab.initialize();

			CreateBotDialog.initialize();
			DeleteBotDialog.initialize();
			ExistsBotDialog.initialize();
			ImportScriptDialog.initialize();
			RenameBotDialog.initialize();
			SettingsDialog.initialize();
			ShowErrorDialog.initialize();

			new MainWindowView(stage).display();

			// Initialize Actions
			AddDebugLogAction.initialize();
			AddEditorTabAction.initialize();
			AddExplorerItemAction.initialize();
			ChangeActivityTabAction.initialize();
			CloseEditorTabAction.initialize();
			HideSideBarAction.initialize();
			OpenDebugRoomTabAction.initialize();
			OpenGlobalLogTabAction.initialize();
			ResizeSideBarAction.initialize();
			SelectActivityTabAction.initialize();
			SendChatMessageAction.initialize();

			new Integer("").parseInt(null);
			// AddExplorerItemAction.update("TEST");
		}
		catch (Exception e)
		{
			ShowErrorDialog.display(new Exception());
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
