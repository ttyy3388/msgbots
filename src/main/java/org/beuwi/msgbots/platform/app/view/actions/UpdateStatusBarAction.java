package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.gui.control.HBox;
import org.beuwi.msgbots.platform.gui.control.Label;

import java.util.Map;

public class UpdateStatusBarAction implements Action
{
	private static Map<String, Object> nameSpace;

	private static HBox component;

	private static Label lblOpenedTabName;
	private static Label lblLinePosition;
	private static Label lblLineEncoding;
	private static Label lblFileEncoding;

	@Override
	public void init()
	{
		component = StatusBarPart.getComponent();
		nameSpace = StatusBarPart.getNamespace();

		lblOpenedTabName = (Label) nameSpace.get("lblOpenedTabName");
		lblLinePosition = (Label) nameSpace.get("lblLinePosition");
		lblLineEncoding = (Label) nameSpace.get("lblLineEncoding");
		lblFileEncoding = (Label) nameSpace.get("lblFileEncoding");
	}

	// [ Opened Tab Name, Line Position, Line Encoding, File Encoding ]
	public static void execute(String[] data)
	{
        lblOpenedTabName.setText(data[0]);
        lblLinePosition.setText(data[1]);
        lblLineEncoding.setText(data[2]);
        lblFileEncoding.setText(data[3]);
	}

	@Override
	public String getName()
	{
		return "update.status.bar.action";
	}
}
