package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

public class SettingsDialog
{
	private static ObservableMap<String, Object> nameSpace;
	private static DialogBoxView dialog;

	private static AnchorPane root;

	private static Button btnOk;
	private static Button btnNo;

	public static void initialize()
	{
		dialog = new DialogBoxView(DialogBoxType.NONE);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SettingsDialog.class.getResource("/forms/SettingsDialog.fxml"));
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

		root = loader.getRoot();

		btnOk = dialog.getOkButton();
		btnNo = dialog.getNoButton();

		btnOk.setDisable(true);
		btnOk.setText("OK");
		btnNo.setText("Cancel");

		dialog.setUseButton(true, true);
		dialog.setContent(root);
		dialog.setTitle("Settings");
		dialog.create();
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}

	public static AnchorPane getRoot()
	{
		return root;
	}

	public static void display()
	{
		dialog.show();
	}
}