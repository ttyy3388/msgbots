package org.beuwi.simulator.platform.ui.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.window.WindowScene;
import org.beuwi.simulator.platform.ui.window.WindowStage;
import org.beuwi.simulator.platform.ui.window.WindowType;
import org.beuwi.simulator.utils.ResourceUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogBoxView extends WindowStage implements Initializable
{
	@FXML private BorderPane DIALOG_PANE;
	@FXML private ImageView  DIALOG_ICON;

	@FXML private HBox	 	 DIALOG_BUTTON_BOX;

	@FXML private Button 	 DIALOG_BUTTON_OK;
	@FXML private Button 	 DIALOG_BUTTON_NO;
	@FXML private Button     DIALOG_BUTTON_ACTION;

	Region DIALOG_CONTENT;
	int    DIALOG_TYPE;

	public DialogBoxView(int type)
	{
		super(WindowType.DIALOG);

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

		DIALOG_TYPE = type;

		DIALOG_PANE.getStyleClass().add("dialog");
	}

	public void setOnKeyPressed(EventHandler<KeyEvent> handler)
	{
		addEventHandler(KeyEvent.KEY_PRESSED, handler);
	}

	public void setUseButton(boolean ok, boolean no, boolean action)
	{
		if (!ok)     DIALOG_BUTTON_BOX.getChildren().remove(DIALOG_BUTTON_OK);
		if (!no)     DIALOG_BUTTON_BOX.getChildren().remove(DIALOG_BUTTON_NO);
		if (!action) DIALOG_BUTTON_BOX.getChildren().remove(DIALOG_BUTTON_ACTION);
	}

	public void setContent(Region content)
	{
		DIALOG_CONTENT = content;
	}

	public Button getOkButton()
	{
		return DIALOG_BUTTON_OK;
	}

	public Button getNoButton()
	{
		return DIALOG_BUTTON_NO;
	}

	public Button getActionButton()
	{
		return DIALOG_BUTTON_ACTION;
	}

	/* public void setType(int type)
	{
		DIALOG_TYPE = type;
	} */

	public void create()
	{
		Image image= switch (DIALOG_TYPE)
		{
			case DialogBoxType.ERROR   -> ResourceUtils.getImage("dialog_error.png");
			case DialogBoxType.WARNING -> ResourceUtils.getImage("dialog_warning.png");
			case DialogBoxType.EVENT   -> ResourceUtils.getImage("dialog_event.png");
			default -> null;
		};

		switch (DIALOG_TYPE)
		{
			case DialogBoxType.NONE  :
				DIALOG_PANE.getChildren().remove(DIALOG_PANE.getLeft());
				setMinSize(DIALOG_CONTENT.getMinWidth(), DIALOG_CONTENT.getMinHeight() + 47);
				setSize(DIALOG_CONTENT.getPrefWidth(), DIALOG_CONTENT.getPrefHeight() + 47);
				break;
			default :
				DIALOG_ICON.setImage(image);
				setMinSize(DIALOG_CONTENT.getMinWidth() + 75, DIALOG_CONTENT.getMinHeight() + 47);
				setSize(DIALOG_CONTENT.getPrefWidth() + 75, DIALOG_CONTENT.getPrefHeight() + 47);
				break;
		};

		DIALOG_PANE.setCenter(DIALOG_CONTENT);

		// initStyle(StageStyle.UNIFIED);
		setScene(new WindowScene(DIALOG_PANE));
		show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		DIALOG_BUTTON_OK.addEventHandler(ActionEvent.ACTION, event ->
		{
			close();
		});

		DIALOG_BUTTON_NO.addEventHandler(ActionEvent.ACTION, event ->
		{
			close();
		});

		DIALOG_BUTTON_ACTION.setOnAction(event ->
		{

		});
	}
}