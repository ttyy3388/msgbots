package org.beuwi.msgbots.platform.win;

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
		getChildren().add(content);

		stage.getIcons().add(ResourceUtils.getImage("program"));
		stage.setTitle("Messenger Bot Simulator"); // Default Title
		stage.setScene(new WindowScene(this));
		stage.toFront();
	}

	public void show()
	{
		stage.show();
	}
}