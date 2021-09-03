package org.beuwi.msgbots.view.app.actions;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.view.app.parts.DebugAreaPart;

import java.util.List;

public class ToggleDebugAreaAction implements Executor {
    private static ToggleDebugAreaAction instance;

    private final Pane target = DebugAreaPart.getInstance();
    private final Pane parent = (Pane) target.getParent();

    public void execute() {
        if (parent == null) {
            // throw new NullPointerException();
            return ;
        }
        List<Node> list = parent.getChildren();
        if (list.contains(target)) {
            list.remove(target);
        }
        else {
            list.add(target);
        }
    }

    public static ToggleDebugAreaAction getInstance() {
        if (instance == null) {
            instance = new ToggleDebugAreaAction();
        }
        return instance;
    }
}
