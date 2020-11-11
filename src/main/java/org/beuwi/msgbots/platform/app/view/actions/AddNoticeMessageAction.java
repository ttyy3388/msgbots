package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.NoticeAreaPart;
import org.beuwi.msgbots.platform.gui.control.Notice;
import org.beuwi.msgbots.platform.gui.control.NoticeView;
import org.beuwi.msgbots.platform.gui.enums.NoticeType;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AddNoticeMessageAction implements Action
{
	private static NoticeView control;

	@Override
	public void init()
	{
		control = NoticeAreaPart.getComponent();
	}

	public static void execute(Throwable error)
	{
		StringWriter message = new StringWriter();
		error.printStackTrace(new PrintWriter(message));

		execute(NoticeType.ERROR, error.toString(), message.toString());
	}

	// Default Type : Event
	/* public static void execute(String title, String content)
	{
		execute(NoticeType.EVENT, title, content);
	} */

	public static void execute(NoticeType type, String title, String content)
	{
		execute(new Notice(type, title, content));
	}

	public static void execute(Notice notice)
	{
		control.addNotice(notice);
	}

	@Override
	public String getName()
	{
		return "add.notice.message.action";
	}
}
