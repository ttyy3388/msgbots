package org.beuwi.msgbots.view.app.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;

import org.beuwi.msgbots.actions.CreateProjectAction;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.view.util.ViewManager;
import org.beuwi.msgbots.view.gui.control.Button;
import org.beuwi.msgbots.view.gui.control.TextField;
import org.beuwi.msgbots.view.gui.dialog.YesOrNoDialog;
import org.beuwi.msgbots.view.gui.layout.VBox;

import java.io.File;

public class ImportScriptDialog extends YesOrNoDialog {
	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;
	private final VBox root;

	@FXML private TextField txfScriptName;
	@FXML private Button btnChooseScript;

	// Use Unified Parameters
	@FXML private CheckBox chkIsUnified;

	// Off On Runtime Error
	@FXML private CheckBox chkIsOffError;

	private final Button btnImport;
	private final Button btnCancel;

	private File file;

	public ImportScriptDialog() {
		loader = new FormLoader();
		loader.setName("import-script-dialog");
		loader.setController(this);
		loader.load();

		namespace = loader.getNamespace();
		root = loader.getRoot();

		btnImport = getActionButton();
		btnCancel = getCancelButton();

		btnImport.setText("Import");

		btnChooseScript.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JavaScript File", "*.js"));
			fileChooser.setTitle("Import Script");

			file = fileChooser.showOpenDialog(ViewManager.getStage());

			if (file != null) {
				txfScriptName.setText(file.getName());
			}
		});

		txfScriptName.textProperty().addListener(change -> {
			btnImport.setDisable(txfScriptName.getText().isEmpty());
		});
	}

	@Override
	protected boolean onOpen() {
		setContent(root);
		setTitle("Import Script");
		return true;
	}

	@Override
	protected boolean onAction() {
		if (txfScriptName.getText().isEmpty()) {
			return false;
		}

		CreateProjectAction.getInstance().execute(
			txfScriptName.getText(),
			FileManager.read(file), true,
			chkIsUnified.isSelected(),
			chkIsOffError.isSelected()
		);

		return true;
	}

	@Override
	protected boolean onInit() {
		return true;
	}

	@Override
	protected boolean onClose() {
		return true;
	}
}
