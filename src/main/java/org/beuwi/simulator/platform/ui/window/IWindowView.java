package org.beuwi.simulator.platform.ui.window;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.views.actions.*;
import org.beuwi.simulator.platform.application.views.dialogs.CreateBotDialog;
import org.beuwi.simulator.platform.application.views.dialogs.ImportScriptDialog;
import org.beuwi.simulator.platform.ui.components.*;
import org.beuwi.simulator.utils.ResourceUtils;

public class IWindowView extends AnchorPane
{
	@FXML private BorderPane bopRootPane;
	@FXML private AnchorPane anpTitleBar;
	@FXML private AnchorPane anpStatusBar;
	@FXML private HBox 		 hoxMenuBar;

	@FXML private ImageView  imvWindowImage;
	@FXML private Label		 lblWindowTitle;

	// Menu Bar
	@FXML private IMenuButton btnFileMenu;
	@FXML private IMenuButton btnEditMenu;
	@FXML private IMenuButton btnViewMenu;
	@FXML private IMenuButton btnDebugMenu;

	// Button Bar
	@FXML private Button btnMinimize;
	@FXML private Button btnMaximize;
	@FXML private Button btnClose;

	private IWindowType type;
	private Stage stage;
	private Region content;
	private String title;

	public IWindowView()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ResourceUtils.getForm("WindowView"));
			loader.setController(this);
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

	public BorderPane getRootPane()
	{
		return bopRootPane;
	}

	public AnchorPane getTitleBar()
	{
		return anpTitleBar;
	}

	public void setStage(Stage stage)
	{
		this.stage = stage;
	}

	public void setType(IWindowType type)
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

	public void create()
	{
		IWindowEvent ievent = new IWindowEvent(this);

		switch (type)
		{
			case WINDOW :

				stage.focusedProperty().addListener((observable, oldValue, newValue) ->
				{
					if (newValue)
					{
						bopRootPane.setStyle
						(
							"-fx-border-color: #007ACC;" +
							"-fx-effect: dropshadow(three-pass-box, rgba(0, 122, 204, 0.5), 10, 0.4, 0, 0);"
						);
					}
					else
					{
						bopRootPane.setStyle
						(
							"-fx-border-color: #434346;" +
							"-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.5), 10, 0.4, 0, 0);"
						);
					}
				});

				btnFileMenu.setMenu(new IContextMenu
				(
					new IMenuItem("New Bot", "Ctrl + N", event -> new CreateBotDialog().display()),
					new IMenuItem("Import Script", "Ctrl + I", event -> new ImportScriptDialog().display()),
					new SeparatorMenuItem(),
					new IMenuItem("Save", "Ctrl + S", event -> SaveEditorTabAction.update()),
					new IMenuItem("Save All", "Ctrl + Shift + S", event -> SaveAllEditorTabsAction.update()),
					new SeparatorMenuItem(),
					new IMenuItem("Reload All Bots", "Ctrl + Alt + Y", event -> RefreshBotsAction.update()),
					new SeparatorMenuItem(),
					new IMenuItem("Settings", "Ctrl + Alt + S", event -> OpenSettingsTabAction.update())
				));

				btnEditMenu.setMenu(new IContextMenu
				(
					new IMenuItem("Undo", "Ctrl + Z"),
					new IMenuItem("Redo", "Ctrl + Y"),
					new SeparatorMenuItem(),
					new IMenuItem("Cut", "Ctrl + X"),
					new IMenuItem("Copy", "Ctrl + C"),
					new IMenuItem("Paste", "Ctrl + V")
				));

				btnViewMenu.setMenu(new IContextMenu());

				btnDebugMenu.setMenu(new IContextMenu
				(
					new IMenuItem("Open Debug Room", event -> OpenDebugRoomTabAction.update()),
					new IMenuItem("Show Global Log", event -> OpenGlobalLogTabAction.update())
				));

				lblWindowTitle.setVisible(false);

				break;

			case DIALOG :

				stage.focusedProperty().addListener((observable, oldValue, newValue) ->
				{
					if (newValue)
					{
						bopRootPane.setStyle("-fx-border-color: #898989;");
					}
					else
					{
						bopRootPane.setStyle("-fx-border-color: #323233;");
					}
				});

				bopRootPane.setEffect
				(
					new DropShadow
					(
						BlurType.GAUSSIAN,
						Color.rgb(0, 0, 0, 0.5),
						10, 0.4, 0, 0
					)
				);

				hoxMenuBar.setVisible(false);
				btnMinimize.setVisible(false);
				btnMaximize.setVisible(false);
				anpStatusBar.setVisible(false);

				lblWindowTitle.setText(title);

				break;
		}

		// When the stage appears
		stage.showingProperty().addListener((observable, oldValue, newValue) ->
		{
			if (!newValue)
			{
				return ;
			}

			ievent.setMovable();
			ievent.setResizable();
		});

		stage.focusedProperty().addListener((observable, oldValue, newValue) ->
		{
			this.pseudoClassStateChanged(PseudoClass.getPseudoClass("focused"), newValue);
		});

		ievent.maximizedProperty().addListener((observable, oldValue, newValue) ->
		{
			double value = newValue ? 0.0 : 5.0;

			AnchorPane.setTopAnchor(bopRootPane, value);
			AnchorPane.setRightAnchor(bopRootPane, value);
			AnchorPane.setBottomAnchor(bopRootPane, value);
			AnchorPane.setLeftAnchor(bopRootPane, value);

			this.pseudoClassStateChanged(PseudoClass.getPseudoClass("maximized"), newValue);
		});

		btnMinimize.setGraphic(ISVGGlyph.getGlyph("Window.Minimize"));
		btnMinimize.setOnAction(event ->
		{
			ievent.setMinimized();
		});

		btnMaximize.setGraphic(ISVGGlyph.getGlyph("Window.Maximize"));
		btnMaximize.setOnAction(event ->
		{
			ievent.setMaximized();
		});

		btnClose.setGraphic(ISVGGlyph.getGlyph("Window.Close"));
		btnClose.setOnAction(event ->
		{
			ievent.setClosed();
		});

		bopRootPane.setCenter(content);

		for (Node node : bopRootPane.getChildren())
		{
			node.setCursor(Cursor.DEFAULT);
		}

		AnchorPane.setTopAnchor(bopRootPane, 5.0);
		AnchorPane.setRightAnchor(bopRootPane, 5.0);
		AnchorPane.setBottomAnchor(bopRootPane, 5.0);
		AnchorPane.setLeftAnchor(bopRootPane, 5.0);

		this.getChildren().add(bopRootPane);
		this.getStyleClass().add("window");
		this.getStylesheets().add(ResourceUtils.getStyle("WindowView"));

		// Shadow And Resize Border : 5px
		double border = 5 * 2 + 2;

		double minW = content.getMinWidth();
		double minH = content.getMinHeight();
		double prefW = content.getPrefWidth();
		double prefH = content.getPrefHeight();
		double maxW = content.getMaxWidth();
		double maxH = content.getMaxHeight();

		// initialize stage (28 : Title Bar , 20 : Status Bar)
		stage.setMinWidth(minW > 0 ? minW + border : 400);
		stage.setMinHeight(minH > 0 ? minH + border + 28 + 20 : 200);
		stage.setWidth(prefW > 0 ? prefW + border : 600);
		stage.setHeight(prefH > 0 ? prefH + border + 28 + 20 : 400);
		stage.setMaxWidth(maxW > 0 ? maxW : Double.MAX_VALUE);
		stage.setMaxHeight(maxH > 0 ? maxH : Double.MAX_VALUE);
	}
}