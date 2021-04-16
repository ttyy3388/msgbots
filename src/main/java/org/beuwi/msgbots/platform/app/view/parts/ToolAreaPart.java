package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.tabs.DetailLogTab;
import org.beuwi.msgbots.platform.app.view.tabs.ProblemListTab;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.layout.AnchorPane;
import org.beuwi.msgbots.platform.gui.layout.ResizePane;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

public class ToolAreaPart implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static ResizePane root;
	private static TabView component;

	// private static StackPane resizebar;

	// private static HBox buttonbar;

	@Override
	public void init() {
		loader = new FormLoader("part", "tool-area-part");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		// Resize Bar
		/* resizebar = (StackPane) namespace.get("stpResizeBar");
		resizebar.setOnMouseDragged(event -> {
			System.out.println("Height:" + MainView.getStage().getHeight());
			System.out.println("SceneY:" + event.getSceneY());
			// 메인 윈도우의 높이에서 Y값을 빼야 아래 기준으로 높이가 나옴
			double size = MainView.getStage().getHeight() - event.getSceneY();
			System.out.println("Rheight:" + root.getHeight());
			System.out.println("SHeight:" + size);

			// Default Min Width
			if (root.getMinHeight() < size) {
				root.setPrefHeight(size);
			}
		}); */

		// Button Bar
		/* buttonbar = (HBox) namespace.get("hbxButtonBar");
		Button reload = (Button) buttonbar.getChildren().get(0);
		reload.setGraphic(AllSVGIcons.get("Bot.Reload")); */

		component.setTab(
			// NoticeListTab.getRoot(),
			DetailLogTab.getRoot(),
			ProblemListTab.getRoot()
		);
		component.selectTab(DetailLogTab.getRoot());
	}

	public static ResizePane getRoot() {
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
