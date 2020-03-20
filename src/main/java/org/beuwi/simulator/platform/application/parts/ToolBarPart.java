package org.beuwi.simulator.platform.application.parts;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class ToolBarPart
{
	@FXML
	private AnchorPane anpToolBar;

	public ToolBarPart() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/ToolBarForm.fxml"));
		loader.setController(this);
		loader.load();
	}

	public AnchorPane getRoot()
	{
		return anpToolBar;
	}
}