package org.beuwi.msgbots.platform.win;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.gui.control.ContextMenu;
import org.beuwi.msgbots.platform.gui.control.MenuItem;
import org.beuwi.msgbots.platform.util.AllSVGIcons;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class WindowFrame extends StackPane
{
	private static final PseudoClass FOCUSED_PSEUDO_CLASS = PseudoClass.getPseudoClass("focused");
	// private static final PseudoClass MINIMIZED_PSEUDO_CLASS = PseudoClass.getPseudoClass("minimized");
	// private static final PseudoClass MAXIMIZED_PSEUDO_CLASS = PseudoClass.getPseudoClass("maximized");
	// private static final PseudoClass CLOSED_PSEUDO_CLASS = PseudoClass.getPseudoClass("closed");

	public static final String DEFAULT_RESOURCE_NAME = "window-box-frame";
	public static final String DEFAULT_STYLE_CLASS = "window";

	@FXML private BorderPane brpRootPane;
	@FXML private AnchorPane anpTitleBar;
	@FXML private HBox 		 hbxTitleBar;

	@FXML private ImageView imvWinIcon;

	@FXML private Label  lblWinTitle;
	@FXML private Button btnWinClose;

	private final Stage stage;

	private WindowEvent event;

	private WindowType type;
	private Region content;
	private String title;

	public WindowFrame(Stage stage)
	{
		this.stage = stage;
	}

	public WindowType getType()
	{
		return type;
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

	public void create()
	{
		if (WindowType.DIALOG.equals(type))
		{
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

			ContextMenu menu = new ContextMenu
			(
				new MenuItem("Close", event -> stage.close())
			);

            event = new WindowEvent(stage);

			menu.setNode(imvWinIcon);
			event.setMovable(anpTitleBar);

			// lblWinTitle.setText(title);
			brpRootPane.setCenter(content);

			hbxTitleBar.setVisible(false);

			btnWinClose.setGraphic(AllSVGIcons.get("Window.Close"));
			btnWinClose.setOnAction(event ->
			{
				stage.close();
			});

			this.getChildren().add(brpRootPane);

			stage.initModality(Modality.WINDOW_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.initOwner(MainView.getStage());
		}
		else
		{
			stage.addEventHandler(KeyEvent.KEY_PRESSED, event ->
			{
				/* if (event.getCode().equals(KeyCode.ALT))
				{
					ToggleMenuBarAction.execute();
				} */
			});

			this.getChildren().add(content);
		}

		this.getStyleClass().add(DEFAULT_STYLE_CLASS);

		stage.focusedProperty().addListener((observable, oldValue, newValue) ->
		{
			this.pseudoClassStateChanged(FOCUSED_PSEUDO_CLASS, newValue);
		});

		stage.getIcons().add(ResourceUtils.getImage("program"));
		stage.setScene(new WindowScene(this));
		stage.setTitle(title);
		stage.toFront();
		stage.show();
	}

	public void open()
	{
		stage.show();
	}
}