package org.beuwi.simulator.platform.application.parts.statusbar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StatusBarController implements Initializable
{
	@FXML
	private AnchorPane anpStatusBar;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// StatusBarManager.setComponent(anpStatusBar);
	}
}
