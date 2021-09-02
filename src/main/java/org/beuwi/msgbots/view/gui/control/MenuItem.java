package org.beuwi.msgbots.view.gui.control;

import javafx.beans.binding.BooleanExpression;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.HBox;

import org.beuwi.msgbots.keyboard.KeyBinding;

public class MenuItem extends javafx.scene.control.MenuItem {

	public MenuItem(String text, KeyBinding binding, EventHandler handler) {
		Label name = new Label(text);
		name.setMinWidth(150);
		HBox hbox = new HBox(name);

		setGraphic(hbox);
		setOnAction(handler);

		if (binding != null && binding.getKeyCombi() != null) {
			setAccelerator(binding.getKeyCombi());
		}

		getStyleClass().add("menu-item");
	}

	public MenuItem disable(boolean disable) {
		setDisable(disable);
		return this;
	}
	public MenuItem disable(BooleanExpression property) {
		parentPopupProperty().addListener(change1 -> {
			ContextMenu parent = getParent();
			if (parent != null) {
				parent.showingProperty().addListener(change2 -> {
					setDisable(property.get());
				});
			}
		});

		/* property.addListener((observable, oldValue, newValue) -> {
			setDisable(newValue);
		}); */

		return this;
	}

	public MenuItem enable(boolean enable) {
		setDisable(!enable);
		return this;
	}
	public MenuItem enable(BooleanExpression property) {
		// 기본값 : 비활성화
		if (property != null) {
			setDisable(true);
		}

		parentPopupProperty().addListener(change1 -> {
			ContextMenu parent = getParent();
			if (parent != null) {
				parent.showingProperty().addListener(change2 -> {
					setDisable(!property.get());
				});
			}
		});
		/* property.addListener(change -> {
			setDisable(!property.get());
		}); */

		return this;
	}

	public ContextMenu getParent() {
		return (ContextMenu) getParentPopup();
	}

	public static SeparatorMenuItem getSeparator() {
		return new SeparatorMenuItem();
	}
}
