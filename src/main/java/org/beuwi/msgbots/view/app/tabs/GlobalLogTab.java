package org.beuwi.msgbots.view.app.tabs;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.base.Dfile;
import org.beuwi.msgbots.base.JArray;
import org.beuwi.msgbots.base.JObject;
import org.beuwi.msgbots.base.Session;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.manager.ScriptManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.view.gui.control.ChatItem;
import org.beuwi.msgbots.view.gui.control.LogItem;
import org.beuwi.msgbots.view.gui.control.LogView;
import org.beuwi.msgbots.view.gui.control.TabItem;
import org.beuwi.msgbots.shared.SharedValues;

import java.util.ArrayList;
import java.util.List;

public class GlobalLogTab extends TabItem implements View {
	private static GlobalLogTab instance = null;

	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;

	@FXML
	private LogView control;

	private GlobalLogTab() {
		loader = new FormLoader();
		loader.setName("global-log-tab");
		loader.setRoot(this);
		loader.setController(this);
		loader.load();

		namespace = loader.getNamespace();
		// root = loader.getRoot();

		// Connect Session
		final Session session = Session.GLOBAL;
		session.setOnLogListener((type, data, date) -> {
			control.getItems().add(new LogItem(type, data, date));
		});

		// Initialize List
		List<LogItem> list = new ArrayList<>();
		Dfile dfile = SharedValues.getDfile("dfile.globalLog");
		JArray array = new JArray(dfile.getData());
		for (Object object : array) {
			JObject json = (JObject) object;
			if (!object.toString().equals("{}") || (json.size() > 0)) {
				list.add(new LogItem((JObject) object));
			}
		}
		control.getItems().setAll(list);
	}

	public LogView getLogView() {
		return control;
	}

	@Override
	public Object findById(String id) {
		return namespace.get(id);
	}

	public static GlobalLogTab getInstance() {
		if (instance == null) {
			instance = new GlobalLogTab();
		}
		return instance;
	}
}