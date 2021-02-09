package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.platform.app.action.OpenDesktopAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenProgramTabAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenScriptTabAction;
import org.beuwi.msgbots.platform.app.view.dialogs.DeleteBotDialog;
import org.beuwi.msgbots.platform.app.view.dialogs.RenameBotDialog;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalConfigTab;
import org.beuwi.msgbots.platform.gui.layout.GridPane;
import org.beuwi.msgbots.platform.util.AllSVGIcons;
import org.beuwi.msgbots.setting.ScriptSettings;

// Bot Item
public class BotItem extends GridPane {
	private static final String DEFAULT_STYLE_CLASS = "bot-item";

	private final CheckBox chbIsCompiled = new CheckBox();
	private final Label lblBotName = new Label();
	private final Button btnCompile = new Button();
	
	// Power Switch
	private final ToggleSwitch tgbBotPower = new ToggleSwitch();
	
	private final ContextMenu menu;

	private BotView parent;

	{
		// GridPane.setHgrow(chbIsCompiled, Priority.ALWAYS);
		GridPane.setHgrow(lblBotName, Priority.ALWAYS);
		// GridPane.setHgrow(btnCompile, Priority.ALWAYS);
		// GridPane.setHgrow(tgbBotPower, Priority.ALWAYS);

		GridPane.setVgrow(chbIsCompiled, Priority.ALWAYS);
		GridPane.setVgrow(lblBotName, Priority.ALWAYS);
		GridPane.setVgrow(btnCompile, Priority.ALWAYS);
		GridPane.setVgrow(tgbBotPower, Priority.ALWAYS);

		GridPane.setHalignment(chbIsCompiled, HPos.CENTER);
		GridPane.setHalignment(lblBotName, HPos.LEFT);
		GridPane.setHalignment(btnCompile, HPos.CENTER);
		GridPane.setHalignment(tgbBotPower, HPos.CENTER);

		GridPane.setValignment(chbIsCompiled, VPos.CENTER);
		GridPane.setValignment(lblBotName, VPos.CENTER);
		GridPane.setValignment(btnCompile, VPos.CENTER);
		GridPane.setValignment(tgbBotPower, VPos.CENTER);

		setDefaultColumn(20, 0, 30, 45);
	}

	public BotItem(String name) {
		menu = new ContextMenu(
			new MenuItem("Compile", event -> {
				ScriptManager.initScript(name, true, false);
			}),
			new MenuItem("Power On / Off", event -> {
				tgbBotPower.setSelected(!getPower());
			}),
			new Separator(),
			// new MenuItem("Show Log", event -> OpenBotLogTabAction.execute(name)),
			new Separator(),
			new MenuItem("Show in Explorer", "Shift + Alt + R", event -> {
				OpenDesktopAction.execute(FileManager.getBotFolder(name));
			}),
			new Separator(),
			new MenuItem("Copy Path", "Ctrl + Alt + C", event -> {
				CopyStringAction.execute(FileManager.getBotScript(name).getPath());
			}),
			new MenuItem("Copy Relative Path", "Ctrl + Shift + C", event -> {
				CopyStringAction.execute(FileManager.getBotScript(name).getAbsolutePath());
			}),
			new Separator(),
			new MenuItem("Rename", event -> {
				OpenDialogBoxAction.execute(new RenameBotDialog(name));
			}),
			new MenuItem("Delete", event -> {
				OpenDialogBoxAction.execute(new DeleteBotDialog(name));
			}),
			new Separator(),
			new MenuItem("Settings", "Ctrl + ,", event -> {
				OpenProgramTabAction.execute(GlobalConfigTab.getRoot());
			})
		);
		menu.setNode(this);
		
		lblBotName.setText(name);
		chbIsCompiled.setDisable(true);

		addItem(chbIsCompiled,  0, 0);
		addItem(lblBotName,  1, 0);
		addItem(btnCompile, 2, 0);
		addItem(tgbBotPower,  3, 0);

		setOnMouseClicked(event -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {
				OpenScriptTabAction.execute(name);
			}
		});

		btnCompile.setGraphic(AllSVGIcons.get("Bot.Reload"));
		btnCompile.setOnAction(event -> {
			ScriptManager.initScript(name, true, false);
		});

		tgbBotPower.setSelected(ScriptSettings.get(name).getBoolean("tgbBotPower"));
		tgbBotPower.selectedProperty().addListener(event -> {
			ScriptSettings.get(name).setData("tgbBotPower", tgbBotPower.isSelected());
		});

		lblBotName.setPrefWidth(50);
		btnCompile.setPrefWidth(30);
		btnCompile.setPrefHeight(30);
		tgbBotPower.setPrefWidth(45);

		setId(name);
		setPrefHeight(35);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public BotView getView() {
		return parent;
	}

	public boolean getPower() {
		return tgbBotPower.isSelected();
	}

	public boolean isCompiled() {
		return chbIsCompiled.isSelected();
	}

	public void setPower(boolean tgbBotPower) {
		this.tgbBotPower.setSelected(tgbBotPower);
	}

	public void setCompiled(boolean compiled) {
		this.chbIsCompiled.setSelected(compiled);
	}

	public void setView(BotView parent) {
		this.parent = parent;
	}
}
