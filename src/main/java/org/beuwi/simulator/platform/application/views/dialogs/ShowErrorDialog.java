package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ShowErrorDialog
{
	private static ObservableMap<String, Object> nameSpace;

	private static DialogBoxView dialog;

	private static Label header;
	private static TextArea content;

	public static void initialize()
	{
		dialog = new DialogBoxView(DialogBoxType.ERROR);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShowErrorDialog.class.getResource("/forms/ShowErrorDialog.fxml"));
		loader.setController(null);

		Region root = null;

		try
		{
			root = loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		nameSpace = loader.getNamespace();

		header  = (Label) nameSpace.get("header");
		content = (TextArea) nameSpace.get("content");

		dialog.setUseButton(true, false);
		dialog.setContent(root);
		dialog.setTitle("Error");
		dialog.create();
	}

	public static void display(Exception error)
	{
		StringWriter message = new StringWriter();
		error.printStackTrace(new PrintWriter(message));

		header.setText(error.toString());
		content.setText(message.toString());

		dialog.show();
	}
}