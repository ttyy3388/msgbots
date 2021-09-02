package org.beuwi.msgbots.view.app.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.actions.DeleteProjectAction;
import org.beuwi.msgbots.view.gui.control.Button;
import org.beuwi.msgbots.view.gui.control.Label;
import org.beuwi.msgbots.view.gui.dialog.YesOrNoDialog;
import org.beuwi.msgbots.view.gui.type.DialogType;

public class DeleteBotDialog extends YesOrNoDialog {
	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;
	private final AnchorPane root;

	@FXML private Label lblMessage;

	private final Button btnDelete;
	private final Button btnCancel;

	private final String name;

	public DeleteBotDialog(String name) {
		super(DialogType.INFO);

		this.name = name;

		loader = new FormLoader();
		loader.setName("delete-bot-dialog");
		loader.setController(this);
		loader.load();

		namespace = loader.getNamespace();
		root = loader.getRoot();

		btnDelete = getActionButton();
		btnCancel = getCancelButton();

		btnDelete.setText("Delete");
	}

	@Override
	protected boolean onOpen() {
		setContent(root);
		setTitle("Delete Bot");
		return true;
	}

	@Override
	protected boolean onInit() {
		lblMessage.setText("Are you sure you want to delete '" + name + "' ?");
		return true;
	}

	@Override
	protected boolean onAction() {
		DeleteProjectAction.getInstance().execute(name);
		return true;
	}

	@Override
	protected boolean onClose() {
		return true;
	}
}