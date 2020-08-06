package org.beuwi.msgbots.platform.app.impl;

import javafx.collections.ObservableMap;
import javafx.scene.layout.AnchorPane;

public abstract class View
{
	/* public View() throws Exception
	{
		this("");
	}

	public View(String name) throws Exception
	{
		if (name != null)
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ResourceUtils.getForm(name));
			loader.setController(null);
			loader.load();

			nameSpace = loader.getNamespace();

			root = loader.getRoot();
		}
		else
		{
			return ;
		}
	} */

    public void init() throws Exception {

    }

    public static ObservableMap<String, Object> nameSpace;

	public static AnchorPane root;

	public static AnchorPane getRoot()
	{
		return root;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}