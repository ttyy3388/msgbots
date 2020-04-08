package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxType;
import org.beuwi.simulator.platform.ui.dialog.DialogBoxView;

public class ExistsBotDialog extends DialogBoxView
{
    @FXML private Label label;

    private String name;

    public ExistsBotDialog(String name)
    {
        super(DialogBoxType.ERROR);

        this.name = name;
    }

    public void display()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/ExistsBotDialog.fxml"));
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

        setUseButton(true, false);
        setContent(root);
        setTitle("Exists");
        create();
    }

    public void initialize()
    {
        label.setText("Cannot create bot '" + name + "' bot already exists.");
    }
}
