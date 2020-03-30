package org.beuwi.simulator.platform.application.views.tabs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.application.actions.ResizeChatAreaAction;
import org.beuwi.simulator.platform.application.actions.SendChatMessageAction;

public class DebugRoomTab
{
	private static ObservableMap<String, Object> nameSpace;
	private static AnchorPane anpDebugRoom;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(DebugRoomTab.class.getResource("/forms/DebugRoomTab.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

        anpDebugRoom = (AnchorPane) nameSpace.get("anpDebugRoom");

		ChatArea.initialize();
		LogArea.initialize();
	}

	public static class ChatArea
	{
		// private static VBox voxChatArea;
		private static ListView lsvChatPart;
		private static TextArea txaChatInput;
		private static Button   btnChatSend;

		public static void initialize()
		{
            lsvChatPart  = (ListView) nameSpace.get("lsvChatPart");
            txaChatInput = (TextArea) nameSpace.get("txaChatInput");
            btnChatSend  = (Button) nameSpace.get("btnChatSend");

            txaChatInput.setOnKeyPressed(event ->
            {
                if (event.getCode().equals(KeyCode.ENTER))
                {
                    if (event.isShiftDown())
                    {
                        txaChatInput.appendText(System.lineSeparator());
                        event.consume();
                        return ;
                    }
                    else if (btnChatSend.isDisable())
                    {
                        event.consume();
                        return ;
                    }

                    SendChatMessageAction.update(txaChatInput.getText(), false);
					txaChatInput.clear();
                    event.consume();
                }
            });

            txaChatInput.textProperty().addListener((observable, oldString, newString) ->
            {
                btnChatSend.setDisable(newString.trim().isEmpty());
            });

            btnChatSend.setOnAction(event ->
            {
                SendChatMessageAction.update(txaChatInput.getText(), false);
                txaChatInput.requestFocus();
				txaChatInput.clear();
            });

            Platform.runLater(() ->
            {
                txaChatInput.requestFocus();
            });
		}
	}

	public static class LogArea
	{
		private static AnchorPane anpLogArea;
		private static StackPane stpResizeBar;

		public static void initialize()
		{
			anpLogArea = (AnchorPane) nameSpace.get("anpLogArea");
			stpResizeBar = (StackPane) nameSpace.get("stpResizeBar");

			stpResizeBar.setOnMouseDragged(event ->
			{
				ResizeChatAreaAction.update(event);
			});
		}
	}

	public static AnchorPane getRoot()
	{
		return anpDebugRoom;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}