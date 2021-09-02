package org.beuwi.msgbots.view.util;

import javafx.event.EventHandler;

import org.beuwi.msgbots.keyboard.KeyBinding;
import org.beuwi.msgbots.view.app.actions.AddMainAreaTabAction;
import org.beuwi.msgbots.view.app.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.view.app.actions.ReloadAllBotsAction;
import org.beuwi.msgbots.view.app.dialogs.CreateBotDialog;
import org.beuwi.msgbots.view.app.dialogs.ImportScriptDialog;
import org.beuwi.msgbots.view.app.tabs.GlobalConfigTab;
import org.beuwi.msgbots.view.gui.control.MenuItem;

// Standard Actions
public enum StdActions {
    COPY("Copy", KeyBinding.COPY),
    COPY_TEXT("Copy Text", KeyBinding.COPY),
    COPY_PATH("Copy Path"),
    COPY_RELATIVE_PATH("Copy Relative Path"),
    BOT_SETTING("Settings"),
    CREATE_BOT("New Bot", KeyBinding.CREATE_BOT, event -> {
        OpenDialogBoxAction.getInstance().execute(new CreateBotDialog());
    }),
    DELETE_BOT("Delete", KeyBinding.DELETE_BOT),
    RENAME_BOT("Rename"),
    IMPORT_SCRIPT("Import Bot", KeyBinding.IMPORT_SCRIPT, event -> {
        OpenDialogBoxAction.getInstance().execute(new ImportScriptDialog());
    }),
    SAVE("Save", KeyBinding.SAVE, event -> {
        // SaveOpenedEditorAction.getInstance().execute();
    }),
    SAVE_ALL("Save All", KeyBinding.SAVE_ALL, event -> {
        // SaveAllEditorsAction.getInstance().execute();
    }),
    SETTINGS("Settings", KeyBinding.REFRESH_ALL_BOTS, event -> {
        AddMainAreaTabAction.getInstance().execute(GlobalConfigTab.getInstance());
    }),
    UNDO("Undo", KeyBinding.UNDO, event -> {

    }),
    REDO("Redo", KeyBinding.REDO),
    CUT("Cut", KeyBinding.CUT),
    PASTE("Paste", KeyBinding.PASTE),
    SELECT_ALL("Select All", KeyBinding.SELECT_ALL),
    COMPILE("Compile", KeyBinding.COMPILE),
    COMPILE_ALL("Compile All", KeyBinding.EMPTY, event -> {
        ReloadAllBotsAction.getInstance().execute();
    }),
    TOGGLE_POWER("Power On / Off", KeyBinding.TOGGLE_POWER),
    CLOSE_TAB("Close Tab"),
    CLOSE_OTHER_TABS("Close Other Tabs"),
    CLOSE_ALL_TABS("Close All Tabs"),
    OPEN_TAB_TO_DIALOG("Open Tab To Dialog"),
    REFRESH_ALL_BOTS("Refresh All Bots", KeyBinding.REFRESH_ALL_BOTS),
    TOGGLE_MENU_BAR("Toggle Menu Bar", KeyBinding.TOGGLE_MENU_BAR, event -> {
        // ToggleMenuBarAction.getInstance().execute();
    }),
    TOGGLE_SIDE_BAR("Toggle Side Bar", KeyBinding.TOGGLE_SIDE_BAR, event -> {
        // ToggleSideAreaAction.getInstance().execute();
    }),
    TOGGLE_DEBUG_AREA("Toggle Debug Area", KeyBinding.TOGGLE_DEBUG_AREA, event -> {
        // ToggleDebugAreaAction.getInstance().execute();
    }),
    TOGGLE_TOOL_AREA("Toggle Tool Area", KeyBinding.TOGGLE_TOOL_AREA, event -> {
        // ToggleToolAreaAction.getInstance().execute();
    }),
    VIEW_KEYMAPS("View Keymaps", KeyBinding.VIEW_KEYMAPS),
    VIEW_LICENSE("View License", KeyBinding.VIEW_LICENSE),
    RELEASE_NOTES("Release Notes", KeyBinding.RELEASE_NOTES),
    ABOUT_PROGRAM("About Program", KeyBinding.ABOUT_PROGRAM),
    WELCOME_GUIDE("Welcome Guide", KeyBinding.WELCOME_GUIDE),
    ADB_CONNECT("Adb Connect"),
    SHOW_IN_EXPLORER("Show in Explorer"),
    HIDE_CHAT_AREA("Hide Chat Area"),
    HIDE_EDITOR_AREA("Hide Editor Area"),
    HIDE_UTILS_AREA("Hide Utils Area");

    private final String text;
    private final KeyBinding binding;
    private EventHandler handler;

    StdActions(String text) {
        this(text, null);
    }
    StdActions(String text, KeyBinding binding) {
        this(text, binding, null);
    }
    StdActions(String text, KeyBinding binding, EventHandler handler) {
        this.text = text;
        this.binding = binding;
        this.handler = handler;
    }

    public String getName() {
        return text;
    }
    public KeyBinding getKeyBinding() {
        return binding;
    }
    public EventHandler getHandler() {
        return handler;
    }

    public StdActions handler(EventHandler handler) {
        this.handler = handler;
        return this;
    }

    public void execute() {
        if (handler != null) {
            handler.handle(null);
        }
    }

    public MenuItem toMenuItem() {
        return new MenuItem(text, binding, handler);
    }
}
