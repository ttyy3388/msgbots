package org.beuwi.simulator.platform.application.views.tabs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.SendChatMessageAction;

public class ChatRoomTab
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane root;

	private static ListView listView;
	private static TextArea textArea;
	private static Button button;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ChatRoomTab.class.getResource("/forms/ChatRoomTab.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

        root = loader.getRoot();

		listView  = (ListView) nameSpace.get("listView");
		textArea = (TextArea) nameSpace.get("textArea");
		button  = (Button) nameSpace.get("button");

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
				else if (button.isDisable())
				{
					event.consume();
					return ;
				}

				SendChatMessageAction.update(textArea.getText(), false);
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
			SendChatMessageAction.update(textArea.getText(), false);
			textArea.requestFocus();
			textArea.clear();
		});

		Platform.runLater(() ->
		{
			textArea.requestFocus();
		});
	}

	public static AnchorPane getRoot()
	{
		return root;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}