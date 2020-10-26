package org.beuwi.msgbots.openapi;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class FormLoader extends FXMLLoader
{
	public FormLoader(String name)
	{
		setLocation(ResourceUtils.getForm(name));
		setController(null);

		try
		{
			this.load();
		}
		catch (Exception e)
		{
			// new ShowToastMessage(e);
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