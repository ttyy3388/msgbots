package org.beuwi.simulator.platform.ui.window;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.beuwi.simulator.platform.application.views.MainWindow;

public class WindowStage extends Stage
{
	public WindowStage()
	{
		initModality(Modality.WINDOW_MODAL);
		initOwner(MainWindow.getStage());
		initStyle(StageStyle.UNDECORATED);
		initStyle(StageStyle.TRANSPARENT);
		toFront();
	}
}