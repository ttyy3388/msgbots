package org.beuwi.msgbots.keyboard;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public enum KeyBinding {
	// Null을 반환하면 안되므로 EMPTY를 반환함
	EMPTY("", "", KeyCategory.NONE),

	CREATE_BOT("Create Bot", "Ctrl + N", KeyCategory.FILE),
	DELETE_BOT("Delete Bot", "", KeyCategory.FILE),
	IMPORT_SCRIPT("Import Script", "Ctrl + I", KeyCategory.FILE),
	SAVE("Save", "Ctrl + S", KeyCategory.FILE),
	SAVE_ALL("Save All", "", KeyCategory.FILE),
	REFRESH_ALL_BOTS("Refresh All Bots", "", KeyCategory.FILE),
	SETTINGS("Settings", "Ctrl + Alt + S", KeyCategory.FILE),

	UNDO("Undo", "Ctrl + Z", KeyCategory.EDIT),
	REDO("Redo", "Ctrl + Y", KeyCategory.EDIT),
	CUT("Cut", "Ctrl + X", KeyCategory.EDIT),
	COPY("Copy", "Ctrl + C", KeyCategory.EDIT),
	PASTE("Paste", "Ctrl + V", KeyCategory.EDIT),
	SELECT_ALL("Select All", "Ctrl + A", KeyCategory.EDIT),

	TOGGLE_MENU_BAR("Toggle Menu Bar", "", KeyCategory.VIEW),
	TOGGLE_SIDE_BAR("Toggle Side Bar", "", KeyCategory.VIEW),
	TOGGLE_DEBUG_AREA("Toggle Debug Area", "", KeyCategory.VIEW),
	TOGGLE_TOOL_AREA("Toggle Tool Area", "", KeyCategory.VIEW),

	COMPILE("Compile", "", KeyCategory.DEBUG),
	TOGGLE_POWER("Power On / Off", "", KeyCategory.DEBUG),

	VIEW_KEYMAPS("View Keymaps", "", KeyCategory.HELP),
	VIEW_LICENSE("View License", "", KeyCategory.HELP),
	RELEASE_NOTES("Release Notes", "", KeyCategory.HELP),
	ABOUT_PROGRAM("About Program", "", KeyCategory.HELP),
	WELCOME_GUIDE("Welcome Guide", "", KeyCategory.HELP);
	// WEB_DEBUG_MODE("Web Debug Mode", "F12", KeyCategory.DEBUG);

	private final String name; // 해당 키맵이 설명될 이름
	private final KeyCombination keycombi; // 해당 키맵의 발동 키 콤보
	// private final EventHandler handler; // 키맵 매칭 시 실행될 액션 등록
	private final KeyCategory category;

	KeyBinding(String name, String key, KeyCategory category) {
		this(name, !key.equals("") ? KeyCombination.keyCombination(key) : null, category);
	}
	KeyBinding(String name, KeyCombination keycombi, KeyCategory category) {
		this.name = name;
		this.keycombi = keycombi;
		// this.handler = handler;
		this.category = category;
	}

	public KeyCombination getKeyCombi() {
		return keycombi;
	}

	/* public EventHandler getHandler() {
		return handler;
	} */

	public String getName() {
		return name;
	}

	public KeyCategory getCategory() {
		return category;
	}

	public static KeyBinding matching(KeyEvent event) {
		KeyCode code = event.getCode();
		if (event.equals(KeyCode.UNDEFINED)) {
			return null;
		}
		KeyBinding[] bindings = KeyBinding.values();
		int length = bindings.length;
		for (int i = 0; i < length; i ++) {
			KeyBinding binding = bindings[i];
			KeyCombination combi = binding.getKeyCombi();
			if (combi != null && combi.match(event)) {
				return binding;
			}
		}
		// Null을 반환하면 안되므로 EMPTY를 반환함
		return KeyBinding.EMPTY;
	}
	/* public static List<KeyBinding> getList(KeyCategory category) {
		List<KeyBinding> list = new ArrayList<>();
		KeyBinding[] bindings = KeyBinding.values();
		int length = bindings.length;
		for (int i = 0; i < length; i ++) {
			KeyBinding binding = bindings[i];
			if (binding.getCategory().equals(category)) {
				list.add(binding);
			}
		}
		return list;
	} */
}
