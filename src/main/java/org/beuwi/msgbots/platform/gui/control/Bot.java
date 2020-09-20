package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.action.OpenDesktopAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenScriptTabAction;
import org.beuwi.msgbots.platform.app.view.dialog.RenameBotDialog;

// Bot Item
public class Bot extends GridPane
{
	public static final String DEFAULT_STYLE_CLASS = "bot-item";

	public static final int DEFAULT_HEIGHT = 35;

	private final CheckBox     check  = new CheckBox();     // Compiled Check Box
	private final TextField    field  = new TextField();    // Name Text Field
	private final Button       button = new Button();       // Compile Button
	private final ToggleButton power  = new ToggleButton(); // Power Switch

	private final ContextMenu menu;

	{
		// GridPane.setHgrow(check, Priority.ALWAYS);
		GridPane.setHgrow(field, Priority.ALWAYS);
		// GridPane.setHgrow(button, Priority.ALWAYS);
		// GridPane.setHgrow(power, Priority.ALWAYS);

		GridPane.setVgrow(check, Priority.ALWAYS);
		GridPane.setVgrow(field, Priority.ALWAYS);
		GridPane.setVgrow(button, Priority.ALWAYS);
		GridPane.setVgrow(power, Priority.ALWAYS);

		GridPane.setHalignment(check, HPos.CENTER);
		GridPane.setHalignment(field, HPos.LEFT);
		GridPane.setHalignment(button, HPos.CENTER);
		GridPane.setHalignment(power, HPos.CENTER);

		GridPane.setValignment(check, VPos.CENTER);
		GridPane.setValignment(field, VPos.CENTER);
		GridPane.setValignment(button, VPos.CENTER);
		GridPane.setValignment(power, VPos.CENTER);

		getColumnConstraints().add(new ColumnConstraints(20));
		getColumnConstraints().add(new ColumnConstraints());
		getColumnConstraints().add(new ColumnConstraints());
		getColumnConstraints().add(new ColumnConstraints());
	}

	public Bot(String name)
	{
		menu = new ContextMenu
		(
			new MenuItem("Compile"),
			new MenuItem("Power On / Off", event -> power.setSelected(!getPower())),
			new SeparatorMenuItem(),
			new MenuItem("Show Log"),
			new SeparatorMenuItem(),
			new MenuItem("Show in Explorer", "Shift + Alt + R", event -> OpenDesktopAction.execute(FileManager.getBotFolder(name))),
			new SeparatorMenuItem(),
			new MenuItem("Copy Path", "Ctrl + Alt + C"),
			new MenuItem("Copy Relative Path", "Ctrl + Shift + C"),
			new SeparatorMenuItem(),
			new MenuItem("Rename", event -> OpenDialogBoxAction.execute(new RenameBotDialog(name))),
			new MenuItem("Delete")
		);

		menu.setNode(this);

		field.setText(name);
		check.setDisable(true);
		field.setDisable(true);

		add(check,  0, 0);
		add(field,  1, 0);
		add(button, 2, 0);
		add(power,  3, 0);

		this.setOnMouseClicked(event ->
		{
			OpenScriptTabAction.execute(name);
		});

		field.setPrefWidth(50);
		field.setMaxWidth(Double.MAX_VALUE);
		// check.setPrefWidth(30);
		button.setPrefWidth(30);
		power.setPrefWidth(45);

		setId(name);
		setPrefHeight(DEFAULT_HEIGHT);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public boolean getPower()
	{
		return power.isSelected();
	}

	public boolean isCompiled()
	{
		return check.isSelected();
	}

	public void setPower(boolean power)
	{
		this.power.setSelected(power);
	}

	public void setCompiled(boolean compiled)
	{
		this.check.setSelected(compiled);
	}
}
