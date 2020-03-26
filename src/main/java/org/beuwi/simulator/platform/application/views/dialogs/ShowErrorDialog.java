package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.window.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.window.dialog.DialogBoxView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowErrorDialog extends DialogBoxView implements Initializable
{
    @FXML private Label    header;
    @FXML private TextArea content;

	final Exception error;

	// Script Name
	public ShowErrorDialog(Exception error)
	{
		this.error = error;
	}

	public void display()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/ShowErrorDialog.fxml"));
		loader.setController(this);

		Region root = null;

		try
		{
			root = loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		setDialogContent(root);
		setDialogTitle("Error");
		setDialogType(DialogBoxType.DOCUMENT);
		createDialog();
	}

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // Error Message
        StringWriter message = new StringWriter();
        error.printStackTrace(new PrintWriter(message));

        header.setText(error.toString());
        content.setText(message.toString());
    }
}