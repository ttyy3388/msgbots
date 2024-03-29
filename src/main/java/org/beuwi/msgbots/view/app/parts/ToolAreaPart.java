package org.beuwi.msgbots.view.app.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.Node;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.view.app.tabs.DetailLogTab;
import org.beuwi.msgbots.view.app.tabs.GlobalLogTab;
import org.beuwi.msgbots.view.gui.control.TabView;
import org.beuwi.msgbots.view.gui.layout.StackPane;

public class ToolAreaPart extends StackPane implements View {
	private static ToolAreaPart instance = null;

	// private static final int MAX_HIEGHT = 000;
	private static final int TOGGLE_HEIGHT = 100;

	private final ObservableMap<String, Node> namespace;
	private final FormLoader loader;

	@FXML private TabView tabView;

	private ToolAreaPart() {
		loader = new FormLoader();
		loader.setName("tool-area-part");
		loader.setController(this);
		loader.setRoot(this);
		loader.load();

		namespace = loader.getNamespace();
		// root = loader.getRoot();

		/* Pane resizebar = getResizeBar();
		// 더블 클릭 시 토글 액션
		resizebar.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (event.getClickCount() >= 2) {
				ToggleToolAreaAction.getInstance().execute();
			}
		});
		// 해당 너비 이하로 드래그 시 토글 액션
		resizebar.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
			Scene scene = getScene();
			double value = scene.getHeight() - event.getSceneY();
			if (value < TOGGLE_HEIGHT) {
				ToggleToolAreaAction.getInstance().execute();
			}
		}); */

		tabView.addTab(
			DetailLogTab.getInstance(),
			GlobalLogTab.getInstance()
		);
	}

	@Override
	public Node findById(String id) {
		return namespace.get(id);
	}

	public TabView getTabView() {
		return tabView;
	}

	public static ToolAreaPart getInstance() {
		if (instance == null) {
			instance = new ToolAreaPart();
		}
		return instance;
	}
}
