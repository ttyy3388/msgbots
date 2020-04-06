package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.utils.ResourceUtils;

public class ILogItem extends AnchorPane
{
	public ILogItem(String logText, String logDate, String logType)
	{
		ImageView itemIcon = new ImageView(ResourceUtils.getImage(logType + ".png"));
		Label 	  itemMsg  = new Label(logText);
		Label     itemDate = new Label(logDate);

		itemIcon.setFitWidth(20);
		itemIcon.setFitHeight(20);

		AnchorPane.setTopAnchor(itemIcon, 15.0);
		AnchorPane.setLeftAnchor(itemIcon, 15.0);

		AnchorPane.setTopAnchor(itemMsg, 15.0);
		AnchorPane.setLeftAnchor(itemMsg, 50.0);
		AnchorPane.setRightAnchor(itemMsg, 15.0);

		AnchorPane.setLeftAnchor(itemDate, 50.0);
		AnchorPane.setBottomAnchor(itemDate, 15.0);

		itemMsg.setWrapText(true);
		itemMsg.getStyleClass().add(logType);
		itemDate.getStyleClass().add(logType);

		this.setPrefHeight(90);
		this.getStyleClass().add("list-item");
		this.getChildren().addAll(itemIcon, itemMsg, itemDate);
	}
}