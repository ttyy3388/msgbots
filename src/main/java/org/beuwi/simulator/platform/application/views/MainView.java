package org.beuwi.simulator.platform.application.views;

import javafx.scene.layout.BorderPane;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.application.views.parts.StatusBarPart;

public class MainView extends BorderPane
{
	// Application Stage
	public MainView()
	{
		// setLeft(ActiveAreaPart.getRoot());
		setCenter(EditorAreaPart.getRoot());
		setBottom(StatusBarPart.getRoot());

		setMinWidth(800);
		setMinHeight(600);
		setPrefWidth(1400);
		setPrefHeight(900);
	}
}