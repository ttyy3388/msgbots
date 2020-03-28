package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import org.beuwi.simulator.managers.BotManager;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

public class RenameBotDialog extends DialogBoxView
{
    @FXML private Label label;
	@FXML private TextField field;

	private Button btnRename;
	private Button btnCancel;

	private String name;

	public RenameBotDialog(String name)
	{
		super(DialogBoxType.EVENT);

		this.name = name;
	}

    public void display()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/RenameBotDialog.fxml"));
        loader.setController(this);

        Region root = null;

        try
        {
            root = loader.load();
        }
        catch (Exception e)
        {
            new ShowErrorDialog(e).display();
        }

        initialize();
        setUseButton(true, true, false);
        setContent(root);
        setTitle("Rename");
        create();
    }

    private void initialize()
    {
        btnRename = getOkButton();
        btnCancel = getNoButton();

        btnRename.setText("Rename");
        btnCancel.setText("Cancel");

        label.setText("Rename bot '" + name + "'?");
        field.setText(name);

        btnRename.setOnAction(event ->
        {
            action();
        });

        field.textProperty().addListener((observable, oldString, newString) ->
        {
            btnRename.setDisable(newString.isEmpty());
        });

        setOnKeyPressed(event ->
        {
            if (event.getCode().equals(KeyCode.ENTER))
            {
                if (!btnRename.isDisable())
                {
                    action();
                }
            }
        });

        Platform.runLater(() -> field.requestFocus());
    }

    private void action()
    {
        BotManager.rename(name, field.getText());
    }
}