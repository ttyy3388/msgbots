package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.RenameBotAction;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogBoxWrap;

public class RenameBotDialog extends DialogBoxWrap
{
	private final ObservableMap<String, Object> nameSpace;

	private final FormLoader loader;

	private final AnchorPane root;

	// Script Name Text Field
	@FXML private TextField field;

	private final String name;

	public RenameBotDialog(String name)
	{
		this.name = name;

		loader = new FormLoader("dialog", "rename-bot-dialog", this);
		nameSpace = loader.getNamespace();
		root = loader.getRoot();

		field.setText(name);

		setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{

			}
		});

		Platform.runLater(() ->
		{
			field.requestFocus();
		});
	}

	@Override
	public void open()
	{
		setContent(root);
		setTitle("Rename Bot");
		create();
	}

	@Override
	public void action()
	{
		RenameBotAction.execute(name, field.getText());
	}
}