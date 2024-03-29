package org.beuwi.msgbots.view.gui.control;

import javafx.event.EventTarget;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import org.beuwi.msgbots.view.gui.control.base.ContextMenuBase;

public class ContextMenu extends ContextMenuBase {
	public ContextMenu() {
		this(null);
	}

	public ContextMenu(MenuItem... items) {
		if (items != null) {
			initItems(items);
		}
	}

	public void setNode(Node item) {
		if (item instanceof Button) {
			Button button = ((Button) item);
			button.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
				if (!isShowing()) {
					show(item, Side.BOTTOM, event);
				}
				else {
					hide();
				}
			});
			
			addChangeListener("showing", change -> {
				button.setPseudoClass("showing", isShowing());
			});
		}
		else {
			// [ListView]의 경우 하위 아이템에 우클릭 시 메뉴가 중첩되어 뜨는 문제점 방지
			item.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
				if (item instanceof ListView) {
					EventTarget target = event.getTarget();
					String id = target.toString();

					if (id.contains("Group") ||
						id.contains("ListViewSkin")) {
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
		MouseButton button = event.getButton();
		if (button.equals(MouseButton.SECONDARY)) {
			show(node, event.getScreenX(), event.getScreenY());
		}
		else {
			hide();
		}
	}

	public void show(Node node, Side side, MouseEvent event) {
		MouseButton button = event.getButton();
		if (button.equals(MouseButton.PRIMARY)) {
			show(node, side, 0, 0);
		}
		else {
			hide();
		}
	}
}
