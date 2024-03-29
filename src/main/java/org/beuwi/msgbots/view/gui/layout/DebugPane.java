package org.beuwi.msgbots.view.gui.layout;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;

import org.beuwi.msgbots.base.JArray;
import org.beuwi.msgbots.base.JObject;
import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.base.Session;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.ScriptManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.setting.GlobalSettings;
import org.beuwi.msgbots.view.gui.control.ChatArea;
import org.beuwi.msgbots.view.gui.control.ChatItem;
import org.beuwi.msgbots.view.gui.control.ChatView;
import org.beuwi.msgbots.view.gui.control.LogItem;
import org.beuwi.msgbots.view.gui.control.LogView;
import org.beuwi.msgbots.view.gui.control.SplitView;
import org.beuwi.msgbots.view.gui.editor.Editor;

import java.io.File;
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

	// private final Project project;
	private final Session session;
	// private final File script;

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

		// editor.setFile(project.getFile("index.js"));
		// editor.setLanguage("javascript");

		// 컴파일러 쪽에서 보내는 상황(Message) 수신
		session.addOnMessageListener((message) -> {
			if (message.equals("Compile Start")) {
				if (GlobalSettings.getBoolean("program.compileAutoSave")) {
					editor.save();
				}
			}
		});

		// 현재 세션에서 챗이 오는 경우는 무조건 봇이 보내는 경우만 존재하나 추후 효율성을 위해
		session.addOnChatListener((message, isBot) -> {
			// 봇이 전송한 메시지만 추가
			if (isBot) {
				ChatItem item = new ChatItem(message, true);
				chatView.getItems().add(item);
			}
		});

		session.addOnLogListener((type, date, data) -> {
			// 로그 아이템 추가
			LogItem item = new LogItem(type, date, data);
			logView.getItems().add(item);

			// 로그 파일 저장
			File file = project.getFile("log.json");
			// 제거됐다면 파일 생성
			if (!file.exists()) {
				FileManager.write(file, "[]");
			}

			JObject object = new JObject();
			object.put("type", type.toString());
			object.put("data", data);
			object.put("date", date);

			JArray array = new JArray(file);
			array.add(object);

			FileManager.write(file, array.toString());
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

		addStyleClass("debug-pane");
	}

	public Editor getEditor() {
		return editor;
	}
}
