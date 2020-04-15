package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxView;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ShowErrorDialog extends IDialogBoxView
{
	@FXML private Label header;
	@FXML private TextArea content;

	private Exception exception;

	public ShowErrorDialog(Exception exception)
	{
		super(IDialogBoxType.ERROR);

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

		initialize();

		setUseButton(true, false);
		setContent(root);
		setTitle("Error");
		create();
	}

	private void initialize()
	{
		StringWriter message = new StringWriter();
		exception.printStackTrace(new PrintWriter(message));

		header.setText(exception.toString());
		content.setText(message.toString());
	}
}