package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.action.DeleteProjectAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.dialog.YesOrNoDialog;
import org.beuwi.msgbots.platform.gui.type.DialogType;

public class DeleteBotDialog extends YesOrNoDialog {
	private static DeleteBotDialog instance = null;

	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;
	private final AnchorPane root;

	@FXML private Label lblMessage;

	private final Button btnDelete;
	private final Button btnCancel;

	private static String name;

	private DeleteBotDialog() {
		super(DialogType.INFO);

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

	public static DeleteBotDialog getInstance(String name) {
		if (instance == null) {
			instance = new DeleteBotDialog();
		}
		DeleteBotDialog.name = name;
		return instance;
	}
}