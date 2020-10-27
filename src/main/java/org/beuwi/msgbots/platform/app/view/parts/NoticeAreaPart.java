package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.layout.BorderPane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.NoticeView;

public class NoticeAreaPart implements View
{
	private static ObservableMap<String, Object> nameSpace;

	private static FormLoader loader;

	private static BorderPane root;

	private static NoticeView component;

	@Override
	public void init()
	{
		loader = new FormLoader("notice-area-part");
		nameSpace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		root.setMouseTransparent(true);
		root.setPickOnBounds(false);
		component.setPickOnBounds(false);
		component.setMouseTransparent(true);
	}

	public static BorderPane getRoot()
	{
		return root;
	}

	public static NoticeView getComponent()
	{
		return component;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}
