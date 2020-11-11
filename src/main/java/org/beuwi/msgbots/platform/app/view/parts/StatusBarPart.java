package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.layout.AnchorPane;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.HBox;

public class StatusBarPart implements View
{
	private static ObservableMap<String, Object> namespace;

	private static FormLoader loader;

	private static AnchorPane root;

	private static HBox component;

	@Override
	public void init()
	{
		loader = new FormLoader("part", "status-bar-part");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();
	}

	public static AnchorPane getRoot()
	{
		return root;
	}

	public static HBox getComponent()
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