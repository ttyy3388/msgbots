package org.beuwi.msgbots.platform.util;

import javafx.scene.input.KeyCombination;

import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.openapi.KeyMap;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.dialogs.CreateBotDialog;
import org.beuwi.msgbots.platform.app.view.dialogs.ImportBotDialog;

import java.util.ArrayList;
import java.util.List;

public class GlobalKeyMaps {
	private static final List<KeyMap> keyMaps = new ArrayList<>();
	static {
		keyMaps.add(new KeyMap("Editor Cut", KeyCombination.keyCombination("Ctrl + X"), event -> {
			// CutEditorAction.excute();
		}));
		keyMaps.add(new KeyMap("Create Bot", KeyCombination.keyCombination("Ctrl + N"), event -> {
			OpenDialogBoxAction.execute(new CreateBotDialog());
		}));
		keyMaps.add(new KeyMap("Import Bot", KeyCombination.keyCombination("Ctrl + I"), event -> {
			OpenDialogBoxAction.execute(new ImportBotDialog());
		}));
		keyMaps.add(new KeyMap("Compile All Bots", KeyCombination.keyCombination("F10"), event -> {
			ScriptManager.initAll(true);
		}));
	}

	public static List<KeyMap>getKeyMaps() {
		return keyMaps;
	}

	/* public static final KeyCombination EDITOR_CUT_KEYCODE = KeyCombination.keyCombination("Ctrl + X");
	public static final KeyCombination EDITOR_COPY_KEYCODE = KeyCombination.keyCombination("Ctrl + C");
	public static final KeyCombination EDITOR_REDO_KEYCODE = KeyCombination.keyCombination("Ctrl + Z");
	public static final KeyCombination EDITOR_UNDO_KEYCODE = KeyCombination.keyCombination("Ctrl + Y");
	public static final KeyCombination EDITOR_SAVE_KEYCODE = KeyCombination.keyCombination("Ctrl + S");
	public static final KeyCombination EDITOR_PASTE_KEYCODE = KeyCombination.keyCombination("Ctrl + V");

	public static final KeyCombination CREATE_BOT_KEYCODE = KeyCombination.keyCombination("Ctrl + N");
	public static final KeyCombination IMPORT_BOT_KEYCODE = KeyCombination.keyCombination("Ctrl + I");

	public static final KeyCombination COMPILE_BOTS_KEYCODE = KeyCombination.keyCombination("F10");
	public static final KeyCombination TOGGLE_POWER_KEYCODE = KeyCombination.keyCombination("F11"); */

	// public static final KeyCombination OPEN_GLOBAL_OPTION_TAB_KEYCODE = KeyCombination.keyCombination("Ctrl + I"); */
}