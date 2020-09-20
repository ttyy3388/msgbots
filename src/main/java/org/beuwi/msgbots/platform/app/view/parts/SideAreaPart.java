package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.BotView;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabPane;

public class SideAreaPart implements View
{
	private static final int MAX_WIDTH = 000;
	private static final int MIN_WIDTH = 180;

	private static ObservableMap<String, Object> nameSpace;

	private static FormLoader loader;

	private static AnchorPane root;

	private static TabPane component;

	private static Pane resize;

	@Override
	public void init()
	{
		loader = new FormLoader("side-area-part");
		nameSpace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		// Resize Bar
		resize = (Pane) nameSpace.get("stpResizeBar");

		resize.setOnMouseDragged(event ->
		{
			double size = event.getSceneX();

			if (MIN_WIDTH < size)
			{
				root.setPrefWidth(size);
			}
		});

		new BotListTab().init();
	}

	public static class BotListTab implements View
	{
		private static Tab root;

		private static BotView component;

		@Override
		public void init()
		{
			root = (Tab) nameSpace.get("tabBotList");
			component = (BotView) nameSpace.get("lsvBotView");
		}

		public static Tab getRoot()
		{
			return root;
		}

		public static BotView getComponent()
		{
			return component;
		}
	}

	public static AnchorPane getRoot()
	{
		return root;
	}

	public static TabPane getComponent()
	{
		return component;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}
