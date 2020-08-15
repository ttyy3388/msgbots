package org.beuwi.msgbots.platform.gui.dialog;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
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

	private final DialogBoxType type;

    private final Stage stage = new Stage();

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

	public void setUseButton(boolean ok, boolean no)
	{
		if (!ok) hbxButtonBox.getChildren().remove(btnOK);
		if (!no) hbxButtonBox.getChildren().remove(btnNO);
	}

	public void create()
	{
		switch (type)
		{
			case APPLICATION :
				brpRootPane.getChildren().remove(brpRootPane.getLeft());
				setMinSize(content.getMinWidth(), content.getMinHeight() + 68);
				setPrefSize(content.getPrefWidth(), content.getPrefHeight() + 68);
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

				setMinSize(content.getMinWidth() + 70, content.getMinHeight() + 68);
				setPrefSize(content.getPrefWidth() + 70, content.getPrefHeight() + 68);
				break;
		};

		lblWinTitle.setText(title);

		stage.focusedProperty().addListener((observable, oldValue, newValue) ->
		{
			pseudoClassStateChanged(PseudoClass.getPseudoClass("focused"), newValue);
		});

		btnDialogClose.setGraphic(AllSVGIcons.get("Window.Close"));
        btnDialogClose.setOnAction(event ->
		{
			stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		});

		brpRootPane.setCenter(content);

		setPadding(new Insets(BORDER_WIDTH));
		getChildren().add(brpRootPane);
		getStyleClass().add("dialog");
		setStageSize();

		stage.getIcons().add(ResourceUtils.getImage("program"));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setScene(new WindowScene(this));
		stage.toFront();
	}

	public void close()
    {
        stage.close();
    }

	public void show()
	{
		stage.show();
	}
}