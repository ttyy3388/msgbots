package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxView;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxType.DOCUMENT;

public class ExistsBotDialogBox extends IDialogBoxView
{
    @FXML private Label label;

    private String name;

    public ExistsBotDialogBox(String name)
    {
        super(DOCUMENT.ERROR);
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
            new ShowErrorDialogBox(e).display();
        }

        initialize();

        setUseButton(true, false);
        setContent(root);
        setTitle("Exists");
        create();
    }

    private void initialize()
    {
        label.setText("Cannot create bot '" + name + "' bot already exists.");
    }
}
