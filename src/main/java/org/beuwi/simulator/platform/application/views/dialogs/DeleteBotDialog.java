package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import org.beuwi.simulator.managers.BotManager;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

public class DeleteBotDialog extends DialogBoxView
{
	@FXML private Label label;

	private Button btnDelete;
	private Button btnCancel;

	private String name;

	public DeleteBotDialog(String name)
	{
		super(DialogBoxType.ERROR);

		this.name = name;
	}

	public void display()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/DeleteBotDialog.fxml"));
		loader.setController(this);

		Region root = null;

		try
		{
			root = loader.load();
		}
		catch (Exception e)
		{
			new ShowErrorDialog(e).display();
		}

		initialize();
		setUseButton(true, false, false);
		setContent(root);
		setTitle("Delete");
		create();
	}

	private void initialize()
	{
		btnDelete = getOkButton();
		btnCancel = getNoButton();

		btnDelete.setText("Delete");
		btnCancel.setText("Cancel");

		label.setText("Delete bot '" + name + "'?");

		btnDelete.setOnAction(event ->
		{
			action();
		});

		setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				action();
			}
		});
	}

	private void action()
	{
		BotManager.delete(name);
	}
}