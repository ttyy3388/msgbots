package org.beuwi.simulator.platform.ui.window;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.beuwi.simulator.platform.ui.components.ISVGPath;
import org.beuwi.simulator.utils.ResourceUtils;

public class IWindowView extends AnchorPane
{
	@FXML private BorderPane bopRootPane;
	@FXML private AnchorPane anpTitleBar;
	@FXML private StackPane  stpContentArea;
	@FXML private AnchorPane anpStatusBar;
	@FXML private HBox 		 hoxMenuBar;

	@FXML private Button btnMinimize;
	@FXML private Button btnMaximize;
	@FXML private Button btnClose;

	private IWindowType type;
	private Stage stage;
	private Region content;

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
			double value = newValue ? 0.0 : 5.0;

			AnchorPane.setTopAnchor(bopRootPane, value);
			AnchorPane.setRightAnchor(bopRootPane, value);
			AnchorPane.setBottomAnchor(bopRootPane, value);
			AnchorPane.setLeftAnchor(bopRootPane, value);

			this.pseudoClassStateChanged(PseudoClass.getPseudoClass("maximized"), newValue);
		});

		btnMinimize.setGraphic(ISVGPath.MINIMIZE);
		btnMaximize.setGraphic(ISVGPath.MAXIMIZE);
		btnClose.setGraphic(ISVGPath.CLOSE);

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
}