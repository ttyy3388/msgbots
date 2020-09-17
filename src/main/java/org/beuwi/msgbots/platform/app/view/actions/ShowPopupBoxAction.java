package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.PopupAreaPart;

public class ShowPopupBoxAction implements Action
{
	private static StackPane pane;

	@Override
	public void init()
	{
		pane = PopupAreaPart.getComponent();
	}

	public static void execute(Pane content)
	{
		pane.getChildren().add(content);
	}

	@Override
	public String getName()
	{
		return "show.popup.box.action";
	}
}
