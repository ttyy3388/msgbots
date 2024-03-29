package org.beuwi.msgbots.view.app.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.actions.RenameProjectAction;
import org.beuwi.msgbots.view.gui.control.Button;
import org.beuwi.msgbots.view.gui.control.Label;
import org.beuwi.msgbots.view.gui.control.TextField;
import org.beuwi.msgbots.view.gui.dialog.YesOrNoDialog;
import org.beuwi.msgbots.view.gui.layout.VBox;

public class RenameBotDialog extends YesOrNoDialog {
	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;
	private final VBox root;

	// Script Name Text Field
	@FXML private Label lblRenameText;
	@FXML private TextField txfScriptName;

	private final Button btnRename;
	private final Button btnCancel;
	private final Label lblMessage;

	private final String name;

	public RenameBotDialog(String name) {
		loader = new FormLoader();
		loader.setName("rename-bot-dialog");
		loader.setController(this);
		loader.load();

		this.name = name;

		namespace = loader.getNamespace();
		root = loader.getRoot();

		btnRename = getActionButton();
		btnCancel = getCancelButton();
		lblMessage = getFooterLabel();

		btnRename.setText("Rename");
		btnRename.setDisable(true);
	}

	@Override
	protected boolean onOpen() {
		setContent(root);
		setTitle("Rename bot");
		return true;
	}

	@Override
	protected boolean onInit() {
		txfScriptName.setText(name);
		txfScriptName.textProperty().addListener(change -> {
			// 이름 변경을 안했으면 막음
			btnRename.setDisable(txfScriptName.getText().isEmpty() || (txfScriptName.getText().equals(name)));
		});
		lblRenameText.setText("Rename bot '" + name + "' and its usages to");
		return true;
	}

	@Override
	protected boolean onAction() {
		if (txfScriptName.getText().isEmpty()) {
			return false;
		}

		// 리네임 실패 시
		boolean result = RenameProjectAction.getInstance().execute(name, txfScriptName.getText());
		if (!result) {
			lblMessage.setText("Rename failed");
			return false;
		}
		return true;
	}

	@Override
	protected boolean onClose() {
		return true;
	}

	/* public static RenameBotDialog getInstance(String name) {
		if (instance == null) {
			instance = new RenameBotDialog();
		}
		RenameBotDialog.name = name;
		return instance;
	} */
}