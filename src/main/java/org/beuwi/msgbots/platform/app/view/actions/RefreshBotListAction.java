package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.app.view.tabs.BotListTab;
import org.beuwi.msgbots.platform.gui.control.BotItem;
import org.beuwi.msgbots.platform.gui.control.BotView;
import org.beuwi.msgbots.platform.gui.control.TabView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RefreshBotListAction implements Action {
	private static BotView botView;
	private static TabView tabView;

	@Override
	public void init() {
		botView = BotListTab.getComponent();
		tabView = MainAreaPart.getComponent();
	}

	public static void execute() {
		List<BotItem> botItems = botView.getItems();

		if (botItems.size() != 0) {
			List<String> fileNames = FileManager.getBotNames();
			List<BotItem> removeItems = new ArrayList(); // Modify 에러가 나서 따로 리스트에 담아서 삭제해야 함

			List<String> botNames = new ArrayList<>();
			botView.getItems().forEach(item ->
				botNames.add(item.getName())
			);

			// 봇 목록을 기준으로 검사
			for (BotItem item : botItems) {
				String botName = item.getName();

				// 봇 목록에도 있고 파일 목록에도 있을 때
				if (fileNames.contains(botName)) {
					// 대상이 아니므로 넘어감
					continue ;
				}
				// 봇 목록에는 있는데 파일 목록에 없으면 삭제된 아이템이라고 간주하여 봇 리스트에서도 삭제
				else {
					removeItems.add(item);

					// 에디터 탭도 열려 있다면 탭 닫음
					if (tabView.getTab(botName) != null) {
						tabView.closeTab(botName);
					}
				}
			}

			botItems.removeAll(removeItems);

			// List<BotItem> addItems = new ArrayList();

			// 파일 목록을 기준으로 검사
			for (String fileName : fileNames) {
				// 파일이 봇 목록에 포함되어 있다면 넘어감
				if (botNames.contains(fileName)) {
					continue ;
				}
				// 아니라면 추가된 아이템이므로 추가함
				else {
					// 정렬을 위해 해당 위치를 지정해서 추가함
					botItems.add(fileNames.indexOf(fileName), new BotItem(fileName));
				}
			}

			// items.addAll(addItems);
		}
		// 봇목록이 비어있다면 새로 시작했거나 등의 경우임
		else {
			for (File folder : FileManager.getBotList()) {
				botView.getItems().add(new BotItem(folder.getName()));
			}
		}
	}

	@Override
	public String getName() {
		return "refresh.bot.list.action";
	}
}
