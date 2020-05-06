package org.beuwi.simulator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.beuwi.simulator.compiler.engine.ScriptEngine;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.MainView;
import org.beuwi.simulator.platform.application.views.actions.AddBotLogItemAction;
import org.beuwi.simulator.platform.application.views.actions.AddChatMessageAction;
import org.beuwi.simulator.platform.application.views.actions.AddEditorTabAction;
import org.beuwi.simulator.platform.application.views.actions.AddExplorerBotAction;
import org.beuwi.simulator.platform.application.views.actions.ChangeActivityTabAction;
import org.beuwi.simulator.platform.application.views.actions.DeleteEditorPaneAction;
import org.beuwi.simulator.platform.application.views.actions.HideSideBarAction;
import org.beuwi.simulator.platform.application.views.actions.MoveEditorPaneAction;
import org.beuwi.simulator.platform.application.views.actions.OpenDebugRoomTabAction;
import org.beuwi.simulator.platform.application.views.actions.OpenGlobalLogTabAction;
import org.beuwi.simulator.platform.application.views.actions.OpenSettingsTabAction;
import org.beuwi.simulator.platform.application.views.actions.RefreshBotsAction;
import org.beuwi.simulator.platform.application.views.actions.ResizeSideBarAction;
import org.beuwi.simulator.platform.application.views.actions.SelectActivityTabAction;
import org.beuwi.simulator.platform.application.views.actions.SplitEditorPaneAction;
import org.beuwi.simulator.platform.application.views.dialogs.CreateBotDialog;
import org.beuwi.simulator.platform.application.views.dialogs.ImportScriptDialog;
import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;
import org.beuwi.simulator.platform.application.views.tabs.GlobalLogTab;
import org.beuwi.simulator.platform.application.views.tabs.SettingsTab;
import org.beuwi.simulator.platform.ui.window.IWindowScene;
import org.beuwi.simulator.platform.ui.window.IWindowStage;
import org.beuwi.simulator.platform.ui.window.IWindowType;
import org.beuwi.simulator.platform.ui.window.IWindowView;
import org.beuwi.simulator.utils.ResourceUtils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
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
						Platform.runLater(() -> RefreshBotsAction.update());
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
			Font.loadFont(ResourceUtils.getFont("Consola"),      0); // Family : "Consolas"
			Font.loadFont(ResourceUtils.getFont("ConsolaBold"),  0); // Family :
			Font.loadFont(ResourceUtils.getFont("D2Coding"),     0); // Family : "D2Coding"
			Font.loadFont(ResourceUtils.getFont("D2codingBold"), 0); // Family : "D2Coding"
			Font.loadFont(ResourceUtils.getFont("Roboto"), 	   0); // Family : "Roboto"
			Font.loadFont(ResourceUtils.getFont("RobotoBold"),   0); // Family : "Roboto Bold"
			Font.loadFont(ResourceUtils.getFont("RobotoMedium"), 0); // Family : "Roboto Medium"

			// 기본 스타일 지정
			Application.setUserAgentStylesheet(ResourceUtils.getTheme("base"));

			// Initialize Views
			ActiveAreaPart.initialize();
			EditorAreaPart.initialize();

			DebugRoomTab.initialize();
			GlobalLogTab.initialize();
			SettingsTab.initialize();

			// Initialize View Actions
			AddBotLogItemAction.initialize();
			AddEditorTabAction.initialize();
			AddExplorerBotAction.initialize();
			ChangeActivityTabAction.initialize();
			DeleteEditorPaneAction.initialize();
			HideSideBarAction.initialize();
			MoveEditorPaneAction.initialize();
			OpenDebugRoomTabAction.initialize();
			OpenGlobalLogTabAction.initialize();
			OpenSettingsTabAction.initialize();
			RefreshBotsAction.initialize();
			ResizeSideBarAction.initialize();
			SelectActivityTabAction.initialize();
			AddChatMessageAction.initialize();
			SplitEditorPaneAction.initialize();

			RefreshBotsAction.update();
			ScriptEngine.preInitialize();

			// Set Window Primary Stage
			IWindowStage.setPrimaryStage(stage);
			IWindowStage.initializeStage(stage);

			IWindowView main = new IWindowView();

			main.setContent(new MainView());
			main.setType(IWindowType.WINDOW);
			main.setStage(stage);
			main.create();

			IWindowScene scene = new IWindowScene(main);

			stage.addEventHandler(KeyEvent.KEY_PRESSED, event ->
			{
				if (event.isControlDown())
				{
					switch (event.getCode())
					{
						case N : new CreateBotDialog().display(); break;
						case I : new ImportScriptDialog().display(); break;
					}

					if (event.isAltDown())
					{
						switch (event.getCode())
						{
							case S : OpenSettingsTabAction.update(); break;
						}
					}
				}

				switch (event.getCode())
				{
					case F8 : OpenGlobalLogTabAction.update(); break;
					case F9 : OpenDebugRoomTabAction.update(); break;
					case F10 : ScriptEngine.allInitialize(true); break;
				}

				event.consume();
			});

			// stage.setMinWidth(800);
			// stage.setMinHeight(600);
			// stage.setWidth(1400);
			// stage.setHeight(900);
			// stage.setMaxWidth(Double.MAX_VALUE);
			// stage.setMaxHeight(Double.MAX_VALUE);
			stage.setScene(scene);
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
