package org.beuwi.msgbots.program.app.view.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.program.app.action.RenameProjectAction;
import org.beuwi.msgbots.program.gui.control.Button;
import org.beuwi.msgbots.program.gui.control.Label;
import org.beuwi.msgbots.program.gui.control.TextField;
import org.beuwi.msgbots.program.gui.dialog.YesOrNoDialog;
import org.beuwi.msgbots.program.gui.layout.VBox;

public class RenameBotDialog extends YesOrNoDialog {
	private static RenameBotDialog instance = null;

	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;
	private final VBox root;

	// Script Name Text Field
	@FXML private Label lblRenameText;
	@FXML private TextField txfScriptName;

	private final Button btnRename;
	private final Button btnCancel;
	private final Label lblMessage;

	private static String name;

	public RenameBotDialog() {
		loader = new FormLoader();
		loader.setName("rename-bot-dialog");
		loader.setController(this);
		loader.load();

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
		focusNode(txfScriptName);
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

	public static RenameBotDialog getInstance(String name) {
		if (instance == null) {
			instance = new RenameBotDialog();
		}
		RenameBotDialog.name = name;
		return instance;
	}
}