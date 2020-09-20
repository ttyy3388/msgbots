package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.parts.SideAreaPart;

public class ToggleSideAreaAction implements Action
{
	private static BorderPane bpane;
	private static AnchorPane apane;

	private static boolean hided;

	@Override
	public void init()
	{
		apane = SideAreaPart.getRoot();
		bpane = MainView.getRoot();
	}

	public static void execute()
	{
		if (hided)
		{
		    bpane.setLeft(apane);
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
		return "toggle.side.area.action";
	}
}
