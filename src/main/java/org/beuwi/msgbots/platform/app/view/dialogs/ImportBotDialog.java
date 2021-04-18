package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.CreateBotAction;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;
import org.beuwi.msgbots.platform.gui.layout.VBox;

import java.io.File;

public class ImportBotDialog extends YesOrNoDialog {
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

	public ImportBotDialog() {
		loader = new FormLoader("dialog", "import-bot-dialog", this);
		namespace = loader.getNamespace();
		root = loader.getRoot();

		btnImport = getActionButton();
		btnCancel = getCancelButton();

		btnImport.setText("Import");

		btnChooseScript.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JavaScript File", "*.js"));
			fileChooser.setTitle("Import Script");

			file = fileChooser.showOpenDialog(MainView.getStage());

			if (file != null) {
				txfScriptName.setText(file.getName());
			}
		});

		txfScriptName.textProperty().addListener(change -> {
			btnImport.setDisable(txfScriptName.getText().isEmpty());
		});

		Platform.runLater(() -> {
			btnChooseScript.requestFocus();
		});
	}

	@Override
	protected void open() {
		setContent(root);
		setTitle("Import Script");
	}

	@Override
	protected boolean action() {
		if (txfScriptName.getText().isEmpty()) {
			return false;
		}

		CreateBotAction.execute(
			txfScriptName.getText(),
			FileManager.read(file), true,
			chkIsUnified.isSelected(),
			chkIsOffError.isSelected()
		);

		return true;
	}
}
