package org.beuwi.msgbots.view.gui.layout;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;

import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.base.Session;
import org.beuwi.msgbots.manager.ScriptManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.view.gui.control.ChatArea;
import org.beuwi.msgbots.view.gui.control.ChatItem;
import org.beuwi.msgbots.view.gui.control.ChatView;
import org.beuwi.msgbots.view.gui.control.LogItem;
import org.beuwi.msgbots.view.gui.control.LogView;
import org.beuwi.msgbots.view.gui.control.SplitView;
import org.beuwi.msgbots.view.gui.editor.Editor;

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

		// 현재 세션에서 챗이 오는 경우는 무조건 봇이 보내는 경우만 존재하나 추후 효율성을 위해
		session.setOnChatListener((message, isBot) -> {
			// 봇이 전송한 메시지만 추가
			if (isBot) chatView.getItems().add(new ChatItem(message, true));
		});

		session.setOnLogListener((type, date, data) -> {
			logView.getItems().add(new LogItem(type, date, data));
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
		});

		getStyleClass().add("debug-pane");
	}

	public Editor getEditor() {
		return editor;
	}
}
