package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.fxml.FXML;
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
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxView;

import java.io.File;

public class ImportScriptDialogBox extends IDialogBoxView
{
	@FXML private TextField txfScriptName;
	@FXML private Button    btnScriptOpen;
	@FXML private CheckBox  chkUnifiedParams;
	@FXML private CheckBox  chkRuntimeError;

	private Button btnImport;
	private Button btnCancel;

	private File file;

	public ImportScriptDialogBox()
	{
		super(IDialogBoxType.APPLICATION);
	}

	public void display()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/ImportScriptDialog.fxml"));
		loader.setController(this);

		Region root = null;

		try
		{
			root = loader.load();
		}
		catch (Exception e)
		{
			new ShowErrorDialogBox(e).display();
		}

		initialize();

		setUseButton(true, true);
		setContent(root);
		setTitle("Import");
		create();
	}

	private void initialize()
	{
		btnImport = getOkButton();
		btnCancel = getNoButton();

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

		setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				if (!btnImport.isDisable())
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
			file.getName(),
			FileManager.read(file), true,
			chkUnifiedParams.isSelected(),
			chkRuntimeError.isSelected()
		);

		close();
	}
}