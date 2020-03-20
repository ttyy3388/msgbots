package org.beuwi.simulator.platform.application.parts.sidebar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class SideBarPart
{
	@FXML
	private AnchorPane anpSideBar;

	@FXML
	private StackPane stpResizeBar;

	public SideBarPart() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/SideBarForm.fxml"));
		loader.setController(this);
		loader.load();
	}

	public AnchorPane getComponent()
	{
		return anpSideBar;
	}
}