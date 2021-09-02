package org.beuwi.msgbots.view.app.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import org.beuwi.msgbots.actions.CreateProjectAction;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.view.gui.control.Button;
import org.beuwi.msgbots.view.gui.control.TextField;
import org.beuwi.msgbots.view.gui.dialog.YesOrNoDialog;
import org.beuwi.msgbots.view.gui.layout.VBox;
import org.beuwi.msgbots.view.util.ViewManager;

import java.io.File;

public class ChooseFileDialog extends YesOrNoDialog {
	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;
	private final VBox root;

	@FXML private TextField txfFilePath;
	@FXML private Button btnChooseFile;

	private final Button btnUpdate;
	private final Button btnCancel;

	private File file;

	public ChooseFileDialog(String description, String... extensions) {
		this(new ExtensionFilter(description, extensions));
	}

	public ChooseFileDialog(ExtensionFilter filter) {
		loader = new FormLoader();
		loader.setName("create-bot-dialog");
		loader.setController(this);
		loader.load();

		namespace = loader.getNamespace();
		root = loader.getRoot();

		btnUpdate = getActionButton();
		btnCancel = getCancelButton();

		btnUpdate.setText("Update");

		btnChooseFile.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose File");

			file = fileChooser.showOpenDialog(ViewManager.getStage());

			if (file != null) {
				txfFilePath.setText(file.getAbsolutePath());
			}
		});

		txfFilePath.textProperty().addListener(change -> {
			btnUpdate.setDisable(txfFilePath.getText().isEmpty());
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
		return true;
	}

	@Override
	protected boolean onClose() {
		return true;
	}
}
