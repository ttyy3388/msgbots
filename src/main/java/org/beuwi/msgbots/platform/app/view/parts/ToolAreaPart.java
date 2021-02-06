package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.tabs.DetailLogTab;
import org.beuwi.msgbots.platform.app.view.tabs.NoticeListTab;
import org.beuwi.msgbots.platform.app.view.tabs.ProblemListTab;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.layout.AnchorPane;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.layout.VBox;

public class ToolAreaPart implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static AnchorPane root;
	private static TabView component;
	private static StackPane resizeBar;

	@Override
	public void init() {
		loader = new FormLoader("part", "tool-area-part");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		// Resize Bar
		resizeBar = (StackPane) namespace.get("stpResizeBar");
		resizeBar.setOnMouseDragged(event -> {
			double size = MainView.getStage().getHeight() - event.getSceneY();

			// Default Min Width
			if (root.getMinHeight() < size) {
				root.setPrefHeight(size);
			}
		});

		component.addTab(
			NoticeListTab.getRoot(),
			ProblemListTab.getRoot(),
			DetailLogTab.getRoot()
		);
		component.selectTab(DetailLogTab.getRoot());
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
