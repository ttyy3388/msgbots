package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.actions.AddChatMessageAction;
import org.beuwi.msgbots.platform.gui.control.ChatView;
import org.beuwi.msgbots.platform.gui.control.LogView;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabPane;

public class DebugAreaPart implements View
{
	private static final int MAX_WIDTH = 000;
	private static final int MIN_WIDTH = 350;

	private static ObservableMap<String, Object> nameSpace;

	private static FormLoader loader;

	private static AnchorPane root;

	private static TabPane component;

	private static Pane resize;

	@Override
	public void init()
	{
		loader = new FormLoader("debug-area-part");
		nameSpace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		// Resize Bar
		resize = (Pane) nameSpace.get("stpResizeBar");

		resize.setOnMouseDragged(event ->
		{
			double size = MainView.getStage().getWidth() - (event.getSceneX() + 16);

			if (MIN_WIDTH < size)
			{
				root.setPrefWidth(size);
			}
		});

		new DebugRoomTab().init();
		new GlobalLogTab().init();

		component.setSelectedTab(DebugRoomTab.getRoot());
	}

	public static class DebugRoomTab implements View
	{
		private static Tab root;

		private static ChatView listView;
		private static TextArea textArea;

		@Override
		public void init()
		{
			root = component.getTab(0);

			listView = (ChatView) nameSpace.get("lsvChatView");
			textArea = (TextArea) nameSpace.get("txaChatInput");

			textArea.addEventFilter(KeyEvent.KEY_PRESSED, event ->
			{
				if (event.getCode().equals(KeyCode.ENTER))
				{
					if (event.isShiftDown())
					{
						textArea.appendText(System.lineSeparator());
						event.consume();
						return ;
					}

					String text = textArea.getText();

					if (text.trim().isEmpty())
					{
						if (text.isEmpty())
						{
							textArea.clear();
						}

						event.consume();
						return ;
					}

					// Action
					AddChatMessageAction.execute(text);

					textArea.clear();
					event.consume();
				}
			});

			textArea.requestFocus();
		}

		public static Tab getRoot()
		{
			return root;
		}

		public static ChatView getComponent()
		{
			return listView;
		}
	}

	public static class GlobalLogTab implements View
	{
		private static Tab root;

		private static LogView listView;

		@Override
		public void init()
		{
			root = component.getTab(1);

			listView = (LogView) nameSpace.get("lsvLogView");
		}

		public static Tab getRoot()
		{
			return root;
		}

		public static LogView getComponent()
		{
			return listView;
		}
	}

	public static AnchorPane getRoot()
	{
		return root;
	}

	public static TabPane getComponent()
	{
		return component;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}
