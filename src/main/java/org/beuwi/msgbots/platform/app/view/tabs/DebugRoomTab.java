package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.platform.gui.control.ChatArea;
import org.beuwi.msgbots.platform.gui.control.ChatView;
import org.beuwi.msgbots.platform.gui.control.TabItem;

public class DebugRoomTab extends TabItem implements View {
	private static final int MAX_TEXT_LENGTH = 5000;

	private static DebugRoomTab instance = null;

	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;

	@FXML private ChatArea control;
	private final ChatView view;

	private DebugRoomTab() {
		loader = new FormLoader();
		loader.setName("debug-room-tab");
		loader.setController(this);
		loader.setRoot(this);
		loader.load();

		namespace = loader.getNamespace();
		// root = loader.getRoot();
		view = control.getChatView();

		/* final Session session = Session.GLOBAL;

		session.setOnChatListener(message -> {
			view.getItems().add(new ChatItem(message, true));
		});

		view.getItems().addListener((ListChangeListener<ChatItem>) change -> {
			while (change.next()) {
				// getAddedSubList로 하면 무한루프가 작동하므로
				// 추가를 감지해서 마지막 아이템만 가져옴
				if (change.wasAdded()) {
					List<ChatItem> list = view.getItems();
					ChatItem item = list.get(list.size() - 1);
					// 유저가 전송한 메시지면 실행
					if (!item.isBot()) {
						ScriptManager.run(item.getMessage());
					}
				}
			}
		}); */
	}

	@Override
	public Object findById(String id) {
		return namespace.get(id);
	}

	public static DebugRoomTab getInstance() {
		if (instance == null) {
			instance = new DebugRoomTab();
		}
		return instance;
	}
}