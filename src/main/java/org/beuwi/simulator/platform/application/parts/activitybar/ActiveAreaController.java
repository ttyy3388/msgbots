package org.beuwi.simulator.platform.application.parts.activitybar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.application.actions.ResizeActiveAreaAction;

import java.net.URL;
import java.util.ResourceBundle;

public class ActiveAreaController implements Initializable
{
	@FXML
	private AnchorPane anpActiveArea;

	@FXML
	private StackPane stpResizeBar;

	@FXML
	private TabPane tapActiveArea;

	@FXML
	private ListView lsvExplorerPart;

	@FXML
	private AnchorPane anpSimulationPart;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		ActiveAreaManager.initialize(anpActiveArea, tapActiveArea);

        stpResizeBar.setOnMouseDragged(event ->
        {
            ResizeActiveAreaAction.update(event);
        });
	}
}