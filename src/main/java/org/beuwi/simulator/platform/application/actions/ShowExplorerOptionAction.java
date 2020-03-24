package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;
import org.beuwi.simulator.platform.ui.components.IMenuItem;

public class ShowExplorerOptionAction
{
	final static IMenuItem SHOW_COMPILED_CHECK_ITEM = new IMenuItem("Show Compiled Check");
	final static IMenuItem SHOW_COMPILE_BUTTON_ITEM = new IMenuItem("Show Compile Button");
	final static IMenuItem SHOW_POWER_SWITCH_ITEM   = new IMenuItem("Show Power Switch");

	private static ContextMenu option;
	private static Button button;

	public static void initialize()
	{
		// button = (Button) ActiveAreaPart.getNameSpace().get("btnExplorerOption");

        option = new ContextMenu();
		option.getItems().addAll
		(
			SHOW_COMPILED_CHECK_ITEM,
            SHOW_COMPILE_BUTTON_ITEM,
			SHOW_POWER_SWITCH_ITEM
		);
	}

	public static void update(MouseEvent event)
	{
		if (event.isPrimaryButtonDown())
		{
			option.show(button, event.getScreenX(), event.getScreenY());
		}
	}
}