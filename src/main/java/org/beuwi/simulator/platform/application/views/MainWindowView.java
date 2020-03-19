package org.beuwi.simulator.platform.application.views;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.beuwi.simulator.platform.application.actions.ResizeActivityAreaAction;
import org.beuwi.simulator.platform.application.actions.SelectActivityTabAction;

public class MainWindowView extends BorderPane
{
	public MainWindowView() throws Exception
	{
		setMinSize(1100, 700);
		setPrefSize(1300, 900);

		AnchorPane anpMenuBar      = new MenuBarView();
		AnchorPane anpActivityArea = new ActivityAreaView();
		AnchorPane anpEditorArea   = new EditorAreaView();
		AnchorPane anpStatusBar    = new StatusBarView();

		setTop(anpMenuBar);
		setLeft(anpActivityArea);
		setCenter(anpEditorArea);
		setBottom(anpStatusBar);

		// init Actions
		// AddEditorTabAction.initAction();
		// CloseEditorTabAction.initAction();
		ResizeActivityAreaAction.initAction();
		// SelectActivityTabAction.initAction();

		// getStyleClass().add()
	}
}