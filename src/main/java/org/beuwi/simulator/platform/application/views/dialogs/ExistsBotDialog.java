package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxType.DOCUMENT;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxView;
import org.beuwi.simulator.utils.ResourceUtils;

public class ExistsBotDialog extends IDialogBoxView
{
    @FXML private Label label;

    private String name;

    public ExistsBotDialog(String name)
    {
        super(DOCUMENT.ERROR);
        this.name = name;
    }

    public void display()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ResourceUtils.getForm("ExistsBotDialog"));
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
