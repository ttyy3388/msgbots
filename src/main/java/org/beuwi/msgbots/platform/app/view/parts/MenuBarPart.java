package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.OpenBrowserAction;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDocumentTabAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenProgramTabAction;
import org.beuwi.msgbots.platform.app.view.actions.RefreshBotListAction;
import org.beuwi.msgbots.platform.app.view.actions.ToggleViewPartAction;
import org.beuwi.msgbots.platform.app.view.dialogs.CreateBotDialog;
import org.beuwi.msgbots.platform.app.view.dialogs.ImportBotDialog;
import org.beuwi.msgbots.platform.app.view.dialogs.StartProgramDialog;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalConfigTab;
import org.beuwi.msgbots.platform.app.view.tabs.ViewKeymapsTab;
import org.beuwi.msgbots.platform.gui.control.*;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.util.SharedValues;

public class MenuBarPart implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static StackPane root;
	private static MenuBar control;

	@Override
	public void init() {
		loader = new FormLoader("part", "menu-bar-part");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		control = loader.getComponent();

		MenuButton btnFileMenu = (MenuButton) namespace.get("btnFileMenu");
		MenuButton btnEditMenu = (MenuButton) namespace.get("btnEditMenu");
		MenuButton btnViewMenu = (MenuButton) namespace.get("btnViewMenu");
		MenuButton btnDebugMenu = (MenuButton) namespace.get("btnDebugMenu");
		MenuButton btnHelpMenu = (MenuButton) namespace.get("btnHelpMenu");

		// File Menu Button
		btnFileMenu.setMenus(
			new MenuItem("New Bot", "Ctrl + N", event -> {
				OpenDialogBoxAction.execute(new CreateBotDialog());
			}),
			new MenuItem("Import Script", "Ctrl + I", event -> {
				OpenDialogBoxAction.execute(new ImportBotDialog());
			}),
			new Separator(),
			new MenuItem("Save", "Ctrl + S"),
			new MenuItem("Save All"),
			new Separator(),
			new MenuItem("Refresh All Bots", "Ctrl + Alt + Y", event -> {
				RefreshBotListAction.execute();
			}),
			new Separator(),
			new MenuItem("Settings", "Ctrl + Alt + S", event -> {
				OpenProgramTabAction.execute(GlobalConfigTab.getRoot());
			})
		);

		// Edit Menu Button
		btnEditMenu.setMenus(
			new MenuItem("Undo", "Ctrl + Z").disable(true),
			new MenuItem("Redo", "Ctrl + Y").disable(true),
			new Separator(),
			new MenuItem("Cut", "Ctrl + X").disable(true),
			new MenuItem("Copy", "Ctrl + C").disable(true),
			new MenuItem("Paste", "Ctrl + V").disable(true)
		);

		// View Menu Button
		btnViewMenu.setMenus(
			new MenuItem("Toggle Menu Bar", "Alt + M", event -> {
				ToggleViewPartAction.execute(MenuBarPart.getRoot());
			}),
			new MenuItem("Toggle Side Bar", "Alt + S", event -> {
				ToggleViewPartAction.execute(SideAreaPart.getRoot());
			}),
			new MenuItem("Toggle Debug Area", "Alt + D", event -> {
				ToggleViewPartAction.execute(DebugAreaPart.getRoot());
			}),
			new MenuItem("Toggle Tool Area", "Alt + D", event -> {
				ToggleViewPartAction.execute(ToolAreaPart.getRoot());
			})
		);

		// Debug Menu Button
		btnDebugMenu.setMenus(
			new MenuItem("Compile"),
			new MenuItem("Power On / Off"),
			new Separator(),
			new MenuItem("Show Global Log", "F8")
			// new MenuItem("Open Debug Room", "F9")
		);

		// Help Menu Button
		btnHelpMenu.setMenus(
			new MenuItem("View Keymaps", event -> {
				OpenProgramTabAction.execute(ViewKeymapsTab.getRoot());
			}),
			new Separator(),
			new MenuItem("View License", event -> {
				OpenBrowserAction.execute(SharedValues.getString("VIEW_LICENSE_LINK"));
			}),
			new MenuItem("Release Notes", event -> {
				OpenBrowserAction.execute(SharedValues.getString("RELEASE_NOTES_LINK"));
			}),
			new MenuItem("About Program", event -> {
				OpenDocumentTabAction.execute(new Document("ABOUT PROGRAM", new Page("about-program-page")));
			}),
			new Separator(),
			new MenuItem("Welcome Guide", event -> {
				OpenDocumentTabAction.execute(new Document("WELCOME GUIDE", new Page("welcome-guide-page")));
			}),
			new Separator(),
			new MenuItem("Welcome to Program", event -> {
				OpenDialogBoxAction.execute(new StartProgramDialog());
			})
		);
	}

	public static StackPane getRoot() {
		return root;
	}

	public static MenuBar getMainControl() {
		return control;
	}

	public static <T> T getComponent(String key) {
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}