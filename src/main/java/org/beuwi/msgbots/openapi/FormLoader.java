package org.beuwi.msgbots.openapi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.platform.app.view.actions.DisplayErrorDialogAction;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.layout.ScrollPane;
import org.beuwi.msgbots.platform.gui.layout.ShadowPane;
import org.beuwi.msgbots.platform.util.ResourceUtils;

import java.io.IOException;
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
		catch (IOException e) {
			DisplayErrorDialogAction.execute(e);
		}
	}

	public <T> T getComponent() {
		if (getRoot() instanceof TabItem) {
			return (T) ((TabItem) getRoot()).getContent();
		}
		if (getRoot() instanceof ScrollPane) {
			return (T) ((ScrollPane) getRoot()).getContent();
		}
		if (getRoot() instanceof ShadowPane) {
			return (T) ((ShadowPane) getRoot()).getContent();
		}
		/* if (getRoot() instanceof SideItem) {
			VBox item = ((SideItem) getRoot()).getContent();
			StackPane pane = (StackPane) item.getChildren().get(1);
			return (T) pane.getChildren().get(0);
		} */

		return (T) ((Pane) getRoot()).getChildren().get(0);
	}
}