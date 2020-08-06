package org.beuwi.msgbots.platform.app.view.parts;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;

import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class EditorAreaPart extends View
{
	private static SplitPane component;

	@Override
	public void init() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("EditorAreaPart"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();
	}

	public static SplitPane getComponent()
	{
		return (SplitPane) root.getChildren().get(0);
	}
}