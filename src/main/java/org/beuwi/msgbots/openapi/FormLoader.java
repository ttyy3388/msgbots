package org.beuwi.msgbots.openapi;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import org.beuwi.msgbots.utils.ResourceUtils;

import java.net.URL;

public class FormLoader extends FXMLLoader {
	/* public FormLoader(String name) {
		this(null, name);
	} */

	public FormLoader() {
		// setController(controller);

		/* try {
			this.load();
		}
		catch (Exception e) {
			e.printStackTrace();
		} */
	}

	public void setName(String name) {
		if (name == null) {
			throw new NullPointerException();
		}
		String data[] = name.split("-");
		String type = data[data.length - 1]; // 마지막 인덱스를 가져옴
		if (type == null) {
			throw new NullPointerException();
		}
		URL location = ResourceUtils.getForm(type + "/" + name);
		setLocation(location);
	}
	
	@Override
	public <T> T load() {
		T result = null;
		try {
			result = super.load();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/* public Node findById(String id) {
		return (Node) getNamespace().get(id);
	} */

	@Override
	public ObservableMap getNamespace() {
		return (ObservableMap) super.getNamespace();
	}
}