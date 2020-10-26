package org.beuwi.msgbots.platform.gui.dialog;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.ContextMenu;
import org.beuwi.msgbots.platform.gui.control.HBox;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.control.Menu;
import org.beuwi.msgbots.platform.gui.enums.Theme;
import org.beuwi.msgbots.platform.gui.layout.ShadowPane;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.util.AllSVGIcons;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.beuwi.msgbots.platform.win.WindowFrame;
import org.beuwi.msgbots.platform.win.WindowType;

public class DialogBoxFrame extends ShadowPane
{
	private static final PseudoClass FOCUSED_PSEUDO_CLASS = PseudoClass.getPseudoClass("focused");

	private static final String DEFAULT_RESOURCE_NAME = "dialog-box-frame";
	private static final String DEFAULT_STYLE_CLASS = "dialog-box";

	// WINDOW
	@FXML private BorderPane brpWinRoot;
	@FXML private ImageView imvWinIcon;
	@FXML private StackPane stpWinMain; // Window Content
	@FXML private Button btnWinClose;
	@FXML private Label lblWinTitle;

	// DIALOG
	@FXML private HBox<Button> hbxBtnBar;
	@FXML private Button btnAction;
	@FXML private Button btnCancel;

	private final DialogBoxEvent event;
	private final WindowFrame frame;
	private final ContextMenu menu;

	private final Stage stage;

	private Region content;
	private String title;

	{
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

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
			new Menu("Close")
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

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}

	public void setContent(Region content)
	{
		this.content = content;
	}

	// Use Action Button, Use Cancel Button
	public void setUseButton(boolean action, boolean cancel)
	{
		if (!action) { hbxBtnBar.remove(btnAction); }
		if (!cancel) { hbxBtnBar.remove(btnCancel); }
	}

	public HBox<Button> getButtonBar()
	{
		return hbxBtnBar;
	}

	public Button getActionButton()
	{
		return btnAction;
	}

	public Button getCancelButton()
	{
		return btnCancel;
	}

	private void initWinSize()
	{
		// content.setMinWidth(content.getPrefWidth());
		// content.setMinHeight(content.getPrefHeight());
		// content.setMaxWidth(content.getPrefHeight());
		// content.setMaxHeight(content.getPrefHeight());
	}

	public void create()
	{
		super.setContent(brpWinRoot);
		event.setMovable(brpWinRoot);

		stage.focusedProperty().addListener(change ->
		{
			pseudoClassStateChanged(FOCUSED_PSEUDO_CLASS, stage.isFocused());
		});

		stage.addEventHandler(KeyEvent.KEY_PRESSED, event ->
		{
			switch (event.getCode())
			{
				case ESCAPE : stage.close(); break;
			}
		});

		lblWinTitle.setText(title);

		stpWinMain.setContent(content);

		btnWinClose.setGraphic(AllSVGIcons.get("Window.Close"));
		btnWinClose.setOnAction(event ->
		{
			stage.close();
		});

		frame.setContent(this);
		frame.setTitle(title);
		frame.setTheme(Theme.DARK);
		frame.setType(WindowType.DIALOG);
		frame.create();
	}

	public void close()
	{
		stage.close();
	}
}