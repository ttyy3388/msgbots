package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.application.actions.RenameBotAction;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

public class RenameBotDialog
{
	private static ObservableMap<String, Object> nameSpace;
	private static DialogBoxView dialog;

	private static Label label;
	private static TextField field;

	private static Button btnRename;
	private static Button btnCancel;

	private static String name;

	public static void initialize()
	{
		dialog = new DialogBoxView(DialogBoxType.EVENT);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(RenameBotDialog.class.getResource("/forms/RenameBotDialog.fxml"));
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
		field = (TextField) nameSpace.get("field");

        btnRename = dialog.getOkButton();
        btnCancel = dialog.getNoButton();

        btnRename.setText("Rename");
        btnCancel.setText("Cancel");

        btnRename.setOnAction(event ->
        {
            action();
        });

        field.textProperty().addListener((observable, oldString, newString) ->
        {
            btnRename.setDisable(newString.isEmpty());
        });

        dialog.setOnKeyPressed(event ->
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

		dialog.setUseButton(true, true);
		dialog.setContent(root);
		dialog.setTitle("Rename");
		dialog.create();
	}

	public static void display(String name)
	{
	    RenameBotDialog.name = name;

        label.setText("Rename bot '" + name + "'?");
        field.setText(name);

	    dialog.show();
	}

	private static void action()
	{
		RenameBotAction.update(name, field.getText());

		dialog.close();
	}
}