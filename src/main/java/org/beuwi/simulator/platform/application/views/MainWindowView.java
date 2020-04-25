package org.beuwi.simulator.platform.application.views;

import javafx.scene.layout.BorderPane;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;

public class MainWindowView extends BorderPane
{
	// Application Stage
	public MainWindowView()
	{
		setLeft(ActiveAreaPart.getRoot());
		setCenter(EditorAreaPart.getRoot());
	}
}