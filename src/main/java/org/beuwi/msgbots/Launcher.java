package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.openapi.KeyMap;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.MainView.MainWindow;
import org.beuwi.msgbots.platform.app.view.actions.*;
import org.beuwi.msgbots.platform.app.view.dialogs.ChooseBotsPathDialog;
import org.beuwi.msgbots.platform.app.view.parts.*;
import org.beuwi.msgbots.platform.app.view.tabs.*;
import org.beuwi.msgbots.platform.gui.control.Document;
import org.beuwi.msgbots.platform.gui.control.Page;
import org.beuwi.msgbots.platform.util.GlobalKeyMaps;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.setting.GlobalSettings;

import java.io.File;
import java.io.IOException;
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
			// 유저가 지정한 경로가 있다면
			String path = GlobalSettings.getString("program:bots_path");

			// 지정한 경로가 없거나 폴더가 아니라 파일인 경우
			if (path == null || !(new File(path).isDirectory())) {
				ChooseBotsPathDialog dialog = new ChooseBotsPathDialog();
				dialog.setOnAction(event -> {
					startProgram(stage);

					// 원래는 "UpdateBotsPathAction" 클래스를 통해 호출되나
					// BotView가 null인 상태이므로 메인 뷰를 로드한 후 직접 호출
					RefreshBotListAction.execute();
				});
				OpenDialogBoxAction.execute(dialog);
			}
			else {
				// 경로 업데이트
				SharedValues.setValue("BOTS_BOLDER_FILE", path);
				// 폴더 업데이트
				SharedValues.setValue("BOTS_FOLDER_FILE", new File(path));
				startProgram(stage);
			}
		}
		catch (Throwable e) {
			try {
				DisplayErrorDialogAction.execute(e);
			}
			// 다이얼 로그 박스도 안열리면 에러 출력하고 앱 멈춤
			catch (Throwable a) {
				e.printStackTrace(); stop();
			}
		}
	}

	private void startProgram(Stage stage) {
		registryKeyMaps(stage);
		startFileWatcher();
		startMainView(stage);
	}
	private void registryKeyMaps(Stage stage) {
		List<KeyMap> keyMaps = GlobalKeyMaps.getKeyMaps();
		// List<String> keys = new ArrayList<>(keyMaps.keySet());
		for (KeyMap binder : keyMaps) {
			stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
				if (binder.getKey().match(event)) {
					binder.getAction().handle(event);
				}
			});
		}
	}
	private void startFileWatcher() {
		try {
			try {
				WATCH_SERVICE = FileSystems.getDefault().newWatchService();

				File file = SharedValues.getFile("BOTS_FOLDER_FILE");
				// 해당 값이 null일 경우가 있나..? 하지만 혹시나 함
				/* if (file == null) {
					return ;
				} */

				Paths.get(file.getPath()).register(
					WATCH_SERVICE,
					ENTRY_CREATE,
					ENTRY_DELETE,
					ENTRY_MODIFY,
					OVERFLOW
				);
			} catch (IOException e) {
				e.printStackTrace();
			}

			new Thread(() -> {
				while (true) {
					try {
						WATCH_KEY = WATCH_SERVICE.take();
					} catch (InterruptedException e) {
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
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			
			// 추후 서비스 시작이 감지되어야 메인뷰가 띄어지도록 해야함
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
	private void startMainView(Stage stage) {
		try {
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

			OpenDocumentTabAction.execute(new Document("WELCOME GUIDE", new Page("welcome-guide-page")));

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
			ClassLoader loader = ClassLoader.getSystemClassLoader(); */

			// 미리 로드해야 하는 클래스들임 ( 안하면 Null 에러남 )
			// Class.forName("org.beuwi.msgbot.platform.util.SharedValues");
			// Class.forName("org.beuwi.msgbot.manager.FileManager");
			// Class.forName("org.beuwi.msgbot.platform.util.ResourceUtils");
			// Class.forName("org.beuwi.msgbot.setting.SharedSettings");
			// Class.forName("org.beuwi.msgbot.setting.GlobalSettings");
			// Class.forName("org.beuwi.msgbot.setting.ScriptSettings");

			launch(args);
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
}