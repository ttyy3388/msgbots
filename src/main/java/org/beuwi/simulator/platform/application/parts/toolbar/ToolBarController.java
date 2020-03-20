package org.beuwi.simulator.platform.application.parts.toolbar;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IContextMenuType;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolBarController implements Initializable
{
	@FXML
	private AnchorPane anpMenuBar;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		IContextMenu menu = new IContextMenu(IContextMenuType.WINDOW);
		Button button = (Button) anpMenuBar.getChildren().get(0);

		menu.showingProperty().addListener((observable, oldValue, newValue) ->
		{
			button.pseudoClassStateChanged(PseudoClass.getPseudoClass("showing"), newValue);
		});

		button.setOnMousePressed(event ->
		{
			menu.show(button, Side.BOTTOM, 0, 0);
		});

		ToolBarManager.initialize(anpMenuBar);
	}
}