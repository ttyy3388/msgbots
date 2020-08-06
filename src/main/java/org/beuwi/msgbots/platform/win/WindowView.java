package org.beuwi.msgbots.platform.win;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class WindowView extends StackPane
{
	private final Background WINDOW_BACK_GROUND;
	private final Background DIALOG_BACK_GROUND;
	private final Border WINDOW_DEFAULT_BORDER;
	private final Border WINDOW_FOCUSED_BORDER;
	private final Border DIALOG_FOCUSED_BORDER;
	private final Border DIALOG_DEFAULT_BORDER;
	private final DropShadow WINDOW_DEFAULT_SHADOW;
	private final DropShadow WINDOW_FOCUSED_SHADOW;
	private final DropShadow DIALOG_DEFAULT_SHADOW;
	private final DropShadow DIALOG_FOCUSED_SHADOW;

	private final double WINDOW_BORDER_WIDTH;

	// Preferences
	{
		WINDOW_BORDER_WIDTH = 5;
		WINDOW_BACK_GROUND = new Background(new BackgroundFill(Color.valueOf("#323233"), CornerRadii.EMPTY, Insets.EMPTY));
		DIALOG_BACK_GROUND = new Background(new BackgroundFill(Color.valueOf("#252526"), CornerRadii.EMPTY, Insets.EMPTY));
		WINDOW_DEFAULT_BORDER = new Border(new BorderStroke(Color.valueOf("#434346"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1)));;
		WINDOW_FOCUSED_BORDER = new Border(new BorderStroke(Color.valueOf("#007ACC"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1)));;
		DIALOG_FOCUSED_BORDER = new Border(new BorderStroke(Color.valueOf("#898989"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1)));
		DIALOG_DEFAULT_BORDER = new Border(new BorderStroke(Color.valueOf("#252526"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1)));
		WINDOW_DEFAULT_SHADOW = new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.5), 10, 0.4, 0, 0);
		WINDOW_FOCUSED_SHADOW = new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 122, 204, 0.5), 10, 0.4, 0, 0);
		DIALOG_DEFAULT_SHADOW = new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.5), 10, 0.4, 0, 0);
		DIALOG_FOCUSED_SHADOW = new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.5), 10, 0.4, 0, 0);
	}

	@FXML private BorderPane brpRootPane;
	@FXML private AnchorPane anpTitleBar;
	@FXML private Label 	 lblWinTitle;

	// Button Bar
	@FXML private HBox   hbxButtonBar;
	@FXML private Button btnMinimize;
	@FXML private Button btnMaximize;
	@FXML private Button btnClose;

	private final WindowEvent ievent;
	private final Stage stage;

	private WindowType type;
	private Region content;
	private String title;

	public WindowView(Stage stage)
	{
		this.stage = stage;
		this.ievent = new WindowEvent(this);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("WindowView"));
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

	public WindowType getType()
	{
		return type;
	}

	public Region getContent()
	{
		return content;
	}

	public String getTitle()
	{
		return title;
	}

	public void setType(WindowType type)
	{
		this.type = type;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setContent(Region content)
	{
		this.content = content;
	}

	private void initButtonBar()
	{

	}

	private void initMenuBar()
	{

	}

	private void initStageSize()
	{
		double border = WINDOW_BORDER_WIDTH * 2;

		double minW = content.getMinWidth();
		double minH = content.getMinHeight();
		double preW = content.getPrefWidth();
		double preH = content.getPrefHeight();
		double maxW = content.getMaxWidth();
		double maxH = content.getMaxHeight();

		stage.setMinWidth (minW > 0 ? minW + border : 400);
		stage.setMinHeight(minH > 0 ? minH + border + 28 : 200);
		stage.setWidth	  (preW > 0 ? preW + border : 600);
		stage.setHeight	  (preH > 0 ? preH + border + 28 : 400);
		stage.setMaxWidth (maxW > 0 ? maxW : Double.MAX_VALUE);
		stage.setMaxHeight(maxH > 0 ? maxH : Double.MAX_VALUE);
	}

	public void create()
	{
		switch (type)
		{
			case WINDOW :

				brpRootPane.setBackground(WINDOW_BACK_GROUND);

				stage.focusedProperty().addListener((observable, oldValue, newValue) ->
				{
					brpRootPane.setBorder(newValue ? WINDOW_FOCUSED_BORDER : WINDOW_DEFAULT_BORDER);
					brpRootPane.setEffect(newValue ? WINDOW_FOCUSED_SHADOW : WINDOW_DEFAULT_SHADOW);
				});

				// lblWinTitle.setVisible(false);

				initMenuBar();

				break;

			case DIALOG :

				brpRootPane.setBackground(DIALOG_BACK_GROUND);

				btnMinimize.setVisible(false);
				btnMaximize.setVisible(false);

				lblWinTitle.setText(title);

				stage.focusedProperty().addListener((observable, oldValue, newValue) ->
				{
					brpRootPane.setBorder(newValue ? DIALOG_FOCUSED_BORDER : DIALOG_DEFAULT_BORDER);
					brpRootPane.setEffect(newValue ? DIALOG_FOCUSED_SHADOW : DIALOG_DEFAULT_SHADOW);
				});

				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(MainView.getStage());

				break;
		}

		// When the stage appears
		stage.showingProperty().addListener((observable, oldValue, newValue) ->
		{
			if (newValue)
			{
				ievent.setMovable(anpTitleBar);
				ievent.setResizable(brpRootPane);
			}
		});

		stage.focusedProperty().addListener((observable, oldValue, newValue) ->
		{
			pseudoClassStateChanged(PseudoClass.getPseudoClass("focused"), newValue);
		});

		ievent.maximizedProperty().addListener((observable, oldValue, newValue) ->
		{
			pseudoClassStateChanged(PseudoClass.getPseudoClass("maximized"), newValue);
		});

		btnMinimize.setOnAction(event ->
		{
			ievent.setMinimized();
		});

		btnMaximize.setOnAction(event ->
		{
			ievent.setMaximized();
		});

		btnClose.setOnAction(event ->
		{
			ievent.setClosed();
		});

		brpRootPane.setCenter(content);

		for (Node node : brpRootPane.getChildren())
		{
			node.setCursor(Cursor.DEFAULT);
		}

		getChildren().add(brpRootPane);
		getStyleClass().add("window");
		getStylesheets().add(ResourceUtils.getStyle("WindowView"));
		initStageSize();

		stage.getIcons().add(ResourceUtils.getImage("program"));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setTitle("Messenger Bot Simulator"); // Default Title
		stage.setScene(new WindowScene(this));
		stage.toFront();
	}

	public void show()
	{
		stage.show();
	}
}