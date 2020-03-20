package org.beuwi.simulator.platform.application.parts;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ActiveAreaPart
{
	@FXML
	private AnchorPane anpActiveArea;

	public ActiveAreaPart() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/ActiveAreaForm.fxml"));
		loader.setController(this);
		loader.load();
	}

	public AnchorPane getRoot()
	{
		return anpActiveArea;
	}
}