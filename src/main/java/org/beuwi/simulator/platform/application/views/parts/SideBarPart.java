package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.application.actions.ResizeSideBarAction;

public class SideBarPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane anpSideBar;
	private static StackPane stpResizeBar;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SideBarPart.class.getResource("/forms/SideBarPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anpSideBar = (AnchorPane) nameSpace.get("anpSideBar");
		stpResizeBar = (StackPane) nameSpace.get("stpResizeBar");

		stpResizeBar.setOnMouseDragged(event ->
		{
			ResizeSideBarAction.update(event);
		});
	}

	public static AnchorPane getRoot()
	{
		return anpSideBar;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}