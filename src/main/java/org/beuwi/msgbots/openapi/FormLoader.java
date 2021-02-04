package org.beuwi.msgbots.openapi;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.util.ResourceUtils;

import java.net.URL;

public class FormLoader extends FXMLLoader {
	public FormLoader(String name) {
		this("", name);
	}

	public FormLoader(String type, String name) {
		this(type, name, null);
	}

	public FormLoader(String type, String name, Object controller) {
		URL location = ResourceUtils.getForm((type != "" ? (type + "/") : "") + name);

		setLocation(location);
		setController(controller);

		try {
			this.load();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> T getComponent() {
		if (getRoot() instanceof TabItem) {
			return (T) ((TabItem) getRoot()).getContent();
		}
		/* if (getRoot() instanceof SideItem) {
			VBox item = ((SideItem) getRoot()).getContent();
			StackPane pane = (StackPane) item.getChildren().get(1);
			return (T) pane.getChildren().get(0);
		} */

		return (T) ((Pane) getRoot()).getChildren().get(0);
	}
}