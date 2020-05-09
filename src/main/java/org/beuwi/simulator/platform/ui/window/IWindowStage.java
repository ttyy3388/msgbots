package org.beuwi.simulator.platform.ui.window;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.beuwi.simulator.utils.ResourceUtils;

public class IWindowStage extends Stage
{
	private int DEFAULT_WIDTH  = 16;
	private int DEFAULT_HEIGHT = 39;

	// Primary Stage
	private static Stage primaryStage;

	public IWindowStage()
	{
		initializeStage(this);
	}

	public static Stage getPrimaryStage()
	{
		return primaryStage;
	}

	public static void setPrimaryStage(Stage stage)
	{
		primaryStage = stage;
	}

	public static void initializeStage(Stage stage)
	{
		stage.getIcons().add(ResourceUtils.getImage("program"));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setTitle("Messenger Bot Simulator"); // Default Title
		stage.toFront();
	}

	public void setType(IWindowType type)
	{
		switch (type)
		{
			case MAIN : break;

			case DIALOG :
				initModality(Modality.WINDOW_MODAL);
				initOwner(primaryStage);
				break;
		}
	}
}