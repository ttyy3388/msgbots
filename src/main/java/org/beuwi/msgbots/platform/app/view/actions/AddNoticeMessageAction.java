package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.NoticeAreaPart;
import org.beuwi.msgbots.platform.gui.control.Notice;
import org.beuwi.msgbots.platform.gui.control.NoticeView;

public class AddNoticeMessageAction implements Action
{
    private static NoticeView control;

    @Override
    public void init()
    {
        control = NoticeAreaPart.getComponent();
    }

    public static void execute(Notice notice)
    {
        control.addToast(notice);
    }

    @Override
    public String getName()
    {
        return "add.notice.message.action";
    }
}
