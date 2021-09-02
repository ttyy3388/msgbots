package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.ScriptManager;
import org.beuwi.msgbots.shared.SharedValues;
import org.beuwi.msgbots.view.app.dialogs.StartProgramDialog;
import org.beuwi.msgbots.view.util.ViewManager;
import org.beuwi.msgbots.view.app.MainWindow;
import org.beuwi.msgbots.view.app.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.view.app.actions.RefreshAllBotsAction;
import org.beuwi.msgbots.view.app.dialogs.LaunchAppDialog;
import org.beuwi.msgbots.utils.ResourceUtils;
import org.beuwi.msgbots.setting.GlobalSettings;

import java.io.File;

public class Launcher extends Application {
	private final LaunchAppDialog dialog1 = new LaunchAppDialog();
	private StartProgramDialog dialog2;

	@Override
	public void init() {
		// 전역 값 등록(가장 먼저)
		SharedValues.register(false, "program.image", ResourceUtils.getImage("program"));
		SharedValues.register(false, "path.mainFolder", System.getProperty("user.dir"));
		SharedValues.register(false, "path.dataFolder", System.getProperty("user.dir") + File.separator + "data");
		SharedValues.register(true, "path.botFolder", System.getProperty("user.dir") + File.separator + "bots"); // 봇 폴더 커스텀 가능
		SharedValues.register(true, "path.saveFolder", System.getProperty("user.dir") + File.separator + "save"); // 세이브 폴더 커스텀 가능
		SharedValues.register(false, "file.mainFolder", new File(SharedValues.getString("path.mainFolder"))); // 프로그램 폴더 경로 강제
		SharedValues.register(false, "file.dataFolder", new File(SharedValues.getString("path.dataFolder"))); // 데이터 폴더 경로 강제
		SharedValues.register(true, "file.botFolder", new File(SharedValues.getString("path.botFolder"))); // 봇 폴더 커스텀 가능
		SharedValues.register(true, "file.saveFolder", new File(SharedValues.getString("path.saveFolder"))); // 세이브 폴더 커스텀 가능

		// 시작 다이얼로그 오픈
		OpenDialogBoxAction.getInstance().execute(dialog1);

		// 텍스트 렌더링 관련
		// System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "true");
		// System.setProperty("prism.order", "j2d");

		// 폰트 리스트 로드
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

		// 기본 테마 지정
		Application.setUserAgentStylesheet(
			ResourceUtils.getTheme(
				GlobalSettings.getString("program.colorTheme")
			)
		);

		// 파일 옵저버 등록
		FileManager.link(
			SharedValues.getFile("file.botFolder"),
			RefreshAllBotsAction.getInstance()::execute
		);
	}

	@Override
	public void start(Stage stage) {
		ViewManager.init(stage);
		MainWindow.init(stage);
		// ActionManager.init();

		// 봇 목록 초기화
		RefreshAllBotsAction
			.getInstance()
			.execute();

		// 다이얼로그가 닫혀야 시작
		dialog1.setOnClose(event1 -> {
			dialog2 = new StartProgramDialog();
			dialog2.setOnAction(event2 -> {
				// Start Window
				MainWindow.launch();
			});
			dialog2.open();
		});

		// MdbManager.switching();
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