package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;
import org.beuwi.simulator.platform.application.views.parts.SideBarPart;

import java.awt.*;

public class ShowInExplorerAction
{
	private static AnchorPane pane;

	public static void initialize()
	{
		pane = SideBarPart.getRoot();
	}

	public static void update()
	{
		try
		{
			Desktop.getDesktop().open(FileManager.BOTS_FOLDER);
		}
		catch (Exception e)
		{
			new ShowErrorDialog(e).display();

			return ;
		}
	}
}