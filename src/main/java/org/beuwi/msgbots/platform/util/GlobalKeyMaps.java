package org.beuwi.msgbots.platform.util;

import javafx.scene.input.KeyCombination;

import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.openapi.KeyMap;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenProgramTabAction;
import org.beuwi.msgbots.platform.app.view.actions.SaveBotScriptTabAction;
import org.beuwi.msgbots.platform.app.view.dialogs.CreateBotDialog;
import org.beuwi.msgbots.platform.app.view.dialogs.ImportBotDialog;
import org.beuwi.msgbots.platform.app.view.tabs.BotListTab;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalConfigTab;
import org.beuwi.msgbots.platform.gui.control.BotItem;
import org.beuwi.msgbots.platform.gui.editor.Editor;

import java.util.ArrayList;
import java.util.List;

public class GlobalKeyMaps {
	private static final List<KeyMap> keyMaps = new ArrayList<>();
	static {
		keyMaps.add(new KeyMap("File - New Bot", KeyCombination.keyCombination("Ctrl + N"), event -> {
			OpenDialogBoxAction.execute(new CreateBotDialog());
		}));
		keyMaps.add(new KeyMap("File - Import Script", KeyCombination.keyCombination("Ctrl + I"), event -> {
			OpenDialogBoxAction.execute(new ImportBotDialog());
		}));
		keyMaps.add(new KeyMap("File - Settings", KeyCombination.keyCombination("Ctrl + Alt + S"), event -> {
			OpenProgramTabAction.execute(GlobalConfigTab.getRoot());
		}));

		keyMaps.add(new KeyMap("Edit - Redo", KeyCombination.keyCombination("Ctrl + Z"), event -> {
			Editor editor = ProgramData.getFocusedEditor();
			if (editor != null) { editor.redo(); }
		}));
		keyMaps.add(new KeyMap("Edit - Undo", KeyCombination.keyCombination("Ctrl + Y"), event -> {
			Editor editor = ProgramData.getFocusedEditor();
			if (editor != null) { editor.undo(); }
		}));
		keyMaps.add(new KeyMap("Edit - Cut", KeyCombination.keyCombination("Ctrl + X"), event -> {
			Editor editor = ProgramData.getFocusedEditor();
			if (editor != null) { editor.cut(); }
		}));
		keyMaps.add(new KeyMap("Edit - Copy", KeyCombination.keyCombination("Ctrl + C"), event -> {
			Editor editor = ProgramData.getFocusedEditor();
			if (editor != null) { editor.copy(); }
		}));
		keyMaps.add(new KeyMap("Edit - Save", KeyCombination.keyCombination("Ctrl + S"), event -> {
			Editor editor = ProgramData.getFocusedEditor();
			if (editor != null) { editor.save(); }
		}));

		keyMaps.add(new KeyMap("Debug - Compile", KeyCombination.keyCombination("F10"), event -> {
			BotItem selectedBot = BotListTab.getComponent().getSelectedItem();
			if (selectedBot != null) {
				ScriptManager.initScript(selectedBot.getName(), true, false);
			}
		}));
		keyMaps.add(new KeyMap("Debug - Save & Compile", KeyCombination.keyCombination("F11"), event -> {
			BotItem selectedBot = BotListTab.getComponent().getSelectedItem();
			if (selectedBot != null) {
				SaveBotScriptTabAction.execute(selectedBot.getName());
				ScriptManager.initScript(selectedBot.getName(), true, false);
			}
		}));
		keyMaps.add(new KeyMap("Debug - Compile All", KeyCombination.keyCombination("F12"), event -> {
			ScriptManager.initAll(true);
		}));

		keyMaps.add(new KeyMap("Debug - Show Global Log", KeyCombination.keyCombination("F9"), event -> {
			BotItem selectedBot = BotListTab.getComponent().getSelectedItem();
			if (selectedBot != null) {
				SaveBotScriptTabAction.execute(selectedBot.getName());
				ScriptManager.initScript(selectedBot.getName(), true, false);
			}
		}));
	}

	public static List<KeyMap> getList() {
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