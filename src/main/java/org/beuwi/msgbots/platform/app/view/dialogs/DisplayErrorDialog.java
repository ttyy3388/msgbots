package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.control.TextArea;
import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;
import org.beuwi.msgbots.platform.gui.dialog.DialogType;
import org.beuwi.msgbots.platform.gui.layout.VBox;

import java.io.PrintWriter;
import java.io.StringWriter;

public class DisplayErrorDialog extends DialogWrap {
	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;
	private final VBox root;

	@FXML private Label lblErrorHeader;
	@FXML private TextArea txaErrorMessage;

	private final Button btnCopy;
	private final Button btnClose;

	public DisplayErrorDialog(Throwable error) {
		super(DialogType.ERROR);

		loader = new FormLoader("dialog", "display-error-dialog", this);
		namespace = loader.getNamespace();
		root = loader.getRoot();

		btnCopy = getActionButton();
		btnClose = getCancelButton();

		btnCopy.setText("Copy And Close");
		btnClose.setText("Close");

		// Error To String
		StringWriter message = new StringWriter();
		error.printStackTrace(new PrintWriter(message));

		lblErrorHeader.setText(error.toString());
		txaErrorMessage.setText(message.toString());

		/* Platform.runLater(() -> {
			txfErrorMessage.requestFocus();
		}); */
	}

	@Override
	protected void open() {
		setContent(root);
		setTitle("Error");
	}

	@Override
	protected void action() {
		/* if (txfScriptName.getText().isEmpty()) {
			return ;
		} */

		CopyStringAction.execute(txaErrorMessage.getText());
	}
}