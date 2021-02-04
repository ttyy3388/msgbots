package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.DeleteBotAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.dialog.DialogType;
import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;

public class DeleteBotDialog extends DialogWrap {
	private final ObservableMap<String, Object> nameSpace;
	private final FormLoader loader;
	private final AnchorPane root;

	@FXML private Label lblDeleteMessage;

	private final Button btnDelete;
	private final Button btnCancel;

	private final String name;

	public DeleteBotDialog(String name) {
		super(DialogType.EVENT);

		this.name = name;

		loader = new FormLoader("dialog", "delete-bot-dialog", this);
		nameSpace = loader.getNamespace();
		root = loader.getRoot();

		btnDelete = getActionButton();
		btnCancel = getCancelButton();

		lblDeleteMessage.setText("Are you sure you want to delete '" + name + "' ?");

		btnDelete.setText("Delete");
	}

	@Override
	public void open() {
		setContent(root);
		setTitle("Delete bot");
		create();
	}

	@Override
	public void action() {
		DeleteBotAction.execute(name);
	}
}