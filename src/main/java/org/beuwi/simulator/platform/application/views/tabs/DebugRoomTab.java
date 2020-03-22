package org.beuwi.simulator.platform.application.views.tabs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.SendDebugRoomChatAction;

public class DebugRoomTab
{
	private static ObservableMap<String, Object> nameSpace;
	private static AnchorPane anchorPane;
    private static TextArea textArea;
	private static Button button;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(DebugRoomTab.class.getResource("/forms/DebugRoomTab.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anchorPane = (AnchorPane) nameSpace.get("anpDebugRoom");
		textArea = (TextArea) nameSpace.get("txaChatInput");
		button = (Button) nameSpace.get("btnChatSend");

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

                // 1 : Sender
                SendDebugRoomChatAction.update(textArea.getText(), 1);
                event.consume();
            }
        });

        textArea.textProperty().addListener((observable, oldString, newString) ->
        {
            button.setDisable(newString.trim().isEmpty());
        });

        button.setOnAction(event ->
        {
            // 1 : Sender
            SendDebugRoomChatAction.update(textArea.getText(), 1);
            textArea.requestFocus();
        });

        Platform.runLater(() ->
        {
            textArea.requestFocus();
        });
	}

	public static AnchorPane getRoot()
	{
		return anchorPane;
	}

	public static Object getComponent()
	{
		return null;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}