package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.MainView.MainWindow;
import org.beuwi.msgbots.platform.app.view.actions.*;
import org.beuwi.msgbots.platform.app.view.parts.*;
import org.beuwi.msgbots.platform.app.view.tabs.*;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.setting.GlobalSettings;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

public class Launcher extends Application {
	WatchService WATCH_SERVICE = null;
	WatchKey     WATCH_KEY     = null;

	@Override
	public void init() {
		try {
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

			// Font.getFamilies().forEach(family -> System.out.println(family));

			// Base Theme
			Application.setUserAgentStylesheet(ResourceUtils.getTheme("base"));

			/* GlobalSettings.addChangedListener(() -> {
				ChangeColorThemeAction.execute(ThemeType.parse(GlobalSettings.getData("program:color_theme")));
			}); */
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
	@Override
	public void start(Stage stage) {
		try {
			try {
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

					for (WatchEvent<?> event : events) {
						Platform.runLater(() -> {
							RefreshBotListAction.execute();
						});
					}

					if (!WATCH_KEY.reset()) {
						try {
							WATCH_SERVICE.close();
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();

			// Initialize tabs
			new GlobalConfigTab().init();
			new BotListTab().init();
			new DebugRoomTab().init();
			new DetailLogTab().init();
			new GlobalLogTab().init();
			// new NoticeListTab().init();
			new ProblemListTab().init();
			// new RunningBotsTab().init();
			// new NoticeListTab().init();

			// Initialize parts
			new DebugAreaPart().init();
			new MainAreaPart().init();
			new MenuBarPart().init();
			new SideAreaPart().init();
			new StatusBarPart().init();
			new ToastListPart().init();
			new ToolAreaPart().init();

			// Initialize actions
			new AddBotLogItemAction().init();
			new AddMainAreaTabAction().init();
			new SaveBotScriptTabAction().init();
			new ShowToastMessageAction().init();
			new InputDetailLogAction().init();
			new OpenBotLogTabAction().init();
			new OpenDocumentTabAction().init();
			new OpenProgramTabAction().init();
			new OpenScriptTabAction().init();
			new RefreshBotListAction().init();
			new SendChatMessageAction().init();
			new ShowToastMessageAction().init();
			new UpdateCurrentPathAction().init();
			new UpdateStatusBarAction().init();

			new MainView(stage).init();
			new MainWindow(stage).create();

			CheckAppUpdateAction.execute();

			OpenDocumentTabAction.execute(SharedValues.WELCOME_GUIDE_DOCUMENT);

			// System.out.println(SharedValues.BOTS_FOLDER_FILE);

			/* BotView botView = BotListTab.getComponent();

			botView.getItems().add(new BotItem("TEST 1"));
			botView.getItems().add(new BotItem("TEST 2"));
			botView.getItems().add(new BotItem("TEST 3"));
			botView.getItems().add(new BotItem("TEST 4"));
			botView.getItems().add(new BotItem("TEST 5")); */

			/* InputDetailLogAction.execute(" Compile started : \"TEST 1\" (1.7s)");
			InputDetailLogAction.execute("> Task : Compile completed : \"TEST 1\" (1.7s)");
			InputDetailLogAction.execute("> Task : Compile started : \"TEST 2\" (1.7s)");
			InputDetailLogAction.execute("> Task : Compile completed : \"TEST 2\" (1.7s)");
			InputDetailLogAction.execute("> Task : Compile started : \"TEST 3\" (1.7s)");
			InputDetailLogAction.execute("> Task : Compile completed : \"TEST 3\" (1.7s)");
			InputDetailLogAction.execute("> Task : Running bots ... : \"TEST 1\" (1.7s)");
			InputDetailLogAction.execute("> Task : Running bots ... : \"TEST 2\" (1.7s)");
			InputDetailLogAction.execute("> Task : Running bots ... : \"TEST 3\" (1.7s)"); */
			// InputDetailLogAction.execute("Runtime Error : Cannot call method \"reply\" of undefined at \"TEST 1\":297 (response)");

			RefreshBotListAction.execute();

			if (GlobalSettings.getBoolean("program:start_auto_compile")) {
				ScriptManager.preInit();
			}
		}
		catch (Throwable e) {
			try {
				DisplayErrorDialogAction.execute(e);
			}
			// 다이얼 로그 박스도 안열리면 에러 출력
			catch (Throwable a) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stop() {
		// 프로그램을 종료해도 프로세서가 남아있는 현상 해결 해야됨. (해결 완료)
		Platform.exit();
		System.exit(0);
	}

	public static void main(String[] args) {
		try {

			/* Method method = ClassLoader.class.getDeclaredMethod("findLoadedClass", new Class[] { String.class });
			method.setAccessible(true);
			ClassLoader loader = ClassLoader.getSystemClassLoader();

			/* // 미리 로드해야 하는 클래스들임 ( 안하면 Null 에러남 )
			Class.forName("org.beuwi.msgbots.manager.FileManager");
			Class.forName("org.beuwi.msgbots.platform.util.SharedValues");
			Class.forName("org.beuwi.msgbots.platform.util.ResourceUtils");
			Class.forName("org.beuwi.msgbots.setting.SharedSettings");
			Class.forName("org.beuwi.msgbots.setting.GlobalSettings");
			Class.forName("org.beuwi.msgbots.setting.ScriptSettings"); */

			launch(args);
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
}