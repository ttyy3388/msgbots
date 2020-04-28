package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.application.actions.DeleteBotAction;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxType.DOCUMENT;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxView;
import org.beuwi.simulator.utils.ResourceUtils;

public class DeleteBotDialog extends IDialogBoxView
{
	@FXML private Label label;

	private Button btnDelete;
	private Button btnCancel;

	private String name;

	public DeleteBotDialog(String name)
	{
		super(DOCUMENT.EVENT);
		this.name = name;
	}

	public void display()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("DeleteBotDialog"));
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
		setTitle("Delete");
		create();
	}

	private void initialize()
	{
		btnDelete = getOKButton();
		btnCancel = getNOButton();

		btnDelete.setText("Delete");
		btnCancel.setText("Cancel");

		btnDelete.setOnAction(event ->
		{
			action();
		});

		setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				action();
			}
		});

		label.setText("Delete bot '" + name + "'?");
	}

	private void action()
	{
		DeleteBotAction.update(name); close();
	}
}