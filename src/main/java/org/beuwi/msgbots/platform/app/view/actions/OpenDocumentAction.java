package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.Document;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabView;

public class OpenDocumentAction implements Action
{
    private static TabView control;

    @Override
    public void init()
    {
        control = MainAreaPart.getComponent();
    }

    public static void execute(Document document)
    {
        execute(control, document);
    }

    public static void execute(TabView control, Document document)
    {
        control.addTab(document);
    }

    @Override
    public String getName()
    {
        return "open.document.action";
    }
}