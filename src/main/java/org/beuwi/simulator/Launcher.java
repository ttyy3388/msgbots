package org.beuwi.simulator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.beuwi.simulator.compiler.engine.ScriptEngine;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.MainWindowView;
import org.beuwi.simulator.platform.application.views.actions.*;
import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.application.views.parts.StatusBarPart;
import org.beuwi.simulator.platform.application.views.parts.ToolBarPart;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;
import org.beuwi.simulator.platform.application.views.tabs.GlobalLogTab;
import org.beuwi.simulator.platform.application.views.tabs.SettingsTab;
import org.beuwi.simulator.platform.ui.window.IWindowScene;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Launcher extends Application
{
	WatchService WATCH_SERVICE = FileSystems.getDefault().newWatchService();
	WatchKey 	 WATCH_KEY     = null;

	public Launcher() throws IOException
	{

	}

	@Override
	public void start(Stage stage)
	{
		try
		{
			// Text Anti Aliasing
			System.setProperty("prism.text", "t2k");
			System.setProperty("prism.lcdtext", "false");
			System.setProperty("prism.subpixeltext", "false");

			Paths.get(FileManager.BOTS_FOLDER.getPath()).register
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
						break;
					}

					List<WatchEvent<?>> events = WATCH_KEY.pollEvents();

					for (WatchEvent<?> event : events)
					{
						Platform.runLater(() -> RefreshExplorerAction.update());
					}

					if (!WATCH_KEY.reset())
					{
						try
						{
							WATCH_SERVICE.close();
						}
						catch (Exception e)
						{
							e.printStackTrace();
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
			Application.setUserAgentStylesheet(getClass().getResource("/themes/base.css").toExternalForm());

			// Initialize Views
			ToolBarPart.initialize();
			ActiveAreaPart.initialize();
			EditorAreaPart.initialize();
			StatusBarPart.initialize();

			DebugRoomTab.initialize();
			GlobalLogTab.initialize();
			SettingsTab.initialize();

			// Initialize View Actions
			AddDebugLogAction.initialize();
			AddExplorerBotAction.initialize();
			ChangeActivityTabAction.initialize();
			CloseEditorPaneAction.initialize();
			HideSideBarAction.initialize();
			MoveEditorPaneAction.initialize();
			OpenDebugRoomTabAction.initialize();
			OpenGlobalLogTabAction.initialize();
			OpenSettingsTabAction.initialize();
			RefreshExplorerAction.initialize();
			ResizeSideBarAction.initialize();
			SaveAllEditorTabsAction.initialize();
			SelectActivityTabAction.initialize();
			SendChatMessageAction.initialize();
			SplitEditorPaneAction.initialize();

			RefreshExplorerAction.update();
			ScriptEngine.preInitialize();

			stage.getIcons().add(new Image(getClass().getResource("/images/program.png").toExternalForm()));
			// stage.setMinWidth(625);
			// stage.setMinHeight(435);
			stage.setWidth(1400);
			stage.setHeight(900);
			// stage.setMaxWidth(Double.MAX_VALUE);
			// stage.setMaxHeight(Double.MAX_VALUE);
			stage.setTitle("Messenger Bot Simulator");
			stage.setScene(new IWindowScene(new MainWindowView(stage)));
			stage.toFront();
			stage.show();
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
