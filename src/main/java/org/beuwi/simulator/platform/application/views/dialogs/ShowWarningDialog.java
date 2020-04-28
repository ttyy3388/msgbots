package org.beuwi.simulator.platform.application.views.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxType.DOCUMENT;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxView;
import org.beuwi.simulator.utils.ResourceUtils;

public class ShowWarningDialog extends IDialogBoxView
{
	@FXML private Label label;

	private String text;

	public ShowWarningDialog(String text)
	{
		super(DOCUMENT.WARNING);
		this.text = text;
	}

	public void display()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("ShowWarningDialog"));
		loader.setController(this);

		Region root = null;

		try
		{
			root = loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		initialize();

		setUseButton(true, false);
		setContent(root);
		setTitle("Warning");
		create();
	}

	private void initialize()
	{
		label.setText(text);
	}
}