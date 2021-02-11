package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.NoticeListPart;
import org.beuwi.msgbots.platform.gui.control.NoticeItem;
import org.beuwi.msgbots.platform.gui.control.NoticeView;

public class AddNoticeListItemAction implements Action {
    private static NoticeView component;

    @Override
    public void init() {
        component = NoticeListPart.getComponent("ntvNoticeList");
    }

    public static void execute(NoticeItem item) {
        component.getItems().add(item);
    }

    @Override
    public String getName() {
        return "add.notice.list.item.action";
    }
}
