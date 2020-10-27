package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.Option;
import org.beuwi.msgbots.platform.gui.control.OptionView;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabPane;
import org.beuwi.msgbots.platform.gui.control.VBox;

public class GlobalSettingTab implements View
{
	private static ObservableMap<String, Object> nameSpace;

	private static FormLoader loader;

	private static Tab root;

	private static TabPane component;

	@Override
	public void init()
	{
		loader = new FormLoader("global-setting-tab");
		nameSpace = loader.getNamespace();
		root = loader.getRoot();
		component = (TabPane) nameSpace.get("tapGlobalSetting");;

		new ProgramOptionTab().init();
		// new DebugOptionTab().init();
	}

	public static class ProgramOptionTab implements View
	{
		private static Tab root;
		private static OptionView component;

		@Override
		public void init()
		{
			root = GlobalSettingTab.component.getTab(0);
			component = (OptionView) root.getContent();
		}

		public static void refresh()
		{

		}

		public static Tab getRoot()
		{
			return root;
		}

		public static OptionView getComponent()
		{
			return component;
		}
	}

	public static class DebugOptionTab implements View
	{
		private static Tab root;
		private static OptionView component;

		@Override
		public void init()
		{
			root = GlobalSettingTab.component.getTab(1);
			component = (OptionView) root.getContent();

            for (String name : FileManager.getBotNames())
            {
                component.addOption(new Option(name, new BotOptionPane(name)));
            }
		}

		public static void refresh()
		{
			for (String name : FileManager.getBotNames())
			{
				component.addOption(new Option(name, new BotOptionPane(name)));
			}
		}

		public static Tab getRoot()
		{
			return root;
		}

		public static OptionView getComponent()
		{
			return component;
		}
	}

	private static class BotOptionPane extends StackPane
	{
		private ObservableMap<String, Object> nameSpace;

		private FormLoader loader;

		private VBox root;

		public BotOptionPane(String name)
		{
			loader = new FormLoader("bot-option-pane");
			nameSpace = loader.getNamespace();
			root = loader.getRoot();

			getChildren().add(root);
		}
	}

	public static Tab getRoot()
	{
		return root;
	}

	public static TabPane getComponent()
	{
		return component;
	}
}