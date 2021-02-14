package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.layout.AnchorPane;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.tabs.DebugRoomTab;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalLogTab;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

public class DebugAreaPart implements View {
	// private static final int DEFAULT_MAX_WIDTH = 000;
	// private static final int DEFAULT_MIN_WIDTH = 350;

	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static AnchorPane root;
	private static TabView component;

	private static StackPane resizebar;

	@Override
	public void init() {
		loader = new FormLoader("part", "debug-area-part");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		// Resize Bar
		resizebar = (StackPane) namespace.get("stpResizeBar");
		resizebar.setOnMouseDragged(event -> {
			double size = MainView.getStage().getWidth() - (event.getSceneX() + 16);

			// Default Min Width
			if (root.getMinWidth() < size) {
				root.setPrefWidth(size);
			}
		});

		component.setTab(
			DebugRoomTab.getRoot(),
			GlobalLogTab.getRoot()
		);
		component.selectTab(DebugRoomTab.getRoot());
	}

	public static AnchorPane getRoot() {
		return root;
	}

	public static TabView getComponent() {
		return component;
	}

	public static Object getComponent(String key) {
		return namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}
