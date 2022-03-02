package org.beuwi.msgbots.view.gui.base;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.Collection;

import org.beuwi.msgbots.base.annotation.NotNull;

public interface Layout /* <P extends Pane> */ extends Control /* <P> */ {
	// 다중 상속으로 구현하려고 했으나 Interface 에서는 정적변수만 허용되므로 주석된것들은 파기
	// public final ObjectProperty<Pane> pane = new SimpleObjectProperty();

	/* default void Layout(Parent parent) {
		this.parent = parent;
	} */

    // [Pane]로 받아서 [getChildren().add]을 하면 문제가 발생함
    // 예를 들어, [StackPane]에 추가를 해야 하는데, [Pane]에 추가하므로 보여지지 않는다는 등,
    // 상위 클래스로 받았을 때 상속과 관련하여 문제가 발생함
    /* void addChild(Node child);
    default void addChild(Pane pane, Node child) {
        pane.getChildren().add(child);
    } */
    void addChild(int index, Node child);
    default void addChild(Pane pane, int index, Node child) {
        pane.getChildren().add(index, child);
    }
    void addChildren(Node... children);
    default void addChildren(Pane pane, Node[] children) {
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
    default void setChildren(Pane pane, Node[] children) {
        pane.getChildren().setAll(children);
    }
    void setChildren(Collection<? extends Node> children);
    default void setChildren(Pane pane, Collection<? extends Node> children) {
        pane.getChildren().setAll(children);
    }
    // void initChild(Node child);
    void initChildren(Node... children);
    default void initChildren(Pane pane, @NotNull Node[] children) {
        pane.getChildren().setAll(children);
    }
    void initChildren(Collection<? extends Node> children);
    default void initChildren(Pane pane, Collection<? extends Node> children) {
        pane.getChildren().setAll(children);
    }

	/* private List getChildren() {
		if (pane.get() == null) {
			throw new RuntimeException("Pane is must be initialize");
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
