package org.beuwi.msgbots.platform.app.view.dialog;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.beuwi.msgbots.platform.app.action.RenameBotAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogBoxWrap;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class RenameBotDialog extends DialogBoxWrap
{
	private ObservableMap<String, Object> nameSpace;

	private final FXMLLoader loader = new FXMLLoader();

	private AnchorPane root;

	@FXML private Label 	lblBeforeName;
	@FXML private TextField txfAfterName;

	@FXML private Button btnRename;
	@FXML private Button btnCancel;

	private final String name;

	public RenameBotDialog(String name)
	{
		this.name = name;

		loader.setLocation(ResourceUtils.getForm("rename-bot-dialog"));
		loader.setController(this);

		try
		{
			loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		nameSpace = loader.getNamespace();
		root = loader.getRoot();

		btnRename = getOkButton();
		btnCancel = getNoButton();

		btnRename.setDisable(true);
		btnRename.setOnAction(event ->
		{
			this.action();
		});

		btnRename.setText("Rename");
		btnCancel.setText("Cancel");

		lblBeforeName.setText("Rename bot '" + name + "' ?");
		txfAfterName.setText(name);

		txfAfterName.textProperty().addListener(change ->
		{
			btnRename.setDisable(txfAfterName.getText().isEmpty());
		});

		setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				if (!btnRename.isDisable())
				{
					this.action();
				}
			}
		});

		Platform.runLater(() ->
		{
			txfAfterName.requestFocus();
		});
	}

	@Override
	public void open()
	{
		setContent(root);
		setTitle("Rename Bot");
		create();
	}

	@Override
	public void action()
	{
		RenameBotAction.execute(name, txfAfterName.getText());
	}
}