package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.tabs.DebugRoomTab;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalLogTab;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.layout.AnchorPanel;

public class DebugAreaPart implements View
{
	private static final int MAX_WIDTH = 000;
	private static final int MIN_WIDTH = 350;

	private static ObservableMap<String, Object> namespace;

	private static FormLoader loader;

	private static AnchorPanel root;

	private static TabView component;

	private static Pane resize;

	@Override
	public void init()
	{
		loader = new FormLoader("part", "debug-area-part");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		// Resize Bar
		resize = (Pane) namespace.get("stpResizeBar");

		resize.setOnMouseDragged(event ->
		{
			double size = MainView.getStage().getWidth() - (event.getSceneX() + 16);

			if (MIN_WIDTH < size)
			{
				root.setPrefWidth(size);
			}
		});

		component.addTabs(DebugRoomTab.getRoot(), GlobalLogTab.getRoot());

		component.select(DebugRoomTab.getRoot());
	}

	public static AnchorPanel getRoot()
	{
		return root;
	}

	public static TabView getComponent()
	{
		return component;
	}

	public static <T> T getComponent(String key)
	{
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace()
	{
		return namespace;
	}
}
