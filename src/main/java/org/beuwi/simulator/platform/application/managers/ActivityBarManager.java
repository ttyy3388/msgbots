package org.beuwi.simulator.platform.application.managers;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.SelectActivityButtonAction;

public class ActivityBarManager
{
	private static AnchorPane component = null;

	public static void initManager(AnchorPane anpActivityBar)
	{
		component = anpActivityBar;
	}

	public static void initActions()
	{
        SelectActivityButtonAction.initAction();
	}

	public static AnchorPane getComponent()
	{
		return component;
	}
}