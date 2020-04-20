package org.beuwi.simulator.platform.application.views.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import org.beuwi.simulator.platform.application.actions.CopyAction;
import org.beuwi.simulator.platform.application.views.actions.AddChatMessageAction;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuItem;
import org.beuwi.simulator.platform.ui.components.ITextArea;

public class DebugRoomTab
{
	private static ObservableMap<String, Object> nameSpace;

	private static VBox root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(DebugRoomTab.class.getResource("/forms/DebugRoomTab.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

        root = loader.getRoot();

        ListView  listView = getComponent();
		ITextArea textArea = (ITextArea) nameSpace.get("textArea");
		Button    button   = (Button) nameSpace.get("button");

		textArea.setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				if (event.isShiftDown())
				{
					textArea.appendText(System.lineSeparator());
					event.consume();
					return ;
				}

				if (button.isDisable())
				{
					textArea.setText("");
					event.consume();
					return ;
				}

				AddChatMessageAction.update(textArea.getText(), false);
				textArea.clear();
				event.consume();
			}
		});

		textArea.textProperty().addListener((observable, oldString, newString) ->
		{
			button.setDisable(newString.trim().isEmpty());
		});

		button.setOnAction(event ->
		{
			AddChatMessageAction.update(textArea.getText(), false);
			textArea.requestFocus();
			textArea.clear();
		});

		textArea.requestFocus();

		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listView.setOnKeyPressed(event ->
		{
			if (event.isControlDown())
			{
				switch (event.getCode())
				{
					case C : CopyAction.update(listView.getSelectionModel().getSelectedItems()); break;
				}
			}
		});

		IContextMenu menu = new IContextMenu
		(
			new IMenuItem("Select All", event -> listView.getSelectionModel().selectAll())
		);

		menu.setNode(listView);
	}


	public static Node getRoot()
	{
		return root;
	}

	public static ListView getComponent()
	{
		return (ListView) root.getChildren().get(0);
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}