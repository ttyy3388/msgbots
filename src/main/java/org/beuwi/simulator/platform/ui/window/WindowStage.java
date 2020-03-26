package org.beuwi.simulator.platform.ui.window;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.views.MainWindow;

public class WindowStage extends Stage
{
	public WindowStage(int type)
	{
		switch (type)
		{
			case WindowType.WINDOW : break;
			case WindowType.DIALOG : initOwner(MainWindow.getStage()); break;
		}

		getIcons().add(new Image(getClass().getResource("/images/program_dark.png").toExternalForm()));
		// initStyle(StageStyle.UNDECORATED);
		// initStyle(StageStyle.TRANSPARENT);

        // Default Title
        setTitle("Messenger Bot Simulator");
        toFront();
	}
}