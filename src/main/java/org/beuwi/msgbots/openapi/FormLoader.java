package org.beuwi.msgbots.openapi;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.util.ResourceUtils;

import java.net.URL;

public class FormLoader extends FXMLLoader
{
	public FormLoader(String name)
	{
		this("", name);
	}

	public FormLoader(String type, String name)
	{
		this(type, name, null);
	}

	public FormLoader(String type, String name, Object controller)
	{
		URL location = ResourceUtils.getForm((type != "" ? (type + "/") : "") + name);

		setLocation(location);
		setController(controller);

		try
		{
			this.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public <T> T getComponent()
	{
		if (getRoot() instanceof Tab)
		{
			return (T) ((Tab) getRoot()).getContent();
		}

		return (T) ((Pane) getRoot()).getChildren().get(0);
	}
}