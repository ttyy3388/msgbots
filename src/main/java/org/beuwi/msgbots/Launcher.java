package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.MainView.MainWindow;
import org.beuwi.msgbots.platform.app.view.actions.*;
import org.beuwi.msgbots.platform.app.view.navis.BotListNavi;
import org.beuwi.msgbots.platform.app.view.navis.DebugRoomNavi;
import org.beuwi.msgbots.platform.app.view.parts.*;
import org.beuwi.msgbots.platform.app.view.tabs.*;
import org.beuwi.msgbots.platform.util.*;

import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Launcher extends Application {
	WatchService WATCH_SERVICE = null;
	WatchKey     WATCH_KEY     = null;

	@Override
	public void init() {
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

		// Set Use Agent Style
		Application.setUserAgentStylesheet(ResourceUtils.getTheme("base"));
	}

	@Override
	public void start(Stage stage) {
		/* try {
			WATCH_SERVICE = FileSystems.getDefault().newWatchService();

			Paths.get(SharedValues.BOTS_FOLDER_FILE.getPath()).register(
				WATCH_SERVICE,
				ENTRY_CREATE,
				ENTRY_DELETE,
				ENTRY_MODIFY,
				OVERFLOW
			);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		new Thread(() -> {
			while (true) {
				try {
					WATCH_KEY = WATCH_SERVICE.take();
				}
				catch (InterruptedException e) {
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

		try {
			// Initialize Tabs
			new BotListNavi().init();
			new DebugRoomNavi().init();
			new GlobalConfigTab().init();
			new GlobalLogTab().init();

			// Initialize Layouts
			new ActiveAreaPart().init();
			new DebugAreaPart().init();
			new MainAreaPart().init();
			new MenuBarPart().init();
			new StatusBarPart().init();

			// Initialize Actions
			new AddBotLogBoxAction().init();
			new AddMainAreaTabAction().init();
			new OpenBotLogTabAction().init();
			new OpenDialogBoxAction().init();
			new OpenDocumentAction().init();
			new OpenProgramTabAction().init();
			new OpenScriptTabAction().init();
			new ExecuteEditMenuAction().init();
			new RefreshBotListAction().init();
			new RefreshBotLogsAction().init();
			new SaveEditorTabAction().init();
			new SendChatMessageAction().init();
			new TogglePowerBotsAction().init();

			new MainView(stage).init();
			new MainWindow(stage).create();

			// 추후 전체를 로딩하는게 아닌 새 파일만 로딩해야됨 > 스크립트 탭도 마차가지로 바꿔야됨 (컴파일 상태 유지)
			RefreshBotListAction.execute();

			ScriptManager.preInit();
		}
		catch (Throwable t) {
			t.printStackTrace();
			try {
				DisplayErrorDialogAction.execute(t);
			}
			// 에러 다이얼로그도 띄울 수 없으면 실행 불가능 상태이므로 OS 에러 메시지 출력
			catch (Exception e) {
				e.printStackTrace();
			}

			// FileManager.save("", e.)
		}
	}
	
	// Registry Program Data <- Edit 메뉴를 만들기 위해 사용하려고 했으나 너무 많은 수고가 들어가기에 개발 일시정지
	/* private void registry(Parent parent) {
		for (Node node : parent.getChildrenUnmodifiable()) {
			System.out.println(node);
			node.focusedProperty().addListener(event -> {
				ProgramData.selectedItemProperty().set(node);
			});
			if (node instanceof Parent) {
				registry((Parent) node);
			}
		}
	} */

	@Override
	public void stop() {
		// 프로그램을 종료해도 프로세서가 남아있는 현상 해결 해야됨. (해결 완료)
		Platform.exit();
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}
}