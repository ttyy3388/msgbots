package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.application.actions.DeleteBotAction;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

public class DeleteBotDialog
{
	private static ObservableMap<String, Object> nameSpace;
	private static DialogBoxView dialog;

	private static Label label;

	private static Button btnDelete;
	private static Button btnCancel;

	private static String name;

	public static void initialize()
	{
		dialog = new DialogBoxView(DialogBoxType.ERROR);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CreateBotDialog.class.getResource("/forms/CreateBotDialog.fxml"));
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
	}

	public static void display()
	{
		dialog = new DialogBoxView(DialogBoxType.ERROR);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(DeleteBotDialog.class.getResource("/forms/DeleteBotDialog.fxml"));
		loader.setController(null);

		Region root = null;

		try
		{
			root = loader.load();
		}
		catch (Exception e)
		{
			ShowErrorDialog.display(e);
		}

		nameSpace = loader.getNamespace();

		label = (Label) nameSpace.get("label");

		btnDelete = dialog.getOkButton();
		btnCancel = dialog.getNoButton();

		btnDelete.setText("Delete");
		btnCancel.setText("Cancel");

		btnDelete.setOnAction(event ->
		{
			action();
		});

		dialog.setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				action();
			}
		});

		dialog.setUseButton(true, false);
		dialog.setContent(root);
		dialog.setTitle("Delete");
		dialog.create();
	}

	public static void display(String name)
	{
		DeleteBotDialog.name = name;

		label.setText("Delete bot '" + name + "'?");

		dialog.show();
	}

	private static void action()
	{
		DeleteBotAction.update(name);

		dialog.close();
	}
}