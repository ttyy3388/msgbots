package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.LogManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.LogView;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;
import org.beuwi.msgbots.platform.util.SharedValues;

public class ScriptLogTab implements View
{
	private static ObservableMap<String, Object> namespace;

	private static FormLoader loader;

	private static Tab root;

	private static LogView component;

	@Override
	public void init()
	{
		loader = new FormLoader("tab", "script-log-tab");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		/* FileManager.link(SharedValues.GLOBAL_LOG_FILE, () ->
		{
			component.getItems().setAll(LogManager.load(SharedValues.GLOBAL_LOG_FILE));
		}); */
	}

	public static Tab getRoot()
	{
		return root;
	}

	public static LogView getComponent()
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