package org.beuwi.simulator.platform.application.views;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.beuwi.simulator.platform.application.actions.ResizeSideBarAction;
import org.beuwi.simulator.platform.application.actions.SelectActivityButtonAction;

public class MainWindowView extends BorderPane
{
	public MainWindowView() throws Exception
	{
		setMinSize(1100, 700);
		setPrefSize(1300, 900);

		AnchorPane anpMenuBar      = new MenuBarView();
		AnchorPane anpActiveArea   = new ActiveAreaView();
		AnchorPane anpEditorArea   = new EditorAreaView();
		AnchorPane anpStatusBar    = new StatusBarView();

		setTop(anpMenuBar);
		setLeft(anpActiveArea);
		setCenter(anpEditorArea);
		setBottom(anpStatusBar);

		// init Actions
		// AddEditorTabAction.initAction();
		// CloseEditorTabAction.initAction();
		ResizeSideBarAction.initAction();
		SelectActivityButtonAction.initAction();

		// getStyleClass().add()
	}
}