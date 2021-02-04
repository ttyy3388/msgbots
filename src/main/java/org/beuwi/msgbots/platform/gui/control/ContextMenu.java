package org.beuwi.msgbots.platform.gui.control;

import javafx.css.PseudoClass;
import javafx.event.EventTarget;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ContextMenu extends javafx.scene.control.ContextMenu
{
	private static final String DEFAULT_STYLE_CLASS = "context-menu";

	public ContextMenu() {
		this(null);
	}

	public ContextMenu(MenuItem... items) {
		getItems().setAll(items);
	}

	public void setNode(Node item) {
		if (item instanceof Button) {
			item.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
				if (!isShowing()) {
					show(item, Side.BOTTOM, event);
				}
				else {
					hide();
				}
			});
			
			this.showingProperty().addListener((observable, oldValue, newValue) -> {
				item.pseudoClassStateChanged(PseudoClass.getPseudoClass("showing"), newValue);
			});
		}
		else
		{
			item.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
				if (item instanceof ListView) {
					EventTarget target = event.getTarget();

					if (target.toString().contains("Group") || target.toString().contains("ListViewSkin")) {
						show(item, event);
					}
				}
				else {
					show(item, event);
				}
			});

			item.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
				if (isShowing()) {
					hide();
				}
			});
		}
	}

	public void show(Node node, MouseEvent event) {
		if (event.getButton().equals(MouseButton.SECONDARY)) {
			show(node, event.getScreenX(), event.getScreenY());
		}
		else {
			hide();
		}
	}

	public void show(Node node, Side side, MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			show(node, side, 0, 0);
		}
		else {
			hide();
		}
	}
}
