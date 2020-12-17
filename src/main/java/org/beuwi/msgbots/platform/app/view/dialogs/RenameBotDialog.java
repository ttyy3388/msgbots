package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.RenameBotAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogBoxWrap;

public class RenameBotDialog extends DialogBoxWrap
{
	private final ObservableMap<String, Object> nameSpace;

	private final FormLoader loader;

	private final AnchorPane root;

	// Script Name Text Field
	@FXML private TextField txfScriptName;

	private final Button btnRename;
	private final Button btnCancel;

	private final String name;

	public RenameBotDialog(String name)
	{
		this.name = name;

		loader = new FormLoader("dialog", "rename-bot-dialog", this);
		nameSpace = loader.getNamespace();
		root = loader.getRoot();

		btnRename = getActionButton();
		btnCancel = getCancelButton();

		btnRename.setText("Rename");

		txfScriptName.setText(name);
		txfScriptName.getTextProperty().addListener(change ->
		{
			btnRename.setDisable(txfScriptName.getText().isEmpty());
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
		setTitle("Rename Bot");
		create();
	}

	@Override
	public void action()
	{
		if (txfScriptName.getText().isEmpty())
		{
			return ;
		}

		RenameBotAction.execute(name, txfScriptName.getText());
	}
}