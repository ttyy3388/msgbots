package org.beuwi.msgbots.platform.gui.dialog;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.dialog.DialogBoxType.DOCUMENT;
import org.beuwi.msgbots.platform.util.AllSVGIcons;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.beuwi.msgbots.platform.win.WindowScene;

public class DialogBoxFrame extends StackPane
{
	private final int BORDER_WIDTH = 5;

	@FXML private BorderPane brpRootPane;
	@FXML private ImageView  imvDialogIcon;
	@FXML private AnchorPane anpTitleBar;

	@FXML private Label  lblWinTitle;
	@FXML private HBox	 hbxButtonBox;
	@FXML private Button btnDialogClose;

	@FXML private Button btnOK;
	@FXML private Button btnNO;

	private final DialogBoxEvent event;
	private final DialogBoxType type;

	private final Stage stage;

	private DOCUMENT document;
	private Region content;
	private String title;

	public DialogBoxFrame(DOCUMENT document)
	{
		this(DialogBoxType.DOCUMENT);

		this.document = document;
	}

	public DialogBoxFrame(DialogBoxType type)
	{
		this.type = type;

		this.stage = new Stage();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("dialog-box-frame"));
		loader.setController(this);

		try
		{
			loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		this.event = new DialogBoxEvent(stage);
	}

	public Stage getStage()
	{
		return stage;
	}

	public Region getContent()
	{
		return content;
	}

	public String getTitle()
	{
		return title;
	}

	public void addButton(Button button)
	{
		hbxButtonBox.getChildren().add(button);
	}

	public HBox getButtonBox()
	{
		return hbxButtonBox;
	}

	public Button getOKButton()
	{
		return btnOK;
	}

	public Button getNOButton()
	{
		return btnNO;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setContent(Region content)
	{
		this.content = content;
	}

	/* public void setUseButton(boolean ok, boolean no)
	{
		if (!ok) hbxButtonBox.getChildren().remove(btnOK);
		if (!no) hbxButtonBox.getChildren().remove(btnNO);
	} */

	private void setStageSize()
	{
		double border = BORDER_WIDTH * 2;

		// Add Width Value
		int value = type.equals(DialogBoxType.DOCUMENT) ? 70 : 0;

		// double minW = content.getMinWidth();
		// double minH = content.getMinHeight();
		double preW = content.getPrefWidth();
		double preH = content.getPrefHeight();

		// stage.setMinWidth (minW > 0 ? minW + value + border : 400);
		// stage.setMinHeight(minH > 0 ? preW + 78    + border : 200);
		stage.setWidth    (preW > 0 ? preW + value + border : 600);
		stage.setHeight   (preH > 0 ? preH + 88    + border : 400);
	}

	public void create()
	{
		switch (type)
		{
			case APPLICATION :
				brpRootPane.getChildren().remove(brpRootPane.getLeft());
				break;

			case DOCUMENT :
				imvDialogIcon.setImage
				(
					switch (document)
					{
						case ERROR   -> ResourceUtils.getImage("error_big");
						case WARNING -> ResourceUtils.getImage("warning_big");
						case EVENT   -> ResourceUtils.getImage("event_big");
					}
				);
				break;
		};

		btnOK.setStyled(true);
		btnNO.setStyled(true);

		lblWinTitle.setText(title);

		btnDialogClose.setGraphic(AllSVGIcons.get("Window.Close"));
		btnDialogClose.setOnAction(event ->
		{
			stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		});

		brpRootPane.setCenter(content);

		setStageSize();
		setPadding(new Insets(BORDER_WIDTH));
		getChildren().add(brpRootPane);
		getStyleClass().add("dialog");

		Scene scene = new WindowScene(this);

		event.setMovable(anpTitleBar);

		scene.setFill(Color.TRANSPARENT);

		stage.focusedProperty().addListener((observable, oldValue, newValue) ->
		{
			pseudoClassStateChanged(PseudoClass.getPseudoClass("focused"), newValue);
		});

		stage.initModality(Modality.WINDOW_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.initOwner(MainView.getStage());
		stage.setScene(scene);
		stage.toFront();
		stage.show();
	}

	public void close()
	{
		stage.close();
	}

	public void display()
	{
		stage.show();
	}
}