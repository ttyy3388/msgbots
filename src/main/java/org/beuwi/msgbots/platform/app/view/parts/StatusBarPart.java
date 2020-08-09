package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class StatusBarPart implements View
{
	private static ObservableMap<String, Object> nameSpace;

	private static Pane root;

	@Override
	public void init() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("StatusBarPart"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();
	}

	public static Pane getRoot()
	{
		return root;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}