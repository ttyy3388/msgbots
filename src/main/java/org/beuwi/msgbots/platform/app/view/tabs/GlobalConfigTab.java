package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.Bot;
import org.beuwi.msgbots.platform.gui.control.Navi;
import org.beuwi.msgbots.platform.gui.control.NaviView;
import org.beuwi.msgbots.platform.gui.control.OptionView;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.enums.ConfigType;

public class GlobalConfigTab implements View
{
	private static ObservableMap<String, Object> namespace;

	private static FormLoader loader;

	private static Tab root;

	private static NaviView component;

	@Override
	public void init()
	{
		loader = new FormLoader("tab", "global-config-tab");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		new BotNaviTab().init();
	}

	private static class BotNaviTab implements View
	{
		private static Navi root;
		private static NaviView component;

		@Override
		public void init()
		{
			root = GlobalConfigTab.getComponent().getNavi("Bots");
			component = (NaviView) root.getContent();

			for (String name : FileManager.getBotNames())
			{
				final FormLoader loader = new FormLoader("view", "bot-option-view");

				OptionView control = loader.getRoot();

				control.setType(ConfigType.SCRIPT);
				control.setName(name);
				control.setTitle(name);

				component.addNavi(new Navi(name, control));
			}
		}
	}

	public static Tab getRoot()
	{
		return root;
	}

	public static NaviView getComponent()
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