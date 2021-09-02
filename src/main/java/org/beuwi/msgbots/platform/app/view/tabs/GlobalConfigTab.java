package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.openapi.Project;
import org.beuwi.msgbots.manager.ProjectManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.gui.control.ConfigView;
import org.beuwi.msgbots.platform.gui.control.NaviItem;
import org.beuwi.msgbots.platform.gui.control.NaviView;
import org.beuwi.msgbots.platform.gui.control.TabItem;

import java.util.ArrayList;
import java.util.List;

public class GlobalConfigTab extends TabItem implements View {
	private static GlobalConfigTab instance = null;

	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;

	@FXML
	private NaviView control;

	private GlobalConfigTab() {
		loader = new FormLoader();
		loader.setName("global-config-tab");
		loader.setController(this);
		loader.setRoot(this);
		loader.load();

		namespace = loader.getNamespace();
		// root = loader.getRoot();

		// Program().getInstance().refresh();
		// Keymap().getInstance().refresh();
		// Design().getInstance().refresh();
		new Bots().refresh();
	}

	private class Bots {
		private final NaviItem root;
		private final NaviView control;

		public Bots() {
			root = getNaviView().getTab("Bots");
			control = (NaviView) root.getContent();
			// FileManager.link(SharedValues.getFile("BOT_FOLDER_FILE"), this::refresh);
		}

		private void refresh() {
			List<NaviItem> list = new ArrayList<>();
			for (Project project : ProjectManager.getList()) {
				FormLoader loader = new FormLoader();
				loader.setName("bot-option-view");
				loader.load();

				String name = project.getName();
				ConfigView control = loader.getRoot();
				/* for (OptionItem item : control.getItems()) {
					item.setAddress(item.getAddress().replace("{$name}", name));
				} */
				control.setTitle(name);
				list.add(new NaviItem(name, control));
			}
			control.addTab(list);
		}
	}

	@Override
	public Object findById(String id) {
		return namespace.get(id);
	}

	public NaviView getNaviView() {
		return control;
	}

	public static GlobalConfigTab getInstance() {
		if (instance == null) {
			instance = new GlobalConfigTab();
		}
		return instance;
	}
}