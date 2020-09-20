package org.beuwi.msgbots.openapi;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class FormLoader extends FXMLLoader
{
	public FormLoader(String name)
	{
		setLocation(ResourceUtils.getForm(name));
		setController(null);

		try
		{
			load();
		}
		catch (Exception e)
		{
			// new ShowToastMessage(e);
			e.printStackTrace();
		}
	}

	public <T> T getComponent()
	{
		return (T) ((Pane) getRoot()).getChildren().get(0);
	}
}