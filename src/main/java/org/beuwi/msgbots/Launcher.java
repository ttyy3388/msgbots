package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.beuwi.msgbots.platform.app.view.MainView.MainWindow;
import org.beuwi.msgbots.platform.app.view.actions.AddBotLogAction;
import org.beuwi.msgbots.platform.app.view.actions.ChangeThemeAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenBotLogTabAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDocumentAction;
import org.beuwi.msgbots.platform.app.view.actions.RefreshBotLogsAction;
import org.beuwi.msgbots.platform.app.view.actions.SaveEditorAreaTabAction;
import org.beuwi.msgbots.platform.app.view.actions.SendChatMessageAction;
import org.beuwi.msgbots.platform.app.view.actions.AddMainAreaTabAction;
import org.beuwi.msgbots.platform.app.view.actions.AddToastMessageAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenProgramTabAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenScriptTabAction;
import org.beuwi.msgbots.platform.app.view.actions.RefreshBotListAction;
import org.beuwi.msgbots.platform.app.view.actions.UpdateStatusBarAction;
import org.beuwi.msgbots.platform.app.view.parts.DebugAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MenuBarPart;
import org.beuwi.msgbots.platform.app.view.parts.ToastAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.SideAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.app.view.tabs.BotListTab;
import org.beuwi.msgbots.platform.app.view.tabs.DebugRoomTab;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalConfigTab;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalLogTab;
import org.beuwi.msgbots.platform.gui.control.LogBox;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.enums.LogType;
import org.beuwi.msgbots.platform.gui.enums.NoticeType;
import org.beuwi.msgbots.platform.util.ResourceUtils;

import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Launcher extends Application
{
	WatchService WATCH_SERVICE = null;
	WatchKey     WATCH_KEY     = null;

	@Override
	public void init()
	{
		// Text Anti Aliasing
		/* System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");
		System.setProperty("prism.subpixeltext", "false"); */

		/* try
		{
			WATCH_SERVICE = FileSystems.getDefault().newWatchService();

			Paths.get(SharedValues.BOTS_FOLDER_FILE.getPath()).register
			(
				WATCH_SERVICE,
				ENTRY_CREATE,
				ENTRY_DELETE,
				ENTRY_MODIFY,
				OVERFLOW
			);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

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
					Platform.runLater(() ->
					{
						RefreshBotListAction.execute();
						RefreshBotLogsAction.execute();
					});
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
		}).start(); */

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
			new BotListTab().init();
			new DebugRoomTab().init();
			new GlobalConfigTab().init();
			new GlobalLogTab().init();

			new DebugAreaPart().init();
			new MainAreaPart().init();
			new MenuBarPart().init();
			new SideAreaPart().init();
			new StatusBarPart().init();
			new ToastAreaPart().init();

			new AddBotLogAction().init();
			new AddMainAreaTabAction().init();
			new AddToastMessageAction().init();
			new ChangeThemeAction().init();
			new OpenBotLogTabAction().init();
			new OpenDialogAction().init();
			new OpenDocumentAction().init();
			new OpenProgramTabAction().init();
			new OpenScriptTabAction().init();
			new RefreshBotListAction().init();
			new RefreshBotLogsAction().init();
			new SaveEditorAreaTabAction().init();
			new SendChatMessageAction().init();
			new UpdateStatusBarAction().init();

			new MainWindow(stage).create();

			for (int i = 0 ; i < 10 ; i ++)
			{
				AddMainAreaTabAction.execute(new Tab("TEST : " + i));
			}

			AddToastMessageAction.execute(NoticeType.EVENT, "TEST TITLE", "CONTENT");

			OpenProgramTabAction.execute(GlobalConfigTab.getRoot());

			// 추후 전체를 로딩하는게 아닌 새 파일만 로딩해야됨 > 스크립트 탭도 마차가지로 바꿔야됨 (컴파일 상태 유지)
			// RefreshBotListAction.execute();

			// ScriptManager.preInit();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void stop()
	{
		// 프로그램을 종료해도 프로세서가 남아있는 현상 해결 해야됨. (해결 완료)
		Platform.exit();
		System.exit(0);
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}