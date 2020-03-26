package org.beuwi.simulator.platform.ui.window.dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.window.WindowType;
import org.beuwi.simulator.platform.ui.window.WindowView;

public class DialogBoxView extends WindowView
{
	@FXML private BorderPane DIALOG_PANE;
	@FXML private HBox DIALOG_BUTTON_BOX;

	@FXML private Button DIALOG_BUTTON_OK;
	@FXML private Button DIALOG_BUTTON_NO;
	@FXML private Button DIALOG_BUTTON_ACTION;

	String DIALOG_TITLE;
	int    DIALOG_TYPE;

	public DialogBoxView()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/DialogBoxView.fxml"));
		loader.setController(this);

		try
		{
			loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setDialogContent(Region content)
	{
		DIALOG_PANE.setCenter(content);
		DIALOG_PANE.setMinWidth(content.getMinWidth());
		DIALOG_PANE.setMinHeight(content.getMinHeight() + 45);
		DIALOG_PANE.setPrefWidth(content.getPrefWidth());
		DIALOG_PANE.setPrefHeight(content.getPrefHeight() + 45);
	}

	public void setDialogTitle(String title)
	{
		DIALOG_TITLE = title;
	}

	public void setDialogType(int type)
	{
		DIALOG_TYPE = type;
	}

	public void createDialog()
	{
		setWindowContent(DIALOG_PANE);
		setWindowTitle(DIALOG_TITLE);
		setWindowType(WindowType.DIALOG);
		createWindow();
		show();
	}
}