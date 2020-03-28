package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import org.beuwi.simulator.managers.BotManager;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

public class CreateBotDialog extends DialogBoxView
{
	@FXML private TextField txfScriptName;
	@FXML private CheckBox chkUseUnifiedParams;
	@FXML private CheckBox chkOffOnRuntimeError;

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
			new ShowErrorDialog(e).display();
		}

		initialize();
		setUseButton(true, true, false);
		setContent(root);
		setTitle("Create");
		create();
	}

	private void initialize()
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

		Platform.runLater(() ->
		{
			txfScriptName.requestFocus();
		});
	}

	private void action()
	{
		BotManager.create
		(
		    txfScriptName.getText(),
			null, false,
		    chkUseUnifiedParams.isSelected(),
		    chkOffOnRuntimeError.isSelected()
		);

		close();
	}
}
