package org.beuwi.msgbots.openapi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class FormLoader extends FXMLLoader
{
	public FormLoader(String name) throws Exception
	{
		setLocation(ResourceUtils.getForm(name));
        setController(null);
        load();
	}

	public Node getComponent()
	{
		return((Pane) getRoot()).getChildren().get(0);
	}
}