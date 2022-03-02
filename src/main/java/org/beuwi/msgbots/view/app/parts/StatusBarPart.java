package org.beuwi.msgbots.view.app.parts;

import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.view.gui.layout.AnchorPane;

public class StatusBarPart extends AnchorPane implements View {
	private static StatusBarPart instance = null;

	private final ObservableMap<String, Node> namespace;
	private final FormLoader loader;

	private StatusBarPart() {
		loader = new FormLoader();
		loader.setName("status-bar-part");
		loader.setController(this);
		loader.setRoot(this);
		loader.load();

		namespace = loader.getNamespace();
		// root = loader.getRoot();
	}

	@Override
	public Node findById(String id) {
		return namespace.get(id);
	}

	public void setColor(Color value) {
		setBackground(
			new Background(
				new BackgroundFill(
					value,
					CornerRadii.EMPTY,
					Insets.EMPTY
				)
			)
		);
	}

	public static StatusBarPart getInstance() {
		if (instance == null) {
			instance = new StatusBarPart();
		}
		return instance;
	}
}