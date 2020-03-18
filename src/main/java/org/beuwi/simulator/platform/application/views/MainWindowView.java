package org.beuwi.simulator.platform.application.views;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class MainWindowView extends BorderPane
{
	public MainWindowView() throws Exception
	{
		setMinSize(1100, 700);
		setPrefSize(1300, 900);

		AnchorPane anpMenuBar     = new MenuBarView();
		AnchorPane anpActivityBar = new ActivityBarView();
		AnchorPane anpSideBar     = new SideBarView();
		AnchorPane anpEditorArea  = new EditorAreaView();
		AnchorPane anpStatusBar   = new StatusBarView();

		HBox.setHgrow(anpSideBar, Priority.ALWAYS);

		setTop(anpMenuBar);
		setLeft(new HBox(anpActivityBar, anpSideBar));
		setCenter(anpEditorArea);
		setBottom(anpStatusBar);

		// getStyleClass().add()
	}
}