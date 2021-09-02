package org.beuwi.msgbots.platform.gui.layout;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.Project;
import org.beuwi.msgbots.openapi.Session;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.gui.control.ChatArea;
import org.beuwi.msgbots.platform.gui.control.ChatItem;
import org.beuwi.msgbots.platform.gui.control.ChatView;
import org.beuwi.msgbots.platform.gui.control.LogView;
import org.beuwi.msgbots.platform.gui.control.SplitView;
import org.beuwi.msgbots.platform.gui.editor.Editor;

import java.util.List;

public class DebugPane extends SplitView {
	private final FormLoader loader;

	/* @FXML private Label lblRoomName;
	@FXML private HBox<Button> hbxToolBar;
	@FXML private ToggleButton tgnBotPower;
	@FXML private Button btnBotCompile;
	@FXML private Button btnListClear; */

	@FXML private Editor editor;
	@FXML private LogView logView;
	@FXML private ChatArea chatArea;
	// @FXML private OptionView settings;
	private ChatView chatView;

	private final Session session;

	public DebugPane(Project project) {
		loader = new FormLoader();
		loader.setName("debug-pane-layout");
		loader.setController(this);
		loader.setRoot(this);
		loader.load();

		if (project == null) {
			throw new NullPointerException();
		}

		session = project.getSession();

		logView.load(project);
		/* String name = project.getName();
		for (OptionItem item : settings.getItems()) {
			item.setAddress(item.getAddress().replace("{$name}", name));
		} */

		editor.setFile(project.getFile("index.js"));
		// editor.setLanguage("javascript");

		// 세션에서 챗이 오는 경우는 무조건 봇이 보내는 경우만 존재함
		/* session.setOnChatListener((message) -> {
			chatView.getItems().add(new ChatItem(message, true));
		});

		chatView = chatArea.getChatView();
		chatView.getItems().addListener((ListChangeListener<ChatItem>) change -> {
			while (change.next()) {
				// getAddedSubList로 하면 무한루프가 작동하므로
				// 추가를 감지해서 마지막 아이템만 가져옴
				if (change.wasAdded()) {
					List<ChatItem> list = chatView.getItems();
					ChatItem item = list.get(list.size() - 1);
					// 유저가 전송한 메시지면 실행
					if (!item.isBot()) {
						ScriptManager.run(project, item.getMessage());
					}
				}
			}
		}); */

		getStyleClass().add("debug-pane");
	}

	public Editor getEditor() {
		return editor;
	}
}
