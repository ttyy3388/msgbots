package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;

public class StatusBarPart implements View
{
	private static ObservableMap<String, Object> nameSpace;

	private static FormLoader loader;

	private static Pane root;

	@Override
	public void init()
	{
		loader = new FormLoader("status-bar-part");
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