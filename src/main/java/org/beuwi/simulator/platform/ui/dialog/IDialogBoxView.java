package org.beuwi.simulator.platform.ui.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxType.DOCUMENT;
import org.beuwi.simulator.platform.ui.window.IWindowScene;
import org.beuwi.simulator.platform.ui.window.IWindowStage;
import org.beuwi.simulator.platform.ui.window.IWindowType;
import org.beuwi.simulator.utils.ResourceUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class IDialogBoxView extends IWindowStage implements Initializable
{
	@FXML private BorderPane DIALOG_PANE;
	@FXML private ImageView  DIALOG_ICON;

	@FXML private HBox	 	 DIALOG_BUTTON_BOX;

	@FXML private Button 	 DIALOG_BUTTON_OK;
	@FXML private Button 	 DIALOG_BUTTON_NO;

	IDialogBoxType DIALOG_TYPE;
	Region 		DIALOG_CONTENT;

	DOCUMENT 	DOCUMENT_TYPE;

	// DOCUMENT
	public IDialogBoxView(DOCUMENT type)
	{
		this(IDialogBoxType.DOCUMENT);
		DOCUMENT_TYPE = type;
	}

	public IDialogBoxView(IDialogBoxType type)
	{
		super(IWindowType.DIALOG);

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

	public void setUseButton(boolean ok, boolean no)
	{
		if (!ok) DIALOG_BUTTON_BOX.getChildren().remove(DIALOG_BUTTON_OK);
		if (!no) DIALOG_BUTTON_BOX.getChildren().remove(DIALOG_BUTTON_NO);
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

	/* public void setType(int type)
	{
		DIALOG_TYPE = type;
	} */

	public void create()
	{
		switch (DIALOG_TYPE)
		{
			case APPLICATION :

				DIALOG_PANE.getChildren().remove(DIALOG_PANE.getLeft());
				setMinSize(DIALOG_CONTENT.getMinWidth(), DIALOG_CONTENT.getMinHeight() + 47);
				setSize(DIALOG_CONTENT.getPrefWidth(), DIALOG_CONTENT.getPrefHeight() + 47);
				break;

			// DOCUMENT
			case DOCUMENT :

				DIALOG_ICON.setImage
				(
					switch (DOCUMENT_TYPE)
					{
						case ERROR   -> ResourceUtils.getImage("error_big.png");
						case WARNING -> ResourceUtils.getImage("warning_big.png");
						case EVENT   -> ResourceUtils.getImage("event_big.png");
						default -> null;
					}
				);

				setMinSize(DIALOG_CONTENT.getMinWidth() + 70, DIALOG_CONTENT.getMinHeight() + 47);
				setSize(DIALOG_CONTENT.getPrefWidth() + 70, DIALOG_CONTENT.getPrefHeight() + 47);
				break;
		};

		DIALOG_PANE.setCenter(DIALOG_CONTENT);

		// initStyle(StageStyle.UNIFIED);
		setScene(new IWindowScene(DIALOG_PANE));
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
	}
}