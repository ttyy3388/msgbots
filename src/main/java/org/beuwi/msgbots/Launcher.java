package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.ScriptManager;
import org.beuwi.msgbots.shared.SharedValues;
import org.beuwi.msgbots.view.app.actions.OpenWebViewTabAction;
import org.beuwi.msgbots.view.app.dialogs.WelcomeToDialog;
import org.beuwi.msgbots.view.gui.control.WebPage;
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
	private WelcomeToDialog dialog2;

	@Override
	public void init() {
		// 전역 값 등록(가장 먼저)
		registerValues();
		updateValues();

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
	}

	@Override
	public void start(Stage stage) {
		ViewManager.init(stage);
		MainWindow.init(stage);
		// ActionManager.init();

		// 해당 다이얼로그에서 경로를 따로 지정하지 않고 넘어갔다면
		// 각 폴더(Bot, Save)를 기본 폴더로 지정한거로 인식함
		if (GlobalSettings.getBoolean("program.showStartDialog")) {
			dialog2 = new WelcomeToDialog();
			dialog2.setOnAction(event2 -> {
				updateValues(); // 값이 변경됐으므로 업데이트
				startProgram();
			});
		}

		// 다이얼로그가 닫혀야 시작
		dialog1.setOnClose(event1 -> {
			if (dialog2 != null) {
				dialog2.open();
			}
			else {
				startProgram();
			}
		});
	}

	private void registerValues() {
		SharedValues.register(false, "path.mainFolder", System.getProperty("user.dir"));
		SharedValues.register(false, "path.dataFolder", System.getProperty("user.dir") + File.separator + "data");
		SharedValues.register(true, "path.botFolder", System.getProperty("user.dir") + File.separator + "bots"); // 봇 폴더 커스텀 가능
		SharedValues.register(true, "path.saveFolder", System.getProperty("user.dir") + File.separator + "save"); // 세이브 폴더 커스텀 가능
		SharedValues.register(false, "file.mainFolder", new File(SharedValues.getString("path.mainFolder"))); // 프로그램 폴더 경로 강제
		SharedValues.register(false, "file.dataFolder", new File(SharedValues.getString("path.dataFolder"))); // 데이터 폴더 경로 강제
		SharedValues.register(true, "file.botFolder", new File(SharedValues.getString("path.botFolder"))); // 봇 폴더 커스텀 가능
		SharedValues.register(true, "file.saveFolder", new File(SharedValues.getString("path.saveFolder"))); // 세이브 폴더 커스텀 가능
		SharedValues.register(false, "program.image", ResourceUtils.getImage("program"));
		// SharedValues.register(false, "program.theme", GlobalSettings.getString("program.colorTheme"));
	}
	private void updateValues() {
		// 봇 폴더를 유저가 지정한 적이 있다면 업데이트함
		String path1 = GlobalSettings.getString("program.botFolderPath");
		if (path1 != null) {
			SharedValues.setValue("path.botFolder", path1);
			SharedValues.setValue("file.botFolder", new File(path1));
		}
		// 저장 폴더를 유저가 지정한 적이 있다면 업데이트함
		String path2 = GlobalSettings.getString("program.saveFolderPath");
		if (path2 != null) {
			SharedValues.setValue("path.saveFolder", path2);
			SharedValues.setValue("file.saveFolder", new File(path2));
		}
	}

	private void startProgram() {
		// 파일 옵저버 등록
		FileManager.link(
			SharedValues.getFile("file.botFolder"),
			RefreshAllBotsAction.getInstance()::execute
		);

		// 봇 목록 초기화
		RefreshAllBotsAction.getInstance().execute();
		// 앱 업데이트 체크
		// CheckAppUpdateAction.getInstance().execute();
		// 가이드 페이지 오픈
		OpenWebViewTabAction.getInstance().execute(
			"WELCOME GUIDE", new WebPage("welcome-guide-page")
		);

		// Start Window
		MainWindow.launch();
		// MdbManager.switching();

		if (GlobalSettings.getBoolean("program.startAutoCompile")) {
			ScriptManager.preInit();
		}
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