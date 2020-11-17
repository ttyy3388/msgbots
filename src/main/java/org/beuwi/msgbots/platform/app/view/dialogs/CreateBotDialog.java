package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.CreateBotAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogBoxWrap;
import org.beuwi.msgbots.platform.gui.control.VBox;

public class CreateBotDialog extends DialogBoxWrap
{
	private final ObservableMap<String, Object> nameSpace;

	private final FormLoader loader;

	private final VBox root;

	@FXML private TextField txfScriptName;

	// Use Unified Parameters
	@FXML private CheckBox chkIsUnified;

	// Off On Runtime Error
	@FXML private CheckBox chkIsOffError;

	private final Button btnCreate;
	private final Button btnCancel;

	public CreateBotDialog()
	{
		loader = new FormLoader("dialog", "create-bot-dialog", this);
		nameSpace = loader.getNamespace();
		root = loader.getRoot();

		btnCreate = getActionButton();
		btnCancel = getCancelButton();

		btnCreate.setText("Create");

		txfScriptName.getTextProperty().addListener(change ->
		{
			btnCreate.setDisable(txfScriptName.getText().isEmpty());
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
		setTitle("Create New Bot");
		create();
	}

	@Override
	public void action()
	{
		if (txfScriptName.getText().isEmpty())
		{
			return ;
		}

		CreateBotAction.execute
		(
			txfScriptName.getText(),
			chkIsUnified.isSelected(),
			chkIsOffError.isSelected()
		);
	}
}
