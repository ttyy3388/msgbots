package org.beuwi.msgbots.platform.gui.dialog;

import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.ContextMenu;
import org.beuwi.msgbots.platform.gui.control.HBox;
import org.beuwi.msgbots.platform.gui.control.MenuItem;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.beuwi.msgbots.platform.win.WindowFrame;
import org.beuwi.msgbots.platform.win.WindowType;

public class DialogBoxFrame extends StackPane
{
	private static final PseudoClass FOCUSED_PSEUDO_CLASS = PseudoClass.getPseudoClass("focused");

	private static final String DEFAULT_RESOURCE_NAME = "dialog-box-frame";
	private static final String DEFAULT_STYLE_CLASS = "dialog";

	private static final int DEFAULT_MARGIN_INSETS = 28;

	@FXML private BorderPane brpRootPane;
	@FXML private HBox hbxButtonBar;
	@FXML private Label lblWinTitle;

	@FXML private Button btnOk;
	@FXML private Button btnNo;

	private final DialogBoxEvent event;
	private final WindowFrame frame;
	private final ContextMenu menu;

	private final Stage stage;

	private Region content;
	private String title;

	public DialogBoxFrame()
	{
		this(new Stage());
	}

	public DialogBoxFrame(Stage stage)
	{
		this.stage = stage;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm(DEFAULT_RESOURCE_NAME));
		loader.setController(this);

		try
		{
			loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		this.menu = new ContextMenu
		(
			new MenuItem("Close")
		);

		this.frame = new WindowFrame(stage);
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

	public Button getOkButton()
	{
		return btnOk;
	}

	public Button getNoButton()
	{
		return btnNo;
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
		if (!ok) { hbxButtonBar.getChildren().remove(btnOk); }
		if (!no) { hbxButtonBar.getChildren().remove(btnNo); }
	}

	private void initWinSize()
	{
		content.setMinWidth(content.getPrefWidth());
		content.setMinHeight(content.getPrefHeight());
		// content.setMaxWidth(content.getPrefHeight());
		// content.setMaxHeight(content.getPrefHeight());
	}

	public void create()
	{
		lblWinTitle.setText(title);
		brpRootPane.setCenter(content);

		btnOk.addEventHandler(ActionEvent.ACTION, event ->
		{
			stage.close();
		});

		btnNo.addEventHandler(ActionEvent.ACTION, event ->
		{
			stage.close();
		});

		stage.addEventHandler(KeyEvent.KEY_PRESSED, event ->
		{
			switch (event.getCode())
			{
				case ESCAPE : stage.close(); break;
			}
		});

		stage.focusedProperty().addListener(change ->
		{
			pseudoClassStateChanged(FOCUSED_PSEUDO_CLASS, stage.isFocused());
		});

		this.initWinSize();
		this.getChildren().add(brpRootPane);
		this.getStyleClass().add(DEFAULT_STYLE_CLASS);

		frame.setContent(this);
		frame.setTitle(title);
		frame.setType(WindowType.DIALOG);
		frame.create();
	}

	public void close()
	{
		stage.close();
	}
}