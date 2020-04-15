package org.beuwi.simulator.platform.application.views.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import org.beuwi.simulator.platform.application.views.actions.SendChatMessageAction;

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

		TextArea textArea = (TextArea) nameSpace.get("textArea");
		Button   button   = (Button) nameSpace.get("button");

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

		textArea.requestFocus();
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