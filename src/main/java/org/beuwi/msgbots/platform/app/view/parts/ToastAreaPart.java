package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.ToastView;
import org.beuwi.msgbots.platform.gui.layout.AnchorPanel;

public class ToastAreaPart implements View
{
	private static ObservableMap<String, Object> namespace;

	private static FormLoader loader;

	private static AnchorPanel root;

	private static ToastView component;

	@Override
	public void init()
	{
		loader = new FormLoader("part", "toast-area-part");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		root.setBackground(null);
		// root.setMouseTransparent(true);
		root.setPickOnBounds(false);
		component.setPickOnBounds(false);
	}

	public static AnchorPanel getRoot()
	{
		return root;
	}

	public static ToastView getComponent()
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
