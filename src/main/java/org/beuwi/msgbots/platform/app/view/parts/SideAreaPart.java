package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.platform.app.view.tabs.BotListTab;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

public class SideAreaPart extends StackPane implements View {
	private static SideAreaPart instance = null;

	// private static final int MAX_WIDTH = 000;
	private static final int TOGGLE_WIDTH = 100;

	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;

	@FXML private TabView tabView;

	private SideAreaPart() {
		loader = new FormLoader();
		loader.setName("side-area-part");
		loader.setController(this);
		loader.setRoot(this);
		loader.load();

		namespace = loader.getNamespace();
		// root = loader.getRoot();

		/* Pane resizebar = getResizeBar();
		// 더블 클릭 시 토글 액션
		resizebar.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (event.getClickCount() >= 2) {
				ToggleSideAreaAction.getInstance().execute();
			}
		});
		// 해당 너비 이하로 드래그 시 토글 액션
		resizebar.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
			double value = event.getSceneX();
			if (value < TOGGLE_WIDTH) {
				ToggleSideAreaAction.getInstance().execute();
			}
		}); */

		tabView.addTab(BotListTab.getInstance());
	}

	@Override
	public Object findById(String id) {
		return namespace.get(id);
	}

	public TabView getTabView() {
		return tabView;
	}

	public static SideAreaPart getInstance() {
		if (instance == null) {
			instance = new SideAreaPart();
		}
		return instance;
	}
}
