package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.MenuBar;
import org.beuwi.msgbots.platform.gui.control.MenuItem;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class MenuBarPart implements View
{
	private static ObservableMap<String, Object> nameSpace;

	private static Pane root;

	private static MenuBar component;

	@Override
	public void init() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("menu-bar-part"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		component = getComponent();

		// File Menu Button
		component.getItem(0).setMenus
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
		component.getItem(1).setMenus
		(
			new MenuItem("Undo", "Ctrl + Z"),
			new MenuItem("Redo", "Ctrl + Y"),
			new SeparatorMenuItem(),
			new MenuItem("Cut", "Ctrl + X"),
			new MenuItem("Copy", "Ctrl + C"),
			new MenuItem("Paste", "Ctrl + V")
		);

		// View Menu Button
		// component.getItem(2).setMenu(new ContextMenu());

		// Debug Menu Button
		component.getItem(3).setMenus
		(
			new MenuItem("Show Global Log", "F8"),
			new MenuItem("Open Debug Room", "F9")
		);
	}

	public static Pane getRoot()
	{
		return root;
	}

	public static MenuBar getComponent()
	{
		return (MenuBar) root.getChildren().get(0);
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}