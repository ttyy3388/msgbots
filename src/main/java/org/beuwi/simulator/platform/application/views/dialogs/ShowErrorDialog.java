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

	final Exception error;

	public ShowErrorDialog(Exception error)
	{
		super(DialogBoxType.ERROR);

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
			// 여기서는 그냥 에러만 표시하도록 (무한 루프 방지)
			e.printStackTrace();
		}

		initialize();

		setUseButton(true, false, false);
		setContent(root);
		setTitle("Error");
		create();
	}

	private void initialize()
	{
		StringWriter message = new StringWriter();
		error.printStackTrace(new PrintWriter(message));

		header.setText(error.toString());
		content.setText(message.toString());
	}
}