package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.actions.AddChatMessageAction;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class DebugRoomTab implements View
{
	private static ObservableMap<String, Object> nameSpace;

	private static VBox root;

	@Override
	public void init() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("debug-room-tab"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		ListView listView = (ListView) nameSpace.get("listView");
		TextArea textArea = (TextArea) nameSpace.get("textArea");
		Button button = (Button) nameSpace.get("button");

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

				AddChatMessageAction.execute(textArea.getText());
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
			AddChatMessageAction.execute(textArea.getText());
			textArea.requestFocus();
			textArea.clear();
		});

		textArea.requestFocus();
	}

	public static VBox getRoot()
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