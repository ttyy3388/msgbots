package org.beuwi.simulator;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.actions.*;
import org.beuwi.simulator.platform.application.views.MainWindow;
import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;
import org.beuwi.simulator.platform.application.views.parts.*;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;

public class Launcher extends Application
{
	@Override
	public void start(Stage stage)
	{
		try
		{
			/* Text Anti Aliasing */
			System.setProperty("prism.text", "t2k");
			System.setProperty("prism.lcdtext", "false");
			System.setProperty("prism.subpixeltext", "false");

			/* Load Fonts */
			Font.loadFont(getClass().getResourceAsStream("/fonts/Consola.ttf"),      0); // Family : "Consolas"
			Font.loadFont(getClass().getResourceAsStream("/fonts/ConsolaBold.ttf"),  0); // Family :
			Font.loadFont(getClass().getResourceAsStream("/fonts/D2Coding.ttf"),     0); // Family : "D2Coding"
			Font.loadFont(getClass().getResourceAsStream("/fonts/D2codingBold.ttf"), 0); // Family : "D2Coding"
			Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto.ttf"), 	   0); // Family : "Roboto"
			Font.loadFont(getClass().getResourceAsStream("/fonts/RobotoBold.ttf"),   0); // Family : "Roboto Bold"
			Font.loadFont(getClass().getResourceAsStream("/fonts/RobotoMedium.ttf"), 0); // Family : "Roboto Medium"

			/* Initialize Views */
			ToolBarPart.initialize();
			EditorAreaPart.initialize();
			ActivityBarPart.initialize();
			StatusBarPart.initialize();
			SideBarPart.initialize(); // ActiveArea에서 ActivityBar, SideBar를 합치므로 먼저 초기화
			ActiveAreaPart.initialize();
			DebugRoomTab.initialize();
			// ShowAllLogsTab.initialize();

			new MainWindow(stage).display();

			/* Initialize Actions */
			AddEditorTabAction.initialize();
			AddExplorerItemAction.initialize();
			CloseEditorTabAction.initialize();
			HideSideBarAction.initialize();
			OpenDebugRoomAction.initialize();
			// ReloadAllBotsAction.initialize();
			ResizeChatAreaAction.initialize();
			ResizeSideBarAction.initialize();
			SelectActivityBarAction.initialize();
			SendChatMessageAction.initialize();
			ShowDebugMenuAction.initialize();
			ShowExplorerOptionAction.initialize();
			ShowFileMenuAction.initialize();
			ChangeSideBarAction.initialize();
			ShowViewMenuAction.initialize();

			for (int i = 0 ; i < 10 ; i ++)
			{
				AddExplorerItemAction.update("test" + i);
			}

			Integer.parseInt("a");
		}
		catch (Exception e)
		{
			new ShowErrorDialog(e).display();

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
