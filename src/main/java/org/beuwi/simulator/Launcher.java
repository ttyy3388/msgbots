package org.beuwi.simulator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.beuwi.simulator.compiler.engine.ScriptManager;
import org.beuwi.simulator.managers.BotManager;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.actions.*;
import org.beuwi.simulator.platform.application.views.MainWindowView;
import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;
import org.beuwi.simulator.platform.application.views.parts.*;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Launcher extends Application
{

	public Launcher() throws IOException
	{

	}

	WatchService WATCH_SERVICE = FileSystems.getDefault().newWatchService();
	WatchKey 	 WATCH_KEY     = null;

	@Override
	public void start(Stage stage)
	{
		try
		{
			// Text Anti Aliasing
			System.setProperty("prism.text", "t2k");
			System.setProperty("prism.lcdtext", "false");
			System.setProperty("prism.subpixeltext", "false");

			Paths.get(FileManager.BOTS_FOLDER.getAbsolutePath()).register
			(
				WATCH_SERVICE,
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY,
				StandardWatchEventKinds.OVERFLOW
			);

			new Thread(() ->
			{
				while (true)
				{
					try
					{
						WATCH_KEY = WATCH_SERVICE.take();
					}
					catch (InterruptedException e)
					{
						new ShowErrorDialog(e).display();

						break;
					}

					List<WatchEvent<?>> events = WATCH_KEY.pollEvents();

					for (WatchEvent<?> event : events)
					{
						Platform.runLater(() -> BotManager.refresh());
					}

					if (!WATCH_KEY.reset())
					{
						try
						{
							WATCH_SERVICE.close();
						}
						catch (Exception e)
						{
							new ShowErrorDialog(e).display();
						}
					}
				}
			}).start();

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
			EditorAreaPart.initialize();
			ActivityBarPart.initialize();
			StatusBarPart.initialize();
			SideBarPart.initialize(); // ActiveArea에서 ActivityBar, SideBar를 합치므로 먼저 초기화
			ActiveAreaPart.initialize();
			DebugRoomTab.initialize();
			// ShowAllLogsTab.initialize();

			new MainWindowView(stage).display();

			// Initialize Actions
			AddEditorTabAction.initialize();
			AddExplorerItemAction.initialize();
			ChangeSideBarAction.initialize();
			ClearExplorerItemsAction.initialize();
			CloseEditorTabAction.initialize();
			HideSideBarAction.initialize();
			OpenDebugRoomAction.initialize();
			// ReloadAllBotsAction.initialize();
			ResizeChatAreaAction.initialize();
			ResizeSideBarAction.initialize();
			SaveEditorTabAction.initialize();
			SelectActivityBarAction.initialize();
			SendChatMessageAction.initialize();
			ShowDebugMenuAction.initialize();
			ShowExplorerOptionAction.initialize();
			ShowFileMenuAction.initialize();
			ShowViewMenuAction.initialize();

			BotManager.refresh();

			ScriptManager.preInitialize();
		}
		catch (Exception e)
		{
			// new ShowErrorDialog(e).display();
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
