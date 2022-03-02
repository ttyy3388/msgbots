package org.beuwi.msgbots.view.gui.control;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.HBox;

import org.beuwi.msgbots.keyboard.KeyBinding;
import org.beuwi.msgbots.view.gui.control.base.MenuItemBase;

public class MenuItem extends MenuItemBase {
	public MenuItem(String text, KeyBinding binding, EventHandler handler) {
		Label name = new Label(text);
		name.setMinWidth(150);

		HBox hbox = new HBox(name);

		setGraphic(hbox);
		setOnAction(handler);

		if (binding != null && binding.getKeyCombi() != null) {
			setAccelerator(binding.getKeyCombi());
		}

		addStyleClass("menu-item");
	}

	public MenuItem disable(boolean disable) {
		setDisable(disable);
		return this;
	}
	public MenuItem disable(ObservableValue<Boolean>  property) {
		addChangeListener("parentPopup", change1 -> {
			ContextMenu parent = getParent();
			if (parent != null) {
				parent.addChangeListener("showing", change2 -> {
					setDisable(property.getValue());
				});
			}
		});

		return this;
	}

	public MenuItem enable(boolean enable) {
		setDisable(!enable);
		return this;
	}
	public MenuItem enable(ObservableValue<Boolean> property) {
		addChangeListener("parentPopup", change1 -> {
			ContextMenu parent = getParent();
			if (parent != null) {
				parent.addChangeListener("showing", change2 -> {
					setDisable(!property.getValue());
				});
			}
		});
		return this;
	}

	public ContextMenu getParent() {
		return (ContextMenu) getParentPopup();
	}

	public static SeparatorMenuItem getSeparator() {
		return new SeparatorMenuItem();
	}
}
