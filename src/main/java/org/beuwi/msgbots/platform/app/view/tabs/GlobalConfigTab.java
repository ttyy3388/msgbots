package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.actions.SetColorThemeAction;
import org.beuwi.msgbots.platform.gui.control.Navi;
import org.beuwi.msgbots.platform.gui.control.NaviView;
import org.beuwi.msgbots.platform.gui.control.OptionBox;
import org.beuwi.msgbots.platform.gui.control.OptionView;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.editor.Editor;
import org.beuwi.msgbots.platform.gui.enums.ConfigType;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.setting.GlobalSettings;

public class GlobalConfigTab implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static Tab root;
	private static NaviView component;

	@Override
	public void init() {
		loader = new FormLoader("tab", "global-config-tab");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		new DesignNaviTab().init();
		new BotNaviTab().init();
	}

	private static class DesignNaviTab implements View {
		private static Navi root;
		private static OptionView component;

		@Override
		public void init() {
			root = GlobalConfigTab.getComponent().getNavi("Design");
			component = (OptionView) root.getContent();

			OptionBox box = (OptionBox) component.getItems().get(0);
			Editor editor = (Editor) box.getContent();

			FileManager.link(SharedValues.USER_THEME_FILE, () -> {
				ThemeType currentTheme = ThemeType.parse(GlobalSettings.getString("program:color_theme"));
				// 현재 선택한 테마가 유저 테마면 바로 스타일 적용
				if (currentTheme.equals(ThemeType.USER)) {
					SetColorThemeAction.execute(ThemeType.USER);
				}
			});

			// 테마 변경 감지됐으면
			/* ProgramData.changeProperty().addListener(change -> {
				File file = ThemeType.parse(GlobalSettings.getString("program:color_theme")).toFile();
				// 테마가 변경됐어야 에디터 파일이 변경되도록
				if (editor.getFile() != file) {
					editor.setFile(file);
				}
			}); */
		}
	}

	private static class BotNaviTab implements View {
		private static Navi root;
		private static NaviView component;

		@Override
		public void init() {
			root = GlobalConfigTab.getComponent().getNavi("Bots");
			component = (NaviView) root.getContent();

			for (String name : FileManager.getBotNames()) {
				final FormLoader loader = new FormLoader("view", "bot-option-view");

				OptionView control = loader.getRoot();

				control.setType(ConfigType.SCRIPT);
				control.setName(name);
				control.setTitle(name);

				component.addNavi(new Navi(name, control));
			}
		}
	}

	public static Tab getRoot() {
		return root;
	}

	public static NaviView getComponent() {
		return component;
	}

	public static <T> T getComponent(String key) {
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}