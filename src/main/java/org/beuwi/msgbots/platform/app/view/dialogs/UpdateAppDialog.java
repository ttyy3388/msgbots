package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;
import org.beuwi.msgbots.platform.gui.layout.VBox;
import org.beuwi.msgbots.platform.util.ReleasedInfo;
import org.beuwi.msgbots.setting.GlobalSettings;

import java.io.File;

public class UpdateAppDialog extends DialogWrap {
	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;
	private final VBox root;

	@FXML private Label lblNewVersion;
	@FXML private Label lblOldVersion;

	private final Button btnImport;
	private final Button btnCancel;

	private File file;

	public UpdateAppDialog() {
		loader = new FormLoader("dialog", "update-app-dialog", this);
		namespace = loader.getNamespace();
		root = loader.getRoot();

		btnImport = getActionButton();
		btnCancel = getCancelButton();

		btnImport.setText("Update");

		lblNewVersion.setText(
			lblNewVersion.getText().replace("{}", ReleasedInfo.getVersionName())
		);
		lblOldVersion.setText(
			lblOldVersion.getText().replace("{}", GlobalSettings.getString("program:version_name"))
		);
	}

	@Override
	public void open() {
		setContent(root);
		setTitle("Update Available");
		create();
	}

	@Override
	public void action() {

	}
}
