package org.beuwi.msgbots.view.app.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.view.gui.control.BotView;
import org.beuwi.msgbots.view.gui.control.TabItem;

public class BotListTab extends TabItem implements View {
	private static BotListTab instance = null;

	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;

	@FXML private BotView botView;

	private BotListTab() {
		loader = new FormLoader();
		loader.setName("bot-list-tab");
		loader.setController(this);
		loader.setRoot(this);
		loader.load();

		namespace = loader.getNamespace();
		// root = loader.getRoot();

		// 프로젝트 목록이 변경될 때마다 목록을 업데이트 함
		// 이러면 한 파일이 추가될 때 마다 업데이트 되므로 비효율적이라 폐기함
		/* ProjectManager.getList().addListener((ListChangeListener<Project>) change -> {
			List<BotItem> list = new ArrayList<>();
			ProjectManager.getList().forEach(project -> {
				System.out.println(project);
				list.add(GUIManager.toListItem(project));
			});
			botView.getItems().setAll(list);
		}); */
	}

	@Override
	public Object findById(String id) {
		return namespace.get(id);
	}

	public BotView getBotView() {
		return botView;
	}

	public static BotListTab getInstance() {
		if (instance == null) {
			instance = new BotListTab();
		}
		return instance;
	}
}