package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.compiler.api.Api;
import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.action.OpenDesktopAction;
import org.beuwi.msgbots.platform.app.utils.Settings;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenScriptTabAction;
import org.beuwi.msgbots.platform.app.view.dialog.RenameBotDialog;

// Bot Item
public class Bot extends GridPane
{
	public static final String DEFAULT_STYLE_CLASS = "bot-item";

	public static final int DEFAULT_HEIGHT = 35;

	private final CheckBox     check  = new CheckBox();     // Compiled Check Box
	private final Label   	   label  = new Label();    	// Name Text Field
	private final Button       button = new Button();       // Compile Button
	private final ToggleButton power  = new ToggleButton(); // Power Switch

	private final ContextMenu menu;

	private BotView parent;

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
		getColumnConstraints().add(new ColumnConstraints(30));
		getColumnConstraints().add(new ColumnConstraints(45));
	}

	public Bot(String name)
	{
		menu = new ContextMenu
		(
			new MenuItem("Compile", event -> ScriptManager.setInitialize(name, true, false)),
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

		label.setText(name);
		check.setDisable(true);

		add(check,  0, 0);
		add(label,  1, 0);
		add(button, 2, 0);
		add(power,  3, 0);

		this.setOnMouseClicked(event ->
		{
			if (event.getButton().equals(MouseButton.PRIMARY))
			{
				OpenScriptTabAction.execute(name);
			}
		});

		button.setOnAction(event ->
		{
			ScriptManager.setInitialize(name, true, false);
		});

		check.setSelected(Api.isCompiled(name));
		power.setSelected(Api.isOn(name));
		power.getSelectedProperty().addListener(change ->
		{
			Settings.getScriptSetting(name).putBoolean("power", power.isSelected());
		});

		label.setPrefWidth(50);
		label.setMaxWidth(Double.MAX_VALUE);
		// check.setPrefWidth(30);
		button.setPrefWidth(30);
		button.setPrefHeight(30);
		power.setPrefWidth(45);

		setId(name);
		setPrefHeight(DEFAULT_HEIGHT);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public BotView getView()
	{
		return parent;
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

	public void setView(BotView parent)
	{
		this.parent = parent;
	}
}
