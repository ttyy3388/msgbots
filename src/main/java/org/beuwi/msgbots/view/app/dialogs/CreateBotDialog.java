package org.beuwi.msgbots.view.app.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.actions.CreateProjectAction;
import org.beuwi.msgbots.view.gui.control.Button;
import org.beuwi.msgbots.view.gui.control.TextField;
import org.beuwi.msgbots.view.gui.dialog.YesOrNoDialog;
import org.beuwi.msgbots.view.gui.layout.VBox;

public class CreateBotDialog extends YesOrNoDialog {
	private static CreateBotDialog instance = null;

	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;
	private final VBox root;

	@FXML private TextField txfScriptName;
	@FXML private CheckBox chkIsUnified; // Use Unified Parameters
	@FXML private CheckBox chkIsOffError; // Off On Runtime Error

	private final Button btnCreate;
	private final Button btnCancel;

	private CreateBotDialog() {
		loader = new FormLoader();
		loader.setName("create-bot-dialog");
		loader.setController(this);
		loader.load();

		namespace = loader.getNamespace();
		root = loader.getRoot();

		btnCreate = getActionButton();
		btnCancel = getCancelButton();

		btnCreate.setText("Create");

		txfScriptName.textProperty().addListener(change -> {
			btnCreate.setDisable(txfScriptName.getText().isEmpty());
		});
	}

	@Override
	protected boolean onOpen() {
		setContent(root);
		setTitle("Create New Bot");
		return true;
	}

	@Override
	protected boolean onInit() {
		return true;
	}

	@Override
	protected boolean onAction() {
		String name = txfScriptName.getText();
		if (name == null || name.isEmpty()) {
			return false;
		}

		CreateProjectAction.getInstance().execute(
			txfScriptName.getText(),
			chkIsUnified.isSelected(),
			chkIsOffError.isSelected()
		);

		return true;
	}

	@Override
	protected boolean onClose() {
		return true;
	}

	public static CreateBotDialog getInstance() {
		if (instance == null) {
			instance = new CreateBotDialog();
		}
		return instance;
	}
}
