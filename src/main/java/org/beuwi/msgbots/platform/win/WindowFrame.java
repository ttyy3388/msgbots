package org.beuwi.msgbots.platform.win;

import javafx.css.PseudoClass;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class WindowFrame extends StackPane
{
	// private static final PseudoClass FOCUSED_PSEUDO_CLASS = PseudoClass.getPseudoClass("focused");
	// private static final PseudoClass MINIMIZED_PSEUDO_CLASS = PseudoClass.getPseudoClass("minimized");
	// private static final PseudoClass MAXIMIZED_PSEUDO_CLASS = PseudoClass.getPseudoClass("maximized");
	// private static final PseudoClass CLOSED_PSEUDO_CLASS = PseudoClass.getPseudoClass("closed");

	public static final String DEFAULT_RESOURCE_NAME = "window-box-frame";
	public static final String DEFAULT_STYLE_CLASS = "window";

	private final int BORDER_WIDTH = 5;

	private final Stage stage;

	private Region content;
	private String title;

	public WindowFrame()
	{
		this(new Stage());
	}

	public WindowFrame(Stage stage)
	{
		this.stage = stage;
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
		this.getChildren().add(content);

		stage.focusedProperty().addListener((observable, oldValue, newValue) ->
		{
			this.pseudoClassStateChanged(PseudoClass.getPseudoClass("focused"), newValue);
		});

		stage.getIcons().add(ResourceUtils.getImage("program"));
		stage.setScene(new WindowScene(this));
		stage.setTitle(title);
		stage.toFront();
	}

	public void close()
	{
		stage.close();
	}

	public void display()
	{
		stage.show();
	}

	public void show()
	{
		stage.show();
	}
}