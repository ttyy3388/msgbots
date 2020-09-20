package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.parts.DebugAreaPart;

public class ToggleDebugAreaAction implements Action
{
	private static BorderPane bpane;
	private static AnchorPane apane;

	private static boolean hided;

	@Override
	public void init()
	{
		apane = DebugAreaPart.getRoot();
		bpane = MainView.getRoot();
	}

	public static void execute()
	{
		if (hided)
		{
		    bpane.setRight(apane);
		}
		else
        {
            bpane.getChildren().remove(apane);
        }

		hided = !hided;
	}

	@Override
	public String getName()
	{
		return "toggle.debug.area.action";
	}
}
