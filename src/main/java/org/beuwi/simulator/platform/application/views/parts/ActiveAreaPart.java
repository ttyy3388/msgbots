package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.application.actions.ResizeSideBarAction;

public class ActiveAreaPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane anpActiveArea;
	private static HBox       hoxActiveArea;
	private static StackPane  stpResizeBar;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ActiveAreaPart.class.getResource("/forms/ActiveAreaPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anpActiveArea = (AnchorPane) nameSpace.get("anpActiveArea");
		hoxActiveArea = (HBox)		 nameSpace.get("hoxActiveArea");
		stpResizeBar  = (StackPane)  nameSpace.get("stpResizeBar");

		stpResizeBar.setOnMouseDragged(event ->
		{
			ResizeSideBarAction.update(event);
		});

		HBox.setHgrow(SideBarPart.getRoot(), Priority.ALWAYS);

		hoxActiveArea.getChildren().addAll
		(
			ActivityBarPart.getRoot(),
			SideBarPart.getRoot()
		);
	}

	public static AnchorPane getRoot()
	{
		return anpActiveArea;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}