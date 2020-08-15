package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.LogItem;
import org.beuwi.msgbots.platform.gui.control.LogType;
import org.beuwi.msgbots.platform.gui.control.LogView;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class GlobalLogTab implements View
{
	private static ObservableMap<String, Object> nameSpace;

	private static StackPane root;

	@Override
	public void init() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("global-log-tab"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		getComponent().addItem(new LogItem("TEST ERROR ITEM", "2020/8/12 19:39:00", LogType.ERROR));
		getComponent().addItem(new LogItem("TEST ERROR ITEM", "2020/8/12 19:39:00", LogType.ERROR));
		getComponent().addItem(new LogItem("TEST DEBUG ITEM", "2020/8/12 19:39:00", LogType.DEBUG));
		getComponent().addItem(new LogItem("TEST DEBUG ITEM", "2020/8/12 19:39:00", LogType.DEBUG));
		getComponent().addItem(new LogItem("TEST EVENT ITEM", "2020/8/12 19:39:00", LogType.EVENT));
		getComponent().addItem(new LogItem("TEST EVENT ITEM", "2020/8/12 19:39:00", LogType.EVENT));
	}

	public static StackPane getRoot()
	{
		return root;
	}

	public static LogView getComponent()
	{
		return (LogView) root.getChildren().get(0);
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}
