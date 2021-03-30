package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.gui.control.Document;

public class OpenDocumentTabAction implements Action {
    // private static TabView control;

    @Override
    public void init() {
        // control = MainAreaPart.getComponent();
    }

    public static void execute(Document document) {
        AddMainAreaTabAction.execute(document);
    }

    @Override
    public String getName() {
        return "open.document.tab.action";
    }
}