package org.beuwi.simulator.platform.ui.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

public class WindowView extends WindowStage
{
	WindowController controller = null;

	Region root;
	Region content;
	String title;
	int    type;

	public void setWindowTitle(String title)
	{
		this.title = title;
	}

	public void setWindowContent(Region content)
	{
		this.content = content;
	}

	public void setWindowType(int type)
	{
		this.type = type;
	}

	public void createWindow()
	{
		controller = new WindowController(this, content, type);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/WindowView.fxml"));
		loader.setController(controller);

		try
		{
			root = loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		setScene(new WindowScene(root));

		controller.setWindowTitle(title);
	}
}