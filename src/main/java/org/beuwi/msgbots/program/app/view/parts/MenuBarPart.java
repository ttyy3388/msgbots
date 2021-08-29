package org.beuwi.msgbots.program.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.program.app.impl.View;
import org.beuwi.msgbots.program.gui.control.MenuButton;
import org.beuwi.msgbots.program.gui.control.MenuItem;
import org.beuwi.msgbots.program.gui.layout.StackPane;
import org.beuwi.msgbots.program.utils.GlobalActions;

public class MenuBarPart extends StackPane implements View {
	private static MenuBarPart instance = null;

	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;

	@FXML private MenuButton btnFile;
	@FXML private MenuButton btnEdit;
	@FXML private MenuButton btnView;
	@FXML private MenuButton btnDebug;
	@FXML private MenuButton btnHelp;

	private MenuBarPart() {
		loader = new FormLoader();
		loader.setName("menu-bar-part");
		loader.setController(this);
		loader.setRoot(this);
		loader.load();

		namespace = loader.getNamespace();
		// root = loader.getRoot();

		btnFile.setMenuItems(
			GlobalActions.CREATE_BOT.toMenu(),
			GlobalActions.IMPORT_SCRIPT.toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.SAVE.toMenu(),
			GlobalActions.SAVE_ALL.toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.REFRESH_ALL_BOTS.toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.SETTINGS.toMenu()
		);

		btnEdit.setMenuItems(
			GlobalActions.UNDO.toMenu(),
			GlobalActions.REDO.toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.CUT.toMenu(),
			GlobalActions.COPY.toMenu(),
			GlobalActions.PASTE.toMenu()
		);

		btnView.setMenuItems(
			GlobalActions.TOGGLE_SIDE_BAR.toMenu(),
			GlobalActions.TOGGLE_DEBUG_AREA.toMenu(),
			GlobalActions.TOGGLE_TOOL_AREA.toMenu()
		);

		btnDebug.setMenuItems(
			GlobalActions.COMPILE_ALL.toMenu(),
			GlobalActions.TOGGLE_POWER.toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.ADB_CONNECT.toMenu()
		);

		btnHelp.setMenuItems(
			GlobalActions.VIEW_KEYMAPS.toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.VIEW_LICENSE.toMenu(),
			GlobalActions.RELEASE_NOTES.toMenu(),
			GlobalActions.ABOUT_PROGRAM.toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.WELCOME_GUIDE.toMenu()
		);
	}

	@Override
	public Object findById(String id) {
		return namespace.get(id);
	}

	public static MenuBarPart getInstance() {
		if (instance == null) {
			instance = new MenuBarPart();
		}
		return instance;
	}
}