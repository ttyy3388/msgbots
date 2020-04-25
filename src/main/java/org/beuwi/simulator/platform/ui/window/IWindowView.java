package org.beuwi.simulator.platform.ui.window;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.beuwi.simulator.utils.ResourceUtils;

public class IWindowView extends AnchorPane
{
	@FXML private BorderPane bopRootPane;
	@FXML private AnchorPane anpTitleBar;
	@FXML private StackPane  stpContentArea;
	@FXML private AnchorPane anpStatusBar;
	@FXML private HBox 		 hoxMenuBar;
	@FXML private ImageView  imvWindowIcon;
	@FXML private Label	 	 lblWindowTitle;

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

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setStage(Stage stage)
	{
		this.stage = stage;
	}

	public void setType(IWindowType type)
	{
		this.type = type;
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

				break;

			case DIALOG :

				AnchorPane.setLeftAnchor(lblWindowTitle, 30.0);

				hoxMenuBar.setVisible(false);
				btnMinimize.setVisible(false);
				btnMaximize.setVisible(false);
				anpStatusBar.setVisible(false);

				break;
		}

		stage.showingProperty().addListener((observable, oldValue, newValue) ->
		{
			if (newValue)
			{
				ievent.setMovable();
				ievent.setResizable();
			}
		});

		stage.focusedProperty().addListener((observable, oldValue, newValue) ->
		{
			this.pseudoClassStateChanged(PseudoClass.getPseudoClass("focused"), newValue);
		});

		ievent.maximizedProperty().addListener((observable, oldValue, newValue) ->
		{
			this.pseudoClassStateChanged(PseudoClass.getPseudoClass("maximized"), newValue);

			double value = newValue ? 0.0 : 5.0;

			AnchorPane.setTopAnchor(bopRootPane, value);
			AnchorPane.setRightAnchor(bopRootPane, value);
			AnchorPane.setBottomAnchor(bopRootPane, value);
			AnchorPane.setLeftAnchor(bopRootPane, value);
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

		lblWindowTitle.setText(title);

		stpContentArea.getChildren().add(content);

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
	}

	/* private static class FileMenu
	{
		public static void initialize()
		{
			IContextMenu menu = new IContextMenu
			(
				new IMenuItem("New Bot", "Ctrl + N", event -> new CreateBotDialogBox().display()),
				new IMenuItem("Import Script", "Ctrl + I", event -> new ImportScriptDialogBox().display()),
				new SeparatorMenuItem(),
				new IMenuItem("Save", "Ctrl + S", event -> SaveEditorTabAction.update()),
				new IMenuItem("Save All", "Ctrl + Shift + S", event -> SaveAllEditorTabsAction.update()),
				new SeparatorMenuItem(),
				new IMenuItem("Reload All Bots", "Ctrl + Alt + Y", event -> RefreshExplorerAction.update()),
				new SeparatorMenuItem(),
				new IMenuItem("Settings", "Ctrl + Alt + S", event -> OpenSettingsTabAction.update())
			);

			menu.setNode((Button) nameSpace.get("btnFileMenu"));
		}
	}

	private static class EditMenu
	{
		public static void initialize()
		{
			IContextMenu menu = new IContextMenu
			(
				new IMenuItem("Undo", "Ctrl + Z"),
				new IMenuItem("Redo", "Ctrl + Y"),
				new SeparatorMenuItem(),
				new IMenuItem("Cut", "Ctrl + X"),
				new IMenuItem("Copy", "Ctrl + C"),
				new IMenuItem("Paste", "Ctrl + V")
			);

			menu.setNode((Button) nameSpace.get("btnEditMenu"));
		}
	}

	private static class ViewMenu
	{
		public static void initialize()
		{
			IContextMenu menu = new IContextMenu();

			menu.setNode((Button) nameSpace.get("btnViewMenu"));
		}
	}

	private static class DebugMenu
	{
		public static void initialize()
		{
			IContextMenu menu = new IContextMenu
			(
				new IMenuItem("Open Debug Room", event -> OpenDebugRoomTabAction.update()),
				new IMenuItem("Show Global Log", event -> OpenGlobalLogTabAction.update())
			);

			menu.setNode((Button) nameSpace.get("btnDebugMenu"));
		}
	}
	 */
}