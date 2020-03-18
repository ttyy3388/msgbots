package org.beuwi.simulator.platform.application.views;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainWindowView extends BorderPane
{
	public MainWindowView() throws Exception
	{
		setMinSize(1100, 700);
		setPrefSize(1300, 900);

		setTop(new MenuBarView());
		setLeft(new HBox(new ActivityBarView(), new SideBarView()));
		setCenter(new EditorAreaView());
		setBottom(new StatusBarView());

		// getStyleClass().add()
	}
}