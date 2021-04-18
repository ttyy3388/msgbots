package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.RenameBotAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;
import org.beuwi.msgbots.platform.gui.layout.VBox;

public class RenameBotDialog extends YesOrNoDialog {
	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;
	private final VBox root;

	// Script Name Text Field
	@FXML private Label lblRenameText;
	@FXML private TextField txfScriptName;

	private final Button btnRename;
	private final Button btnCancel;

	private final String name;

	public RenameBotDialog(String name) {
		this.name = name;

		loader = new FormLoader("dialog", "rename-bot-dialog", this);
		namespace = loader.getNamespace();
		root = loader.getRoot();

		btnRename = getActionButton();
		btnCancel = getCancelButton();

		btnRename.setText("Rename");
		btnRename.setDisable(true);

		txfScriptName.setText(name);
		txfScriptName.textProperty().addListener(change -> {
			// 이름 변경을 안했으면 막음
			btnRename.setDisable(txfScriptName.getText().isEmpty() || (txfScriptName.getText().equals(name)));
		});

		lblRenameText.setText("Rename bot '" + name + "' and its usages to");

		Platform.runLater(() -> {
			txfScriptName.requestFocus();
		});
	}

	@Override
	protected void open() {
		setContent(root);
		setTitle("Rename bot");
	}

	@Override
	protected boolean action() {
		if (txfScriptName.getText().isEmpty()) {
			return false;
		}

		RenameBotAction.execute(name, txfScriptName.getText());

		return true;
	}
}