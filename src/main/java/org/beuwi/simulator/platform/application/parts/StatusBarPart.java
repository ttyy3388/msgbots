package org.beuwi.simulator.platform.application.parts;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class StatusBarPart
{
	@FXML
	private AnchorPane anpStatusBar;

	public StatusBarPart() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/StatusBarForm.fxml"));
		loader.setController(this);
		loader.load();
	}

	public AnchorPane getRoot()
	{
		return anpStatusBar;
	}
}