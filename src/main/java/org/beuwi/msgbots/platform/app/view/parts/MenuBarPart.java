package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.actions.ToggleMenuBarAction;
import org.beuwi.msgbots.platform.gui.control.MenuBar;
import org.beuwi.msgbots.platform.gui.control.Menu;

public class MenuBarPart implements View
{
	private static ObservableMap<String, Object> nameSpace;

	private static FormLoader loader;

	private static StackPane root;

	private static MenuBar component;

	@Override
	public void init() throws Exception
	{
		loader = new FormLoader("menu-bar-part");

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		component = (MenuBar) loader.getComponent();

		// File Menu Button
		component.getItem(0).setMenus
		(
			new Menu("New Bot", "Ctrl + N"),
			new Menu("Import Script", "Ctrl + I"),
			new SeparatorMenuItem(),
			new Menu("Save", "Ctrl + S"),
			new Menu("Save All"),
			new SeparatorMenuItem(),
			new Menu("Refresh All Bots", "Ctrl + Alt + Y"),
			new SeparatorMenuItem(),
			new Menu("Settings", "Ctrl + Alt + S")
		);

		// Edit Menu Button
		component.getItem(1).setMenus
		(
			new Menu("Undo", "Ctrl + Z"),
			new Menu("Redo", "Ctrl + Y"),
			new SeparatorMenuItem(),
			new Menu("Cut", "Ctrl + X"),
			new Menu("Copy", "Ctrl + C"),
			new Menu("Paste", "Ctrl + V")
		);

		// View Menu Button
		component.getItem(2).setMenus
		(
			new Menu("Toggle Menu Bar", /* "Alt",*/ event -> ToggleMenuBarAction.execute())
			/* new SeparatorMenuItem(),
			new MenuItem("Toggle Bots Tab"),
			new MenuItem("Toggle Debug Tab") */
		);

		// Debug Menu Button
		component.getItem(3).setMenus
		(
			new Menu("Show Global Log", "F8"),
			new Menu("Open Debug Room", "F9")
		);

		// Help Menu Button
		component.getItem(4).setMenus
		(
			new Menu("View Update List"),
			new Menu("View License"),
			new SeparatorMenuItem(),
			new Menu("About Program"),
			new SeparatorMenuItem(),
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

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}