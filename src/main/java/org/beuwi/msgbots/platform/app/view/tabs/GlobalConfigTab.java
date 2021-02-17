package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import javafx.scene.Node;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.NaviItem;
import org.beuwi.msgbots.platform.gui.control.NaviView;
import org.beuwi.msgbots.platform.gui.control.OptionItem;
import org.beuwi.msgbots.platform.gui.control.OptionView;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.util.ArrayList;
import java.util.List;

public class GlobalConfigTab implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static TabItem root;
	private static NaviView component;

	@Override
	public void init() {
		loader = new FormLoader("tab", "global-config-tab");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		// new DesignNaviItem().init();
		new BotsNaviItem().init();
	}

	/* private static class DesignNaviItem implements View {
		private static NaviItem root;

		@Override
		public void init() {
			root = GlobalConfigTab.getComponent().getTab("Design");

			FileManager.link(SharedValues.CUSTOM_THEME_FILE, () -> {
				SetColorThemeAction.execute(SharedValues.CUSTOM_THEME_FILE);
			});
		}
	} */

	private static class BotsNaviItem implements View {
		private static NaviItem root;
		private static NaviView component;

		@Override
		public void init() {
			root = GlobalConfigTab.getComponent().getTab("Bots");
			component = (NaviView) root.getContent();

			FileManager.link(SharedValues.BOTS_FOLDER_FILE, () -> {
				refresh();
			});
			
			refresh();
		}

		private void refresh() {
			List<NaviItem> addItems = new ArrayList<>();
			for (String name : FileManager.getBotNames()) {
				final FormLoader loader = new FormLoader("view", "bot-option-view");
				OptionView control = loader.getRoot();
				for (Node node : control.getItems()) {
					if (node instanceof OptionItem) {
						OptionItem item = (OptionItem) node;
						item.setAddress(item.getAddress().replace("{$name}", name));
					}
				}
				control.setTitle(name);

				addItems.add(new NaviItem(name, control));
			}
			component.addTab(addItems);
		}
	}

	public static TabItem getRoot() {
		return root;
	}

	public static NaviView getComponent() {
		return component;
	}

	public static <T> T getComponent(String key) {
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}