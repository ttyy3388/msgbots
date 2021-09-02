package org.beuwi.msgbots.openapi;

import javafx.fxml.FXMLLoader;
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
		String type = data[data.length - 1];
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


	/* public <T extends Node> T findById(String id) {
		return (T) getNamespace().get(id);
	} */
}