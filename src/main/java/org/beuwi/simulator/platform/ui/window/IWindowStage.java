package org.beuwi.simulator.platform.ui.window;

import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.views.MainWindowView;

public class IWindowStage extends Stage
{
	private int DEFAULT_WIDTH  = 16;
	private int DEFAULT_HEIGHT = 39;

	public IWindowStage(int type)
	{
		switch (type)
		{
			case IWindowType.WINDOW : break;
			case IWindowType.DIALOG :
				initModality(Modality.WINDOW_MODAL);
				initOwner(MainWindowView.getStage());
				break;
		}

		getIcons().add(new Image(getClass().getResource("/images/program.png").toExternalForm()));
		// initStyle(StageStyle.UNDECORATED);
		// initStyle(StageStyle.TRANSPARENT);

        // Default Title
        setTitle("Messenger Bot Simulator");
        toFront();
	}

	public void setMinSize(double width, double height)
	{
		setMinWidth(width + DEFAULT_WIDTH);
		setMinHeight(height + DEFAULT_HEIGHT);
	}

	public void setSize(double width, double height)
	{
		setWidth(width + DEFAULT_WIDTH);
		setHeight(height + DEFAULT_HEIGHT);
	}

}