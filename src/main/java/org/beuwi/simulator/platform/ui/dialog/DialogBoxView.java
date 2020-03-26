package org.beuwi.simulator.platform.ui.dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.window.WindowScene;
import org.beuwi.simulator.platform.ui.window.WindowStage;
import org.beuwi.simulator.platform.ui.window.WindowType;

public class DialogBoxView extends WindowStage
{
	@FXML private BorderPane DIALOG_PANE;
	@FXML private ImageView  DIALOG_ICON;

	@FXML private HBox	 	 DIALOG_BUTTON_BOX;
	@FXML private Button 	 DIALOG_BUTTON_OK;
	@FXML private Button 	 DIALOG_BUTTON_NO;

	// Default
	int DIALOG_TYPE;

	/* public DialogBoxView()
	{
		this(DialogBoxType.EVENT);
	} */

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
	}

	public void setContent(Region content)
	{
		DIALOG_PANE.setCenter(content);
		/* DIALOG_PANE.setMinWidth(content.getMinWidth());
		DIALOG_PANE.setMinHeight(content.getMinHeight() + 45);
		DIALOG_PANE.setPrefWidth(content.getPrefWidth());
		DIALOG_PANE.setPrefHeight(content.getPrefHeight() + 45); */
	}

	/* public void setType(int type)
	{
		DIALOG_TYPE = type;
	} */

	public void create()
	{
		Image image = switch (DIALOG_TYPE)
		{
			case DialogBoxType.ERROR   -> new Image(getClass().getResource("/images/dialog_error.png").toExternalForm());
			case DialogBoxType.WARNING -> new Image(getClass().getResource("/images/dialog_warning.png").toExternalForm());
			case DialogBoxType.EVENT   -> new Image(getClass().getResource("/images/dialog_event.png").toExternalForm());
			default -> null;
		};

		// Set Icon
		DIALOG_ICON.setImage(image);
		// initStyle(StageStyle.UNIFIED);
		setScene(new WindowScene(DIALOG_PANE));
		show();
	}
}