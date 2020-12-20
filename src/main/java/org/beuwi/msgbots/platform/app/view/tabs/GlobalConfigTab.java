package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.Bot;
import org.beuwi.msgbots.platform.gui.control.ComboBox;
import org.beuwi.msgbots.platform.gui.control.Navi;
import org.beuwi.msgbots.platform.gui.control.NaviView;
import org.beuwi.msgbots.platform.gui.control.OptionBox;
import org.beuwi.msgbots.platform.gui.control.OptionView;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.VBox;
import org.beuwi.msgbots.platform.gui.editor.Editor;
import org.beuwi.msgbots.platform.gui.enums.ConfigType;
import org.beuwi.msgbots.platform.gui.skins.OptionBoxSkin;
import org.beuwi.msgbots.platform.gui.skins.OptionViewSkin;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.setting.GlobalSettings;

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

		new DesignNaviTab().init();
		new BotNaviTab().init();
	}

	private static class DesignNaviTab implements View
	{
		private static Navi root;
		private static OptionView component;

		@Override
		public void init()
		{
			root = GlobalConfigTab.getComponent().getNavi("Design");
			component = (OptionView) root.getContent();

			ComboBox select = (ComboBox) ((OptionBox) component.getItems().get(0)).getContent();
			Editor editor = (Editor) component.getItems().get(1);

			editor.setLanguage("css");

			select.getSelectedItemProperty().addListener(event ->
			{
				String theme = (String) select.getSelectedItem();
				switch (theme)
				{
					case "dark" : editor.setFile(SharedValues.DARK_THEME_FILE); break;
					case "light" : editor.setFile(SharedValues.LIGHT_THEME_FILE); break;
				}
			});

			// Default select value : current theme
			select.select(GlobalSettings.getString("program:color_theme"));

			VBox.setVgrow(editor, Priority.ALWAYS);

			// component.getNavi("Light").setContent(new Editor(SharedValues.LIGHT_THEME_FILE));
			// component.getNavi("Black").setContent(new Editor(ResourceUtils.getFile("themes/dark.css")));
			// component.getNavi("White").setContent(new Editor(ResourceUtils.getFile("themes/dark.css")));
		}
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