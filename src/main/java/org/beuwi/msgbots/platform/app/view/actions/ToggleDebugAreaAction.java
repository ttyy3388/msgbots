package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.parts.DebugAreaPart;
import org.beuwi.msgbots.platform.gui.control.VBox;

public class ToggleDebugAreaAction implements Action
{
	private static AnchorPane pane;
	private static VBox root;

	private static boolean hided;

	@Override
	public void init()
	{
		pane = DebugAreaPart.getRoot();
		root = MainView.getRoot();
	}

	public static void execute()
	{
		if (hided)
		{
			root.addItem(0, root);
		}
		else
        {
			root.remove(pane);
        }

		hided = !hided;
	}

	@Override
	public String getName()
	{
		return "toggle.debug.area.action";
	}
}
