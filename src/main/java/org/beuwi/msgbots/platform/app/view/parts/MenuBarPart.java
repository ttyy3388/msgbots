package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenProgramTabAction;
import org.beuwi.msgbots.platform.app.view.actions.ToggleDebugAreaAction;
import org.beuwi.msgbots.platform.app.view.actions.ToggleMenuBarAction;
import org.beuwi.msgbots.platform.app.view.actions.ToggleSideAreaAction;
import org.beuwi.msgbots.platform.app.view.dialogs.CreateBotDialog;
import org.beuwi.msgbots.platform.app.view.dialogs.ImportBotDialog;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalConfigTab;
import org.beuwi.msgbots.platform.gui.control.Menu;
import org.beuwi.msgbots.platform.gui.control.MenuBar;
import org.beuwi.msgbots.platform.gui.control.Separator;

public class MenuBarPart implements View
{
	private static ObservableMap<String, Object> namespace;

	private static FormLoader loader;

	private static StackPane root;

	private static MenuBar component;

	@Override
	public void init()
	{
		loader = new FormLoader("part", "menu-bar-part");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		// File Menu Button
		component.getMenu(0).setMenus
		(
			new Menu("New Bot", "Ctrl + N", event -> OpenDialogBoxAction.execute(new CreateBotDialog())),
			new Menu("Import Script", "Ctrl + I", event -> OpenDialogBoxAction.execute(new ImportBotDialog())),
			new Separator(),
			new Menu("Save", "Ctrl + S"),
			new Menu("Save All"),
			new Separator(),
			new Menu("Refresh All Bots", "Ctrl + Alt + Y"),
			new Separator(),
			new Menu("Settings", "Ctrl + Alt + S", event -> OpenProgramTabAction.execute(GlobalConfigTab.getRoot()))
		);

		// Edit Menu Button
		// ((CodeArea) MainAreaPart.getComponent().getSelectedTab().getContent())
		component.getMenu(1).setMenus
		(
			new Menu("Undo", "Ctrl + Z"),
			new Menu("Redo", "Ctrl + Y"  /* , event -> RedoTextTabAction.execute() */),
			new Separator(),
			new Menu("Cut", "Ctrl + X" /*, event -> CutTextTabAction.execute() */),
			new Menu("Copy", "Ctrl + C" /*, event -> CopyTextTabAction.execute() */),
			new Menu("Paste", "Ctrl + V" /*, event -> PasteTextTabAction.execute() */)
		);

		// View Menu Button
		component.getMenu(2).setMenus
		(
			new Menu("Toggle Menu Bar", "Alt + M", event -> ToggleMenuBarAction.execute()),
			new Menu("Toggle Side Bar", "Alt + S", event -> ToggleSideAreaAction.execute()),
			new Menu("Toggle Debug Area", "Alt + D", event -> ToggleDebugAreaAction.execute())
		);

		// Debug Menu Button
		component.getMenu(3).setMenus
		(
			new Menu("Compile", event -> ScriptManager.initAll(true)),
			new Menu("Power On / Off"),
			new Separator(),
			new Menu("Show Global Log", "F8"),
			new Menu("Open Debug Room", "F9")
		);

		// Help Menu Button
		component.getMenu(4).setMenus
		(
			new Menu("View Update List"),
			new Menu("View License"),
			new Separator(),
			new Menu("About Program"),
			new Separator(),
			new Menu("Welcome Guide")
		);
	}

	public static StackPane getRoot()
	{
		return root;
	}

	public static MenuBar getComponent()
	{
		return component;
	}

	public static <T> T getComponent(String key)
	{
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace()
	{
		return namespace;
	}
}