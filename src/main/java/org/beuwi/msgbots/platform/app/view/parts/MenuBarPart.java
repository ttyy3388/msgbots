package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.actions.ToggleMenuBarAction;
import org.beuwi.msgbots.platform.gui.control.MenuBar;
import org.beuwi.msgbots.platform.gui.control.MenuItem;

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
		component = loader.getComponent();

		// File Menu Button
		component.getMenu(0).setMenus
		(
			new MenuItem("New Bot", "Ctrl + N"),
			new MenuItem("Import Script", "Ctrl + I"),
			new SeparatorMenuItem(),
			new MenuItem("Save", "Ctrl + S"),
			new MenuItem("Save All"),
			new SeparatorMenuItem(),
			new MenuItem("Refresh All Bots", "Ctrl + Alt + Y"),
			new SeparatorMenuItem(),
			new MenuItem("Settings", "Ctrl + Alt + S")
		);

		// Edit Menu Button
		component.getMenu(1).setMenus
		(
			new MenuItem("Undo", "Ctrl + Z"),
			new MenuItem("Redo", "Ctrl + Y"),
			new SeparatorMenuItem(),
			new MenuItem("Cut", "Ctrl + X"),
			new MenuItem("Copy", "Ctrl + C"),
			new MenuItem("Paste", "Ctrl + V")
		);

		// View Menu Button
		component.getMenu(2).setMenus
		(
			new MenuItem("Toggle Menu Bar", /* "Alt",*/ event -> ToggleMenuBarAction.execute())
			/* new SeparatorMenuItem(),
			new MenuItem("Toggle Bots Tab"),
			new MenuItem("Toggle Debug Tab") */
		);

		// Debug Menu Button
		component.getMenu(3).setMenus
		(
			new MenuItem("Compile"),
			new MenuItem("Power On / Off"),
			new SeparatorMenuItem(),
			new MenuItem("Show Global Log", "F8"),
			new MenuItem("Open Debug Room", "F9")
		);

		// Help Menu Button
		component.getMenu(4).setMenus
		(
			new MenuItem("View Update List"),
			new MenuItem("View License"),
			new SeparatorMenuItem(),
			new MenuItem("About Program"),
			new SeparatorMenuItem(),
			new MenuItem("Welcome Guide")
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