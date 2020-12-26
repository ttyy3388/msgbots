package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.navis.BotListNavi;
import org.beuwi.msgbots.platform.gui.control.NaviView;
import org.beuwi.msgbots.platform.gui.layout.AnchorPanel;

public class ActiveAreaPart implements View {
	// private static final int DEFAULT_MAX_WIDTH = 000;
	// private static final int DEFAULT_MIN_WIDTH = 180;

	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static AnchorPanel root;
	private static NaviView component;
	private static Pane resizeBar;

	@Override
	public void init() {
		loader = new FormLoader("part", "active-area-part");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		resizeBar = (Pane) namespace.get("stpResizeBar");
		resizeBar.setOnMouseDragged(event -> {
			double size = event.getSceneX();
			if (root.getMinWidth() < size) {
				root.setPrefWidth(size);
			}
		});

		component.addNavi(BotListNavi.getRoot());

		/* HBox<Button> btnbar = (HBox) component.getButtonBar();

		ToggleButton check = new ToggleButton(); // Show Compiled Check Box
		ToggleButton power = new ToggleButton(); // Show Power Switch
		Button compile = new Button(); 		 	 // Show Compile Button

		compile.setGraphic(AllSVGIcons.get("Bot.Compile"));

		btnbar.addItem(check, power, compile); */
	}

	public static AnchorPanel getRoot() {
		return root;
	}

	public static NaviView getComponent() {
		return component;
	}

	public static <T> T getComponent(String key) {
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}
