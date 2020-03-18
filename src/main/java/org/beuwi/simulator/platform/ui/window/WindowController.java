package org.beuwi.simulator.platform.ui.window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController extends WindowEvent implements Initializable
{
	@FXML private AnchorPane WINDOW_SHADOW_PANE;
	@FXML private AnchorPane WINDOW_ROOT_PANE;
	@FXML private BorderPane WINDOW_CONTENT_PANE;

	@FXML private ImageView  WINDOW_ICON_VIEW;

	@FXML private AnchorPane WINDOW_TITLE_BAR;
	@FXML private Label      WINDOW_TITLE_LABEL;

	@FXML private Button     WINDOW_MINIMIZE_BUTTON;
	@FXML private Button     WINDOW_MAXIMIZE_BUTTON;
	@FXML private Button     WINDOW_CLOSE_BUTTON;

	@FXML private ImageView  WINDOW_MINIMIZE_IMAGE;
	@FXML private ImageView  WINDOW_MAXIMIZE_IMAGE;
	@FXML private ImageView  WINDOW_CLOSE_IMAGE;

	final Stage  WINDOW_STAGE;
	final Region WINDOW_CONTENT;
	final int    WINDOW_TYPE;

	public WindowController(Stage WINDOW_STAGE, Region WINDOW_CONTENT, int WINDOW_TYPE)
	{
		super(WINDOW_STAGE);

		this.WINDOW_STAGE   = WINDOW_STAGE;
		this.WINDOW_CONTENT = WINDOW_CONTENT;
		this.WINDOW_TYPE    = WINDOW_TYPE;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		WINDOW_STAGE.setMinWidth (WINDOW_CONTENT.getMinWidth()   + 12);
		WINDOW_STAGE.setMinHeight(WINDOW_CONTENT.getMinHeight()  + 12 + 28);
		WINDOW_STAGE.setWidth	 (WINDOW_CONTENT.getPrefWidth()  + 12);
		WINDOW_STAGE.setHeight	 (WINDOW_CONTENT.getPrefHeight() + 12 + 28);
		// WINDOW_STAGE.setMaxWidth(Double.MAX_VALUE);
		// WINDOW_STAGE.setMaxHeight(Double.MAX_VALUE);

		WINDOW_CONTENT_PANE.setCenter(WINDOW_CONTENT);
		WINDOW_ROOT_PANE.setEffect
		(
			new DropShadow
			(
				BlurType.GAUSSIAN,
				Color.rgb(0, 0, 0, 0.5), 10, 0.2, 0, 0
			)
		);

		WINDOW_CLOSE_BUTTON.setOnAction(event ->
		{
			setWindowClose();
		});

		setWindowMovable(WINDOW_TITLE_BAR);
		setWindowResizable();
	}
}