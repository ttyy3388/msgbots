package org.beuwi.msgbots.view.app.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.view.gui.control.MenuButton;
import org.beuwi.msgbots.view.gui.control.MenuItem;
import org.beuwi.msgbots.view.gui.layout.StackPane;
import org.beuwi.msgbots.view.util.StdActions;

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
			StdActions.CREATE_BOT.toMenuItem(),
			StdActions.IMPORT_SCRIPT.toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.SAVE.toMenuItem(),
			StdActions.SAVE_ALL.toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.REFRESH_ALL_BOTS.toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.SETTINGS.toMenuItem()
		);

		btnEdit.setMenuItems(
			StdActions.UNDO.toMenuItem(),
			StdActions.REDO.toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.CUT.toMenuItem(),
			StdActions.COPY.toMenuItem(),
			StdActions.PASTE.toMenuItem()
		);

		btnView.setMenuItems(
			StdActions.TOGGLE_SIDE_BAR.toMenuItem(),
			StdActions.TOGGLE_DEBUG_AREA.toMenuItem(),
			StdActions.TOGGLE_TOOL_AREA.toMenuItem()
		);

		btnDebug.setMenuItems(
			StdActions.COMPILE_ALL.toMenuItem(),
			StdActions.TOGGLE_POWER.toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.ADB_CONNECT.toMenuItem()
		);

		btnHelp.setMenuItems(
			StdActions.VIEW_KEYMAPS.toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.VIEW_LICENSE.toMenuItem(),
			StdActions.RELEASE_NOTES.toMenuItem(),
			StdActions.ABOUT_PROGRAM.toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.WELCOME_GUIDE.toMenuItem()
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