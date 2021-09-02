package org.beuwi.msgbots.view.app.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.view.app.tabs.DebugRoomTab;
import org.beuwi.msgbots.view.gui.control.TabView;
import org.beuwi.msgbots.view.gui.layout.StackPane;

public class DebugAreaPart extends StackPane implements View {
	private static DebugAreaPart instance = null;

	// private static final int MAX_WIDTH = 000;
	private static final int TOGGLE_WIDTH = 150;

	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;

	@FXML private TabView tabView;

	private DebugAreaPart() {
		loader = new FormLoader();
		loader.setName("debug-area-part");
		loader.setController(this);
		loader.setRoot(this);
		loader.load();

		namespace = loader.getNamespace();
		// root = loader.getRoot();

		/* Pane resizebar = getResizeBar();
		// 더블 클릭 시 토글 액션
		resizebar.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (event.getClickCount() >= 2) {
				ToggleDebugAreaAction.getInstance().execute();
			}
		});
		// 해당 너비 이하로 드래그 시 토글 액션
		resizebar.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
			Scene scene = getScene();
			double value = scene.getWidth() - event.getSceneX();
			if (value < TOGGLE_WIDTH) {
				ToggleDebugAreaAction.getInstance().execute();
			}
		}); */

		tabView.addTab(DebugRoomTab.getInstance());
	}

	@Override
	public Object findById(String id) {
		return namespace.get(id);
	}

	public TabView getTabView() {
		return tabView;
	}

	public static DebugAreaPart getInstance() {
		if (instance == null) {
			instance = new DebugAreaPart();
		}
		return instance;
	}
}
