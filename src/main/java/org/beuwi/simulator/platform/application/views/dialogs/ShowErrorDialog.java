package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ShowErrorDialog extends DialogBoxView
{
	@FXML private Label header;
	@FXML private TextArea content;

	private Exception exception;

	public ShowErrorDialog(Exception exception)
	{
		super(DialogBoxType.ERROR);

		this.exception = exception;
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

		setUseButton(true, false);
		setContent(root);
		setTitle("Error");
		create();
	}

	public void initialize()
	{
		StringWriter message = new StringWriter();
		exception.printStackTrace(new PrintWriter(message));

		header.setText(exception.toString());
		content.setText(message.toString());
	}
}