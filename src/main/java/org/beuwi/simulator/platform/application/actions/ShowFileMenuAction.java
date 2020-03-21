package org.beuwi.simulator.platform.application.actions;

import javafx.css.PseudoClass;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.beuwi.simulator.platform.application.parts.ToolBarPart;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IContextMenuType;

public class ShowFileMenuAction
{
	private static IContextMenu menu;
	private static Button button;

	public static void initialize()
	{
		menu = new IContextMenu(IContextMenuType.MENU_FILE);
		button = (Button) ToolBarPart.getNameSpace().get("btnFileMenu");
	}

	public static void update(MouseEvent event)
	{
		menu.show(button, Side.BOTTOM);

		menu.showingProperty().addListener((observable, oldValue, newValue) ->
		{
			button.pseudoClassStateChanged(PseudoClass.getPseudoClass("showing"), newValue);
		});
	}
}