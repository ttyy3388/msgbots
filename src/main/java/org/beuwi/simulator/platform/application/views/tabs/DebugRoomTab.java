package org.beuwi.simulator.platform.application.views.tabs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.SendChatMessageAction;

public class DebugRoomTab
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(DebugRoomTab.class.getResource("/forms/DebugRoomTab.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

        root = loader.getRoot();

        ChatArea.initialize();
        LogArea.initialize();
	}

	private static class ChatArea
	{
		public static void initialize()
		{
			TextArea textArea = (TextArea) nameSpace.get("txaChatInput");
			Button   button   = (Button) nameSpace.get("btnChatSend");

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
	}

	private static class LogArea
	{
		public static void initialize()
		{

		}
	}

	public static AnchorPane getRoot()
	{
		return root;
	}

	public static SplitPane getComponent()
	{
		return (SplitPane) root.getChildren().get(0);
	}

	public static ListView getChatListView()
	{
		return (ListView) nameSpace.get("lsvChatArea");
	}

	public static ListView getLogListView()
	{
		return (ListView) nameSpace.get("lsvLogArea");
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}