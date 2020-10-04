package org.beuwi.msgbots.platform.app.view.dialog;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.beuwi.msgbots.platform.app.action.CreateBotAction;
import org.beuwi.msgbots.platform.app.utils.FileUtils;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogBoxWrap;
import org.beuwi.msgbots.platform.util.ResourceUtils;

import java.io.File;

public class ImportScriptDialog extends DialogBoxWrap
{
	private ObservableMap<String, Object> nameSpace;

	private final FXMLLoader loader = new FXMLLoader();

	private AnchorPane root;

	@FXML private TextField txfScriptName;
	@FXML private Button btnScriptOpen;

	// Use Unified Parameters
	@FXML private CheckBox chkIsUnified;

	// Off On Runtime Error
	@FXML private CheckBox chkIsOffError;

	private final Button button;

	private File file;

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

		button = getOkButton();

		button.setText("Import");
		button.setDisable(true);
		button.setOnAction(event ->
		{
			this.action();
		});

		btnScriptOpen.setOnAction(event ->
		{
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JavaScript File", "*.js"));
			fileChooser.setTitle("Import Script");

			file = fileChooser.showOpenDialog(MainView.getStage());

			if (file != null)
			{
				txfScriptName.setText(file.getName());
			}
		});

		txfScriptName.textProperty().addListener(change ->
		{
			button.setDisable(txfScriptName.getText().isEmpty());
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
			FileUtils.read(file), true,
			chkIsUnified.isSelected(),
			chkIsOffError.isSelected()
		);
	}
}
