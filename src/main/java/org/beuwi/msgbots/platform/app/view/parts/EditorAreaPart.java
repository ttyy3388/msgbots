package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.area.EditorPane;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class EditorAreaPart implements View
{
	private static ObservableMap<String, Object> nameSpace;

	private static EditorPane root;

	@Override
	public void init() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("editor-area-part"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();
	}

	public static EditorPane getRoot()
	{
		return root;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}