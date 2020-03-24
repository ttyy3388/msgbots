package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.SelectActivityBarAction;

public class ActivityBarPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane anpActivityBar;

	private static ToggleButton tgnExplorerTab;
	private static ToggleButton tgnDebugTab;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ActivityBarPart.class.getResource("/forms/ActivityBarPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anpActivityBar = (AnchorPane) nameSpace.get("anpActivityBar");
		tgnExplorerTab = (ToggleButton) nameSpace.get("tgnExplorerTab");
		tgnDebugTab    = (ToggleButton) nameSpace.get("tgnDebugTab");

		tgnExplorerTab.setOnAction(event ->
		{
			SelectActivityBarAction.update(0);
		});

		tgnDebugTab.setOnAction(event ->
		{
			SelectActivityBarAction.update(1);
		});
	}

	public static AnchorPane getRoot()
	{
		return anpActivityBar;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}