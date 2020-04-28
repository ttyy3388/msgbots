package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.application.actions.CreateBotAction;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxView;
import org.beuwi.simulator.utils.ResourceUtils;

public class CreateBotDialog extends IDialogBoxView
{
	@FXML private TextField txfScriptName;
	@FXML private CheckBox chkUnifiedParams;
	@FXML private CheckBox chkRuntimeError;

	private Button btnCreate;
	private Button btnCancel;

	public CreateBotDialog()
	{
		super(IDialogBoxType.APPLICATION);
	}

	public void display()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("CreateBotDialog"));
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

		this.initialize();
		this.setUseButton(true, true);
		this.setContent(root);
		this.setTitle("Create");
		this.create();
	}

	private void initialize()
	{
		btnCreate = getOKButton();
		btnCancel = getNOButton();

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

		this.close();
	}
}
