package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;

import javafx.scene.Node;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.BotView;
import org.beuwi.msgbots.platform.gui.control.ContextMenu;
import org.beuwi.msgbots.platform.gui.control.MenuItem;
import org.beuwi.msgbots.platform.gui.control.NaviItem;
import org.beuwi.msgbots.platform.gui.control.NaviView;
import org.beuwi.msgbots.platform.gui.control.OptionItem;
import org.beuwi.msgbots.platform.gui.control.OptionView;
import org.beuwi.msgbots.platform.gui.control.Separator;
import org.beuwi.msgbots.platform.gui.control.TabItem;

public class BotListTab implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static TabItem root;
	private static BotView component;

	private final ContextMenu menu = new ContextMenu(
		new MenuItem("Reload Bot List"),
		new Separator(),
		new MenuItem("Show Compile Button"),
		new MenuItem("Show Power Switch")
	);

	@Override
	public void init() {
		loader = new FormLoader("tab", "bot-list-tab");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		/* Button button = root.getButton();

		button.setVisible(true);
		button.setDisable(false);
		button.setGraphic(AllSVGIcons.get("Tab.More"));
		button.setOnAction(null);

		menu.setNode(button); */

		new BotNaviItem().init();
	}

	private static class BotNaviItem implements View {
		private static NaviItem root;
		private static NaviView component;

		@Override
		public void init() {
			root = GlobalConfigTab.getComponent().getTab("Bots");
			component = (NaviView) root.getContent();

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

				component.addTab(new NaviItem(name, control));
			}
		}
	}

	public static TabItem getRoot() {
		return root;
	}

	public static BotView getComponent() {
		return component;
	}

	public static <T> T getComponent(String key) {
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}