package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.dialog.DialogBoxType;
import org.beuwi.msgbots.platform.gui.dialog.DialogBoxWrap;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class CreateBotDialog extends DialogBoxWrap
{
	@FXML private TextField txfScriptName;
	@FXML private CheckBox chkUnifiedParams;
	@FXML private CheckBox chkRuntimeError;

	private static Button btnCreate;
	private static Button btnCancel;

    private static Pane root;

	public CreateBotDialog()
	{
		super(DialogBoxType.APPLICATION);
	}

	@Override
	public void init()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("create-bot-dialog"));
		loader.setController(this);

		try
        {
            root = loader.load();
        }
		catch (Exception e)
        {
            e.printStackTrace();
        }

        btnCreate = getOKButton();
        btnCancel = getNOButton();

        btnCreate.setDisable(true);
        btnCreate.setText("Create");
        btnCancel.setText("Cancel");

        btnCreate.setOnAction(event ->
        {

        });

        txfScriptName.textProperty().addListener((observable, oldString, newString) ->
        {
            btnCreate.setDisable(newString.isEmpty());
        });

        setOnKeyPressed(event ->
        {
            if (event.getCode().equals(KeyCode.ENTER))
            {
                if (!btnCreate.isDisable())
                {

                }
            }
        });

        Platform.runLater(() -> txfScriptName.requestFocus());

        setContent(root);
        setTitle("Create a new bot");
    }

	@Override
	public void open()
    {
        create();
	}
}
