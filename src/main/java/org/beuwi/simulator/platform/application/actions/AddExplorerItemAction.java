package org.beuwi.simulator.platform.application.actions;

import com.jfoenix.controls.JFXToggleButton;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AddExplorerItemAction
{
	private static ListView<HBox> listView;

	public static void initialize()
	{
		// listView = (ListView) ActiveAreaPart.getNameSpace().get("lsvExplorerTab");
	}

	// Script Name (No Extension)
	public static void update(String name)
	{
		HBox 			itemCell    = new HBox();
		CheckBox        itemCheck   = new CheckBox();
		Label 			itemName    = new Label(name);
		Button 		    itemCompile = new Button();
		JFXToggleButton itemSwitch  = new JFXToggleButton();

		itemCell.setId(name);
		itemCell.setPrefHeight(35);
		itemCell.getChildren().addAll
		(
			getItemVBox(itemCheck,   Pos.CENTER, 	  Priority.NEVER,  25),
			getItemVBox(itemName,    Pos.CENTER_LEFT, Priority.ALWAYS, 50),
			getItemVBox(itemCompile, Pos.CENTER, 	  Priority.NEVER,  40),
			getItemVBox(itemSwitch,  Pos.CENTER_LEFT, Priority.NEVER,  40)
		);
		itemCell.getStyleClass().add("list-item");

		itemCompile.setPrefSize(30, 30);

		listView.getItems().add(itemCell);
	}

	private static VBox getItemVBox(Node node, Pos pos, Priority priority, double prefWidth)
	{
		VBox box = new VBox(node);
		box.setAlignment(pos);
		box.setPrefWidth(prefWidth);

		VBox.setVgrow(box, priority);
		HBox.setHgrow(box, priority);

		return box;
	}
}