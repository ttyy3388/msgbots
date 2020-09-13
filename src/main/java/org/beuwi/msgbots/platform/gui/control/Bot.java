package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.app.view.actions.OpenScriptTabAction;

// Bot Item
public class Bot extends GridPane
{
	public static final String DEFAULT_STYLE_CLASS = "bot-item";

	public static final int DEFAULT_HEIGHT = 35;

	final CheckBox     check  = new CheckBox();     // Compiled Check Box
	final Label        label  = new Label();        // Name Label
	final Button       button = new Button();       // Compile Button
	final ToggleButton power  = new ToggleButton(); // Power Switch

	final ContextMenu menu = new ContextMenu
	(
		new MenuItem("Compile"),
		new MenuItem("Power On / Off"),
		new SeparatorMenuItem(),
		new MenuItem("Show Log"),
		new SeparatorMenuItem(),
		new MenuItem("Show in Explorer", "Shift + Alt + R"),
		new SeparatorMenuItem(),
		new MenuItem("Copy Path", "Ctrl + Alt + C"),
		new MenuItem("Copy Relative Path", "Ctrl + Shift + C"),
		new SeparatorMenuItem(),
		new MenuItem("Rename"),
		new MenuItem("Delete")
	);

	{
		// GridPane.setHgrow(check, Priority.ALWAYS);
		GridPane.setHgrow(label, Priority.ALWAYS);
		// GridPane.setHgrow(button, Priority.ALWAYS);
		// GridPane.setHgrow(power, Priority.ALWAYS);

		GridPane.setVgrow(check, Priority.ALWAYS);
		GridPane.setVgrow(label, Priority.ALWAYS);
		GridPane.setVgrow(button, Priority.ALWAYS);
		GridPane.setVgrow(power, Priority.ALWAYS);

		GridPane.setHalignment(check, HPos.CENTER);
		GridPane.setHalignment(label, HPos.LEFT);
		GridPane.setHalignment(button, HPos.CENTER);
		GridPane.setHalignment(power, HPos.CENTER);

		GridPane.setValignment(check, VPos.CENTER);
		GridPane.setValignment(label, VPos.CENTER);
		GridPane.setValignment(button, VPos.CENTER);
		GridPane.setValignment(power, VPos.CENTER);

		getColumnConstraints().add(new ColumnConstraints(20));
		getColumnConstraints().add(new ColumnConstraints());
		getColumnConstraints().add(new ColumnConstraints());
		getColumnConstraints().add(new ColumnConstraints());
	}

	public Bot(String name)
	{
		menu.setNode(this);

		label.setText(name);
		check.setDisable(true);

		add(check,  0, 0);
		add(label,  1, 0);
		add(button, 2, 0);
		add(power,  3, 0);

		this.setOnMouseClicked(event ->
		{
			OpenScriptTabAction.execute(name);
		});

		// check.setPrefWidth(30);
		button.setPrefWidth(30);
		power.setPrefWidth(45);

		setId(name);
		setPrefHeight(DEFAULT_HEIGHT);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}
