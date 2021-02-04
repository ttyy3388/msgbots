package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.MainView.MainWindow;
import org.beuwi.msgbots.platform.app.view.actions.*;
import org.beuwi.msgbots.platform.app.view.parts.*;
import org.beuwi.msgbots.platform.app.view.tabs.*;
import org.beuwi.msgbots.platform.gui.control.BotItem;
import org.beuwi.msgbots.platform.gui.control.BotView;
import org.beuwi.msgbots.platform.gui.control.LogItem;
import org.beuwi.msgbots.platform.gui.control.LogView;
import org.beuwi.msgbots.platform.gui.control.NoticeItem;
import org.beuwi.msgbots.platform.gui.enums.NoticeType;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.beuwi.msgbots.platform.util.SharedValues;

public class Launcher extends Application {

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

			Application.setUserAgentStylesheet(ResourceUtils.getTheme("base"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage) {
		try {
			/* // Initialize tabs
			new BotListTab().init();
			new DebugRoomTab().init();
			new DetailLogTab().init();
			new GlobalConfigTab().init();
			new GlobalLogTab().init();
			new NoticeListTab().init();
			new ProblemListTab().init();
			// new RunningBotsTab().init();

			// Initialize parts
			new DebugAreaPart().init();
			new MainAreaPart().init();
			new MenuBarPart().init();
			new SideAreaPart().init();
			new StatusBarPart().init();
			new ToolAreaPart().init();

			// Initialize actions
			new AddMainAreaTabAction().init();
			new AddNoticeListItemAction().init();
			new InputDetailLogAction().init();
			new OpenProgramTabAction().init();
			new OpenScriptTabAction().init();
			new RefreshBotListAction().init();
			new SendChatMessageAction().init();

			new MainView(stage).init();
			new MainWindow(stage).create();

			OpenProgramTabAction.execute(GlobalConfigTab.getRoot());
			AddNoticeListItemAction.execute(new NoticeItem(
				NoticeType.ERROR,
				"TEST TITLE",
				"TEST CONTENT"
			));
			AddNoticeListItemAction.execute(new NoticeItem(
				NoticeType.EVENT,
				"TEST TITLE",
				"TEST CONTENT"
			));
			AddNoticeListItemAction.execute(new NoticeItem(
				NoticeType.WARNING,
				"TEST TITLE",
				"TEST CONTENT"
			));

			// System.out.println(SharedValues.BOTS_FOLDER_FILE);

			/* BotView botView = BotListTab.getComponent();

			botView.getItems().add(new BotItem("TEST 1"));
			botView.getItems().add(new BotItem("TEST 2"));
			botView.getItems().add(new BotItem("TEST 3"));
			botView.getItems().add(new BotItem("TEST 4"));
			botView.getItems().add(new BotItem("TEST 5")); */

			/* LogView logView = GlobalLogTab.getComponent();

			logView.getItems().add(new LogItem("error", "TEST 1", "2021 / 12 / 01"));
			logView.getItems().add(new LogItem("error", "TEST 1", "2021 / 12 / 01"));
			logView.getItems().add(new LogItem("error", "TEST 1", "2021 / 12 / 01"));
			logView.getItems().add(new LogItem("error", "TEST 1", "2021 / 12 / 01"));
			logView.getItems().add(new LogItem("error", "TEST 1", "2021 / 12 / 01"));

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

			System.out.println(1);
			System.out.println(ResourceUtils.getData("global_log.json").exists());

			// RefreshBotListAction.execute();
			// ScriptManager.preInit();
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}

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