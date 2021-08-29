package org.beuwi.msgbots.program.app.view.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.base.Dfile;
import org.beuwi.msgbots.base.JArray;
import org.beuwi.msgbots.base.JObject;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.program.app.impl.View;
import org.beuwi.msgbots.program.gui.control.LogItem;
import org.beuwi.msgbots.program.gui.control.LogView;
import org.beuwi.msgbots.program.gui.control.TabItem;
import org.beuwi.msgbots.utils.SharedValues;
import org.json.simple.JSONObject;

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

		// Initialize List
		List<LogItem> list = new ArrayList<>();
		Dfile dfile = SharedValues.getDfile("GLOBAL_LOG_DFILE");
		JArray array = new JArray(dfile.getData());
		for (Object object : array) {
			JSONObject json = (JSONObject) object;
			if (!object.toString().equals("{}") || (json.size() > 0)) {
				list.add(new LogItem((JSONObject) object));
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