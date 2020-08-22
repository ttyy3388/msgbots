package org.beuwi.msgbots.platform.app.view.parts;

import javafx.scene.layout.Pane;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.area.SidePane;

public class ActiveAreaPart extends SidePane implements View
{
	private static SidePane root;

	public ActiveAreaPart()
	{
		root = this;
	}

	@Override
	public void init() throws Exception
	{

	}

	public static Pane getRoot()
	{
		return root;
	}
}