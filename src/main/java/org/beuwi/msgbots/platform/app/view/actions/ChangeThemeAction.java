package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.Scene;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.gui.enums.Theme;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class ChangeThemeAction implements Action
{
	@Override
	public void init()
	{

	}

	public static void execute(Theme type)
	{
	    execute(MainView.getStage().getScene(), type);
	}

	public static void execute(Scene scene, Theme type)
    {
        scene.getStylesheets().setAll(ResourceUtils.getTheme(type.toString()));
    }

	@Override
	public String getName()
	{
		return "change.theme.action";
	}
}
