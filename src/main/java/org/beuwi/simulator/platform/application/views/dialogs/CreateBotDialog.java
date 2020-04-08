package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.application.actions.CreateBotAction;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

public class CreateBotDialog extends DialogBoxView
{
	@FXML private TextField txfScriptName;
	@FXML private CheckBox chkUnifiedParams;
	@FXML private CheckBox chkRuntimeError;

	private Button btnCreate;
	private Button btnCancel;

	public CreateBotDialog()
	{
		super(DialogBoxType.NONE);
	}

	public void display()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/CreateBotDialog.fxml"));
		loader.setController(this);

		Region root = null;

		try
		{
			root = loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		setUseButton(true, true);
		setContent(root);
		setTitle("Create");
		create();
	}

	public void initialize()
	{
		btnCreate = getOkButton();
		btnCancel = getNoButton();

		btnCreate.setDisable(true);
		btnCreate.setText("Create");
		btnCancel.setText("Cancel");

		btnCreate.setOnAction(event ->
		{
			action();
		});

		txfScriptName.textProperty().addListener((observable, oldString, newString) ->
		{
			btnCreate.setDisable(newString.isEmpty());
		});

		setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				if (!btnCreate.isDisable())
				{
					action();
				}
			}
		});
	}

	private void action()
	{
		CreateBotAction.update
		(
		    txfScriptName.getText(),
			null, false,
		    chkUnifiedParams.isSelected(),
			chkRuntimeError.isSelected()
		);

		close();
	}
}
