package org.beuwi.simulator.platform.application.actions;

import javafx.css.PseudoClass;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;
import org.beuwi.simulator.platform.application.views.parts.ToolBarPart;
import org.beuwi.simulator.platform.ui.components.IMenuItem;

public class ShowDebugMenuAction
{
	final static IMenuItem OPEN_DEBUG_ROOM_ITEM = new IMenuItem("Open Debug Room");
	final static IMenuItem SHOW_ALL_LOGS_ITEM   = new IMenuItem("Show All Logs");

	private static ContextMenu menu;
	private static Button button;

	public static void initialize()
	{
		button = (Button) ToolBarPart.getNameSpace().get("btnDebugMenu");
		menu = new ContextMenu();
		menu.getItems().addAll
		(
			OPEN_DEBUG_ROOM_ITEM,
			SHOW_ALL_LOGS_ITEM
		);
	}

	public static void update(MouseEvent event)
	{
		menu.show(button, Side.BOTTOM, 0, 0);

		menu.showingProperty().addListener((observable, oldValue, newValue) ->
		{
			button.pseudoClassStateChanged(PseudoClass.getPseudoClass("showing"), newValue);
		});
	}
}