package org.beuwi.simulator.platform.application.views.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class SettingsTab
{
    private static ObservableMap<String, Object> nameSpace;

    private static AnchorPane root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SettingsTab.class.getResource("/forms/SettingsTab.fxml"));
		loader.setController(null);
        loader.load();

        nameSpace = loader.getNamespace();

	    root = loader.getRoot();

		ListView<Label> lsvSettingTabs = (ListView) nameSpace.get("lsvSettingTabs");

		lsvSettingTabs.setCellFactory(param -> new ListCell<Label>()
		{
			@Override
			protected void updateItem(Label item, boolean empty)
			{
				super.updateItem(item, empty);

				if (item != null && !empty)
				{
					setGraphic(item);
					setCursor(Cursor.HAND);
				}
				else
				{
					setGraphic(null);
				}
			}
		});

		Label lblProgramTab = new Label("Program");
		Label lblDebugTab   = new Label("Debug");
		Label lblBotsTab    = new Label("Bots");

		lblProgramTab.setOnMousePressed(event ->
		{
			((AnchorPane) nameSpace.get("anpProgramTab")).toFront();
		});

		lblDebugTab.setOnMousePressed(event ->
		{
			((AnchorPane) nameSpace.get("anpDebugTab")).toFront();
		});

		lblBotsTab.setOnMousePressed(event ->
		{
			((AnchorPane) nameSpace.get("anpBotsTab")).toFront();
		});

		lsvSettingTabs.getItems().addAll(lblProgramTab, lblDebugTab, lblBotsTab);
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}

	public static TabPane getComponent()
    {
        return (TabPane) root.getChildren().get(0);
    }

	public static AnchorPane getRoot()
	{
		return root;
	}
}