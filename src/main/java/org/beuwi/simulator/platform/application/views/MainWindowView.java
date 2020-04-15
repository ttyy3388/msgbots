package org.beuwi.simulator.platform.application.views;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.application.views.parts.StatusBarPart;
import org.beuwi.simulator.platform.application.views.parts.ToolBarPart;

public class MainWindowView extends BorderPane
{
	private static Stage stage = null;

	// Application Stage
	public MainWindowView(Stage stage)
	{
		this.stage = stage;

		setTop(ToolBarPart.getRoot());
		setLeft(ActiveAreaPart.getRoot());
		setCenter(EditorAreaPart.getRoot());
		setBottom(StatusBarPart.getRoot());
	}

	public static Stage getStage()
	{
		return stage;
	}
}
