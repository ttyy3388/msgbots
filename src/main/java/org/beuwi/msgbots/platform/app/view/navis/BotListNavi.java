package org.beuwi.msgbots.platform.app.view.navis;

import javafx.collections.ObservableMap;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.BotView;
import org.beuwi.msgbots.platform.gui.control.Navi;

public class BotListNavi implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static Navi root;
	private static BotView component;

	@Override
	public void init()
	{
		loader = new FormLoader("navi", "bot-list-navi");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();
	}

	public static Navi getRoot()
	{
		return root;
	}

	public static BotView getComponent()
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