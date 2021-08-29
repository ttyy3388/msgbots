package org.beuwi.msgbots.program.app.view.actions;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.program.app.impl.Executor;
import org.beuwi.msgbots.program.app.view.parts.SideAreaPart;

import java.util.List;

public class ToggleSideAreaAction implements Executor {
	private static ToggleSideAreaAction instance = null;

	private final Pane target = SideAreaPart.getInstance();
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

	public static ToggleSideAreaAction getInstance() {
		if (instance == null) {
			instance = new ToggleSideAreaAction();
		}
		return instance;
	}
}
