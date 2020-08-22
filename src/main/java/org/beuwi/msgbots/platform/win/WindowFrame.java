package org.beuwi.msgbots.platform.win;

import javafx.css.PseudoClass;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class WindowFrame extends StackPane
{
	private final Stage stage;

	private WindowType type;
	private Region content;
	private String title;

	/* public WindowFrame()
	{
		this(null);
	} */

	public WindowFrame(Stage stage)
	{
		this.stage = stage;
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

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setType(WindowType type)
	{
		this.type = type;
	}

	public void setContent(Region content)
	{
		this.content = content;
	}

	public void create()
	{
		switch (type)
		{
			case WINDOW : break;
			case DIALOG :
				/* stage.initModality(Modality.WINDOW_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.initOwner(MainView.getStage()); */
				break;
		}

		stage.focusedProperty().addListener((observable, oldValue, newValue) ->
		{
			pseudoClassStateChanged(PseudoClass.getPseudoClass("focused"), newValue);
		});

		stage.getIcons().add(ResourceUtils.getImage("program"));
		stage.setScene(new WindowScene(content));
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
}