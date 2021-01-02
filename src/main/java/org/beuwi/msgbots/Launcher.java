package org.beuwi.msgbots;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.control.TabView;
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
		// Application.setUserAgentStylesheet(ResourceUtils.getTheme("base"));
		Application.setUserAgentStylesheet(ResourceUtils.getTheme("dark"));
	}

	@Override
	public void start(Stage stage) {
		try {
			TabView test = new TabView(
				new TabItem("TEST", new Label("TEST")),
				new TabItem("TEST", new Label("TEST")),
				new TabItem("TEST", new Label("TEST")),
				new TabItem("TEST", new Label("TEST")),
				new TabItem("TEST", new Label("TEST"))
			);
			// test.setType(Side.LEFT);

			stage.setScene(new Scene(test));
			stage.show();
		}
		catch (Exception e) {
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