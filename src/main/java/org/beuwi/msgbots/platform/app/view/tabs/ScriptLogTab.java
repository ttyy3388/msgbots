package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

public class ScriptLogTab implements View
{
	private static ObservableMap<String, Object> namespace;

	private static FormLoader loader;

	private static Tab root;

	private static StackPanel component;

	@Override
	public void init()
	{
		loader = new FormLoader("tab", "global-log-tab");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();
	}

	public static Tab getRoot()
	{
		return root;
	}

	public static StackPanel getComponent()
	{
		return component;
	}

	public static <T> T getComponent(String key)
	{
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace()
	{
		return namespace;
	}
}