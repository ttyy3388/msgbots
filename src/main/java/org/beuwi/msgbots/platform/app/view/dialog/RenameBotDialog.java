package org.beuwi.msgbots.platform.app.view.dialog;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.beuwi.msgbots.platform.app.action.RenameBotAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogBoxWrap;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class RenameBotDialog extends DialogBoxWrap
{
	private ObservableMap<String, Object> nameSpace;

	private final FXMLLoader loader = new FXMLLoader();

	private AnchorPane root;

	// Script Name Text Field
	@FXML private TextField field;

	private final Button button;
	private final String name;

	public RenameBotDialog(String name)
	{
		this.name = name;

		loader.setLocation(ResourceUtils.getForm("rename-bot-dialog"));
		loader.setController(this);

		try
		{
			loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		nameSpace = loader.getNamespace();
		root = loader.getRoot();

		button = getOkButton();

		button.setText("Rename");
		button.setDisable(true);
		button.setOnAction(event ->
		{
			this.action();
		});

		field.setText(name);
		field.textProperty().addListener(change ->
		{
			button.setDisable(field.getText().isEmpty() || field.getText().equals(name));
		});

		setUseButton(true, false);
		setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				if (!button.isDisable())
				{
					this.action();
				}
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