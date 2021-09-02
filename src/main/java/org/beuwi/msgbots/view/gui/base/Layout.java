package org.beuwi.msgbots.view.gui.base;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.Collection;

public interface Layout extends Control {
	// 다중 상속으로 구현하려고 했으나 Interface 에서는 정적변수만 허용되므로 주석된것들은 파기
	// public final ObjectProperty<Pane> pane = new SimpleObjectProperty();

	/* default void Layout(Parent parent) {
		this.parent = parent;
	} */

    void addChild(Node child);
    default void addChild(Pane pane, Node child) {
        pane.getChildren().add(child);
    }
    void addChild(int index, Node child);
    default void addChild(Pane pane, int index, Node child) {
        pane.getChildren().add(index, child);
    }
    void addChildren(Node... children);
    default void addChildren(Pane pane, Node... children) {
        pane.getChildren().addAll(children);
    }
    void addChildren(Collection<? extends Node> children);
    default void addChildren(Pane pane, Collection<? extends Node> children) {
        pane.getChildren().addAll(children);
    }

    void setChild(int index, Node child);
    default void setChild(Pane pane, int index, Node child) {
        pane.getChildren().set(index, child);
    }
    void setChildren(Node... children);
    default void setChildren(Pane pane, Node... children) {
        pane.getChildren().setAll(children);
    }
    void setChildren(Collection<? extends Node> children);
    default void setChildren(Pane pane, Collection<? extends Node> children) {
        pane.getChildren().setAll(children);
    }

	/* private List getChildren() {
		if (pane.get() == null) {
			throw new RuntimeException("Pane is muse be initialize");
		}
		return pane.get().getChildren();
	} */

	/* default void addChangeListener(Property property, ChangeListener listener) {
		property.addListener(listener);
	}
	default void delChangeListener(Property property, ChangeListener listener) {
		property.removeListener(listener);
	} */
}
