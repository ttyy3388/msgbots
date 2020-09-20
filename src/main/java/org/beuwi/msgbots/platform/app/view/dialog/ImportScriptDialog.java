package org.beuwi.msgbots.platform.app.view.dialog;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.beuwi.msgbots.platform.app.action.CreateBotAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogBoxWrap;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class ImportScriptDialog extends DialogBoxWrap
{
	private ObservableMap<String, Object> nameSpace;

	private final FXMLLoader loader = new FXMLLoader();

	private AnchorPane root;

	@FXML private TextField txfScriptName;

	// Use Unified Parameters
	@FXML private CheckBox chkIsUnified;

	// Off On Runtime Error
	@FXML private CheckBox chkIsOffError;

	@FXML private Button btnImport;
	@FXML private Button btnCancel;

	public ImportScriptDialog()
	{
		loader.setLocation(ResourceUtils.getForm("import-script-dialog"));
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

		btnImport = getOkButton();
		btnCancel = getNoButton();

		btnImport.setText("Import");
		btnCancel.setText("Cancel");

		btnImport.setDisable(true);
		btnImport.setOnAction(event ->
		{
			this.action();
		});

		txfScriptName.textProperty().addListener(change ->
		{
			btnImport.setDisable(txfScriptName.getText().isEmpty());
		});

		setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				if (!btnImport.isDisable())
				{
					this.action();
				}
			}
		});

		Platform.runLater(() ->
		{
			txfScriptName.requestFocus();
		});
	}

	@Override
	public void open()
	{
		setContent(root);
		setTitle("Import Script");
		create();
	}

	@Override
	public void action()
	{
		CreateBotAction.execute
		(
			txfScriptName.getText(),
			chkIsUnified.isSelected(),
			chkIsOffError.isSelected()
		);
	}
}
