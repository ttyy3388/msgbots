package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

public class ExistsBotDialog
{
    private static ObservableMap<String, Object> nameSpace;
    private static DialogBoxView dialog;

    private static Label label;

    public static void initialize()
    {
        dialog = new DialogBoxView(DialogBoxType.ERROR);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ExistsBotDialog.class.getResource("/forms/ExistsBotDialog.fxml"));
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

        dialog.setUseButton(true, false);
        dialog.setContent(root);
        dialog.setTitle("Exists");
        dialog.create();
    }

    public static void display(String name)
    {
        label.setText("Cannot create bot '" + name + "' bot already exists.");

        dialog.show();
    }
}
