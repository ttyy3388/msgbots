package org.beuwi.simulator.platform.application.views;

import javafx.scene.layout.BorderPane;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;

public class MainView extends BorderPane
{
	// Application Stage
	public MainView()
	{
		this.setLeft(ActiveAreaPart.getRoot());
		this.setCenter(EditorAreaPart.getRoot());

		this.setMinWidth(800);
		this.setMinHeight(600);
		this.setPrefWidth(1400);
		this.setPrefHeight(900);
	}
}