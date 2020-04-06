package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.application.actions.CreateBotAction;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

public class CreateBotDialog
{
	private static ObservableMap<String, Object> nameSpace;
	private static DialogBoxView dialog;

	private static TextField txfScriptName;
	private static CheckBox chkUnifiedParams;
	private static CheckBox chkRuntimeError;

	private static Button btnCreate;
	private static Button btnCancel;

	public static void initialize()
	{
		dialog = new DialogBoxView(DialogBoxType.NONE);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CreateBotDialog.class.getResource("/forms/CreateBotDialog.fxml"));
		loader.setController(null);

		Region root = null;

		try
		{
			root = loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		nameSpace = loader.getNamespace();

		txfScriptName    = (TextField) nameSpace.get("txfScriptName");
		chkUnifiedParams = (CheckBox) nameSpace.get("chkUnifiedParams");
		chkRuntimeError  = (CheckBox) nameSpace.get("chkRuntimeError");

		btnCreate = dialog.getOkButton();
		btnCancel = dialog.getNoButton();

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

		dialog.setOnKeyPressed(event ->
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

		dialog.setUseButton(true, true);
		dialog.setContent(root);
		dialog.setTitle("Create");
		dialog.create();
	}

	public static void display()
	{
		dialog.show();
	}

	private static void action()
	{
		CreateBotAction.update
		(
		    txfScriptName.getText(),
			null, false,
		    chkUnifiedParams.isSelected(),
			chkRuntimeError.isSelected()
		);

		dialog.close();
	}
}
