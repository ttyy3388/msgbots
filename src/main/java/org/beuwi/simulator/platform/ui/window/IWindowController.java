package org.beuwi.simulator.platform.ui.window;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.application.views.actions.OpenDebugRoomTabAction;
import org.beuwi.simulator.platform.application.views.actions.OpenGlobalLogTabAction;
import org.beuwi.simulator.platform.application.views.actions.OpenSettingsTabAction;
import org.beuwi.simulator.platform.application.views.actions.RefreshBotsAction;
import org.beuwi.simulator.platform.application.views.actions.SaveAllEditorTabsAction;
import org.beuwi.simulator.platform.application.views.actions.SaveEditorTabAction;
import org.beuwi.simulator.platform.application.views.dialogs.CreateBotDialog;
import org.beuwi.simulator.platform.application.views.dialogs.ImportScriptDialog;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuButton;
import org.beuwi.simulator.platform.ui.components.IMenuItem;
import org.beuwi.simulator.platform.ui.components.ISVGGlyph;
import org.beuwi.simulator.utils.ResourceUtils;

public class IWindowController extends IWindowEvent
{
	@FXML private StackPane  stpRootPane;
	@FXML private BorderPane brpContentPane;
	@FXML private AnchorPane anpTitleBar;
	@FXML private HBox 		 hoxMenuBar;

	// @FXML private Label		 lblTitle;

	// Menu Bar
	@FXML private IMenuButton btnFileMenu;
	@FXML private IMenuButton btnEditMenu;
	@FXML private IMenuButton btnViewMenu;
	@FXML private IMenuButton btnDebugMenu;

	// Button Bar
	@FXML private Button btnMinimize;
	@FXML private Button btnMaximize;
	@FXML private Button btnClose;

	private IWindowView main;
	private IWindowType type;
	private Stage stage;
	private Region content;
	private String title;

	public IWindowController(IWindowView main)
	{
		super(main);

		this.main = main;
	}

	public void create()
	{
		type = main.getType();
		stage = main.getStage();
		content = main.getContent();
		title = main.getTitle();

		switch (type)
		{
			case MAIN :

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

				stpRootPane.getStyleClass().add("main");

				break;

			case DIALOG :

				hoxMenuBar.setVisible(false);
				btnMinimize.setVisible(false);
				btnMaximize.setVisible(false);

				stpRootPane.getStyleClass().add("dialog");

				break;
		}

		// When the stage appears
		stage.showingProperty().addListener((observable, oldValue, newValue) ->
		{
			if (newValue)
			{
				this.setMovable(anpTitleBar);
				this.setResizable(stpRootPane);
			}
		});

		stage.focusedProperty().addListener((observable, oldValue, newValue) ->
		{
			main.pseudoClassStateChanged(PseudoClass.getPseudoClass("focused"), newValue);
		});

		this.maximizedProperty().addListener((observable, oldValue, newValue) ->
		{
			main.pseudoClassStateChanged(PseudoClass.getPseudoClass("maximized"), newValue);
		});

		btnMinimize.setGraphic(ISVGGlyph.getGlyph("Window.Minimize"));
		btnMinimize.setOnAction(event ->
		{
			this.setMinimized();
		});

		btnMaximize.setGraphic(ISVGGlyph.getGlyph("Window.Maximize"));
		btnMaximize.setOnAction(event ->
		{
			this.setMaximized();
		});

		btnClose.setGraphic(ISVGGlyph.getGlyph("Window.Close"));
		btnClose.setOnAction(event ->
		{
			this.setClosed();
		});

		brpContentPane.setCenter(content);

		for (Node node : brpContentPane.getChildren())
		{
			node.setCursor(Cursor.DEFAULT);
		}

		main.getChildren().add(stpRootPane);
		main.getStyleClass().add("window");
		main.getStylesheets().add(ResourceUtils.getStyle("WindowView"));

		double minW = content.getMinWidth();
		double minH = content.getMinHeight();
		double prefW = content.getPrefWidth();
		double prefH = content.getPrefHeight();
		double maxW = content.getMaxWidth();
		double maxH = content.getMaxHeight();

		stage.setMinWidth(minW > 0 ? minW : 400);
		stage.setMinHeight(minH > 0 ? minH + 28 : 200);
		stage.setWidth(prefW > 0 ? prefW : 600);
		stage.setHeight(prefH > 0 ? prefH + 28 : 400);
		stage.setMaxWidth(maxW > 0 ? maxW : Double.MAX_VALUE);
		stage.setMaxHeight(maxH > 0 ? maxH : Double.MAX_VALUE);
	}
}
