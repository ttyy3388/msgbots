package org.beuwi.simulator.platform.ui.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.beuwi.simulator.utils.ResourceUtils;

public class IWindowView extends StackPane
{
	private IWindowController controller;

	private IWindowType type;
	private Stage stage;
	private Region content;
	private String title;

	public IWindowView()
	{
		controller = new IWindowController(this);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("WindowView"));
		loader.setController(controller);

		try
		{
			loader.load();
		}
		catch (Exception e)
		{
			return ;
		}
	}

	public Stage getStage()
	{
		return stage;
	}

	public IWindowType getType()
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
		controller.create();
	}
}