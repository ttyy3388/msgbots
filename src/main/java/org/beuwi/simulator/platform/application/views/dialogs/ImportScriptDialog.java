package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.actions.CreateBotAction;
import org.beuwi.simulator.platform.application.views.MainWindowView;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

import java.io.File;

public class ImportScriptDialog
{
	private static ObservableMap<String, Object> nameSpace;
	private static DialogBoxView dialog;

	private static TextField txfScriptName;
	private static Button    btnScriptOpen;
	private static CheckBox  chkUnifiedParams;
	private static CheckBox  chkRuntimeError;

	private static Button btnImport;
	private static Button btnCancel;

	private static File file;

	public static void initialize()
	{
		dialog = new DialogBoxView(DialogBoxType.NONE);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImportScriptDialog.class.getResource("/forms/ImportScriptDialog.fxml"));
		loader.setController(null);

		Region root = null;

		try
		{
			root = loader.load();
		}
		catch (Exception e)
		{
			ShowErrorDialog.display(e);
		}

		nameSpace = loader.getNamespace();

		txfScriptName    = (TextField) nameSpace.get("txfScriptName");
		btnScriptOpen    = (Button) nameSpace.get("btnScriptOpen");;
		chkUnifiedParams = (CheckBox) nameSpace.get("chkUnifiedParams");;
		chkRuntimeError  = (CheckBox) nameSpace.get("chkRuntimeError");;

		btnImport = dialog.getOkButton();
		btnCancel = dialog.getNoButton();

		btnImport.setDisable(true);
		btnImport.setText("Import");
		btnCancel.setText("Cancel");

		btnScriptOpen.setOnAction(event ->
		{
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JavaScript File", "*.js"));
			fileChooser.setTitle("Import Script");

			file = fileChooser.showOpenDialog(MainWindowView.getStage());

			if (file != null)
			{
				txfScriptName.setText(file.getName());
				btnImport.setDisable(false);
			}
		});

		btnImport.setOnAction(event ->
		{
			action();
		});

		txfScriptName.textProperty().addListener((observable, oldString, newString) ->
		{
			btnImport.setDisable(newString.isEmpty());
		});

		dialog.setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				if (!btnImport.isDisable())
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
		dialog.setTitle("Import");
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
			file.getName(),
			FileManager.read(file), true,
			chkUnifiedParams.isSelected(),
			chkRuntimeError.isSelected()
		);

		dialog.close();
	}
}
