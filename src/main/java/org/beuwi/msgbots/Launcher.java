package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.beuwi.msgbots.base.enums.LogType;
import org.beuwi.msgbots.base.enums.ToastType;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.ScriptManager;
import org.beuwi.msgbots.manager.MdbManager;
import org.beuwi.msgbots.program.app.MainWindow;
import org.beuwi.msgbots.program.app.manager.ViewManager;
import org.beuwi.msgbots.program.app.view.actions.AppendGlobalLogAction;
import org.beuwi.msgbots.program.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.program.app.view.actions.RefreshAllBotsAction;
import org.beuwi.msgbots.program.app.view.actions.ShowToastMessageAction;
import org.beuwi.msgbots.program.app.view.dialogs.StartProgramDialog;
import org.beuwi.msgbots.program.gui.control.LogItem;
import org.beuwi.msgbots.program.gui.control.ToastItem;
import org.beuwi.msgbots.utils.ResourceUtils;
import org.beuwi.msgbots.utils.SharedValues;
import org.beuwi.msgbots.setting.GlobalSettings;

public class Launcher extends Application {
	private final StartProgramDialog dialog = new StartProgramDialog();

	@Override
	public void init() {
		OpenDialogBoxAction.getInstance().execute(dialog); //

		// System.setProperty("prism.text", "t2k"); // 텍스트 렌더링 관련
		System.setProperty("prism.lcdtext", "true"); // 텍스트 렌더링 관련
		// System.setProperty("prism.order", "j2d"); // 텍스트 렌더링 관련

		// Load Fonts
		Font.loadFont(ResourceUtils.getFont("Consola"), 0); // Family : "Consolas"
		Font.loadFont(ResourceUtils.getFont("Consola-Bold"), 0); // Family : "Consolas"
		Font.loadFont(ResourceUtils.getFont("D2Coding-Regular"), 0); // Family : "D2Coding"
		Font.loadFont(ResourceUtils.getFont("D2Coding-Bold"), 0); // Family : "D2Coding"
		Font.loadFont(ResourceUtils.getFont("Roboto-Regular"), 0); // Family : "Roboto"
		Font.loadFont(ResourceUtils.getFont("Roboto-Medium"), 0); // Family : "Roboto Medium"
		Font.loadFont(ResourceUtils.getFont("Roboto-Bold"), 0); // Family : "Roboto Bold"
		Font.loadFont(ResourceUtils.getFont("NanumGothic-Bold"), 0); // Family : "나눔고딕"
		Font.loadFont(ResourceUtils.getFont("NanumGothic-Regular"), 0); // Family : "나눔고딕"
		Font.loadFont(ResourceUtils.getFont("JetBrainsMono-Regular"), 0); // Family : "JetBrainsMono"
		Font.loadFont(ResourceUtils.getFont("JetBrainsMono-Medium"), 0); // Family : "JetBrainsMono Medium"
		Font.loadFont(ResourceUtils.getFont("JetBrainsMono-Bold"), 0); // Family : "JetBrainsMono Bold"

		Application.setUserAgentStylesheet(ResourceUtils.getTheme(GlobalSettings.getString("program.colorTheme"))); // 기본 테마 지정

		FileManager.link(SharedValues.getFile("BOT_FOLDER_FILE"), RefreshAllBotsAction.getInstance()::execute); // 파일 옵저버 등록
	}

	@Override
	public void start(Stage stage) {
		ViewManager.init(stage);
		MainWindow.init(stage);
		// ActionManager.init();

		ShowToastMessageAction.getInstance().execute(new ToastItem(ToastType.INFO, "TEST INFO", "TEST CONTENT"));
		ShowToastMessageAction.getInstance().execute(new ToastItem(ToastType.ERROR, "TEST ERROR", "TEST CONTENT"));

		RefreshAllBotsAction.getInstance().execute(); // 봇 목록 초기화

		AppendGlobalLogAction.getInstance().execute(new LogItem(LogType.INFO, "2021/06/29 14:45:39", "Compile Complete: WELCOME"));
		AppendGlobalLogAction.getInstance().execute(new LogItem(LogType.DEBUG, "2021/06/29 14:45:40", "Compile Complete: WELCOME"));
		AppendGlobalLogAction.getInstance().execute(new LogItem(LogType.ERROR, "2021/06/29 14:45:41", "Runtime Error: org.mozilla.javascript.EcmaError: Reference Error: \"replire\" is not defined. (index.js#12)"));
		AppendGlobalLogAction.getInstance().execute(new LogItem(LogType.ERROR, "2021/06/29 14:45:42", "Runtime Error: org.mozilla.javascript.EcmaError: Reference Error: \"replire\" is not defined. (index.js#12)"));
		AppendGlobalLogAction.getInstance().execute(new LogItem(LogType.ERROR, "2021/06/29 14:45:43", "Runtime Error: org.mozilla.javascript.EcmaError: Reference Error: \"replire\" is not defined. (index.js#12)"));
		AppendGlobalLogAction.getInstance().execute(new LogItem(LogType.ERROR, "2021/06/29 14:45:44", "Runtime Error: org.mozilla.javascript.EcmaError: Reference Error: \"replire\" is not defined. (index.js#12)"));
		AppendGlobalLogAction.getInstance().execute(new LogItem(LogType.ERROR, "2021/06/29 14:45:45", "Runtime Error: org.mozilla.javascript.EcmaError: Reference Error: \"replire\" is not defined. (index.js#12)"));

		// 다이얼로그가 닫혀야 시작
		dialog.setOnClose(event -> {
			// Start Window
			MainWindow.launch();
		});

		MdbManager.switching();
		ScriptManager.preInit();
	}

	public static void main(String[] args) {
		try {
			launch(args);
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
}