package org.beuwi.simulator;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.views.MainView;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.application.views.parts.StatusBarPart;
import org.beuwi.simulator.platform.ui.editor.IEditorPane;
import org.beuwi.simulator.platform.ui.editor.IEditorTab;
import org.beuwi.simulator.platform.ui.window.IWindowStage;
import org.beuwi.simulator.platform.ui.window.IWindowType;
import org.beuwi.simulator.platform.ui.window.IWindowView;
import org.beuwi.simulator.utils.ResourceUtils;

public class Launcher extends Application
{
	@Override
	public void start(Stage stage)
	{
		try
		{
			// Text Anti Aliasing
			System.setProperty("prism.text", "t2k");
			System.setProperty("prism.lcdtext", "false");
			System.setProperty("prism.subpixeltext", "false");

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
			StatusBarPart.initialize();

			// Set Window Primary Stage
			IWindowStage.setPrimaryStage(stage);
			IWindowStage.initializeStage(stage);

			IWindowView main = new IWindowView(stage);

			main.setContent(new MainView());
			main.setType(IWindowType.WINDOW);
			main.create();
			main.show();

			EditorAreaPart.getComponent().getItems().add(new IEditorPane(new IEditorTab("TEST"),new IEditorTab("TEST"),new IEditorTab("TEST"),new IEditorTab("TEST"),new IEditorTab("TEST"),new IEditorTab("TEST"),new IEditorTab("TEST")));
		}
		catch (Exception e)
		{
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
