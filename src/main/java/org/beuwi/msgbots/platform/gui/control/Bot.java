package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.action.OpenDesktopAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenBotLogTabAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenProgramTabAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenScriptTabAction;
import org.beuwi.msgbots.platform.app.view.dialogs.RenameBotDialog;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalConfigTab;
import org.beuwi.msgbots.platform.gui.layout.GridPanel;

// Bot Item
public class Bot extends GridPanel
{
	private static final String DEFAULT_STYLE_CLASS = "bot";

	private final CheckBox     check  = new CheckBox();     // Compiled Check Box
	private final Label   	   label  = new Label();    	// Name Text
	private final Button       button = new Button();       // Compile Button
	private final ToggleSwitch power  = new ToggleSwitch(); // Power Switch

	private final ContextMenu menu;

	private BotView parent;

	{
		// GridPanel.setHgrow(check, Priority.ALWAYS);
		GridPanel.setHgrow(label, Priority.ALWAYS);
		// GridPanel.setHgrow(button, Priority.ALWAYS);
		// GridPanel.setHgrow(power, Priority.ALWAYS);

		GridPanel.setVgrow(check, Priority.ALWAYS);
		GridPanel.setVgrow(label, Priority.ALWAYS);
		GridPanel.setVgrow(button, Priority.ALWAYS);
		GridPanel.setVgrow(power, Priority.ALWAYS);

		GridPanel.setHalignment(check, HPos.CENTER);
		GridPanel.setHalignment(label, HPos.LEFT);
		GridPanel.setHalignment(button, HPos.CENTER);
		GridPanel.setHalignment(power, HPos.CENTER);

		GridPanel.setValignment(check, VPos.CENTER);
		GridPanel.setValignment(label, VPos.CENTER);
		GridPanel.setValignment(button, VPos.CENTER);
		GridPanel.setValignment(power, VPos.CENTER);

		setDefaultColumn(20, 0, 30, 45);
	}

	public Bot(String name)
	{
		menu = new ContextMenu
		(
			new Menu("Compile", event -> ScriptManager.initBot(name, true, false)),
			new Menu("Power On / Off", event -> power.setSelected(!getPower())),
			new Separator(),
			new Menu("Show Log", event -> OpenBotLogTabAction.execute(name)),
			new Separator(),
			new Menu("Show in Explorer", "Shift + Alt + R", event -> OpenDesktopAction.execute(FileManager.getBotFolder(name))),
			new Separator(),
			new Menu("Copy Path", "Ctrl + Alt + C"),
			new Menu("Copy Relative Path", "Ctrl + Shift + C"),
			new Separator(),
			new Menu("Rename" , event -> OpenDialogBoxAction.execute(new RenameBotDialog(name))),
			new Menu("Delete"),
			new Separator(),
			new Menu("Settings", "Ctrl + ,", event -> OpenProgramTabAction.execute(GlobalConfigTab.getRoot()))
		);

		menu.setNode(this);

		label.setText(name);
		check.setDisable(true);

		addItem(check,  0, 0);
		addItem(label,  1, 0);
		addItem(button, 2, 0);
		addItem(power,  3, 0);

		button.setOnAction(event ->
		{
			ScriptManager.initBot(name, true, false);
		});

		setOnMouseClicked(event ->
		{
			if (event.getButton().equals(MouseButton.PRIMARY))
			{
				OpenScriptTabAction.execute(name);
			}
		});

		label.setPrefWidth(50);
		button.setPrefWidth(30);
		button.setPrefHeight(30);
		power.setPrefWidth(45);

		setId(name);
		setPrefHeight(35);
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
