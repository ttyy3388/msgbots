package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.CreateBotAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;
import org.beuwi.msgbots.platform.gui.layout.VBox;

public class CreateBotDialog extends DialogWrap {
	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;
	private final VBox root;

	@FXML private TextField txfScriptName;

	// Use Unified Parameters
	@FXML private CheckBox chkIsUnified;

	// Off On Runtime Error
	@FXML private CheckBox chkIsOffError;

	private final Button btnCreate;
	private final Button btnCancel;

	public CreateBotDialog() {
		loader = new FormLoader("dialog", "create-bot-dialog", this);
		namespace = loader.getNamespace();
		root = loader.getRoot();

		btnCreate = getActionButton();
		btnCancel = getCancelButton();

		btnCreate.setText("Create");

		txfScriptName.textProperty().addListener(change -> {
			btnCreate.setDisable(txfScriptName.getText().isEmpty());
		});

		Platform.runLater(() -> {
			txfScriptName.requestFocus();
		});
	}

	@Override
	protected void open() {
		setContent(root);
		setTitle("Create New Bot");
	}

	@Override
	protected boolean action() {
		if (txfScriptName.getText().isEmpty()) {
			return false;
		}

		CreateBotAction.execute(
			txfScriptName.getText(),
			chkIsUnified.isSelected(),
			chkIsOffError.isSelected()
		);

		return true;
	}
}
