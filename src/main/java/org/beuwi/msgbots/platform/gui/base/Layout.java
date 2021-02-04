package org.beuwi.msgbots.platform.gui.base;

public interface Layout extends Control {
	// 다중 상속으로 구현하려고 했으나 Interface 에서는 정적변수만 허용되므로 주석된것들은 파기
	// public final ObjectProperty<Pane> pane = new SimpleObjectProperty();

	/* default void Layout(Parent parent) {
		this.parent = parent;
	} */

	/* default void Layout(Pane pane) {
	} */

	// Node node = null;
	/* default void registLayout(Pane pane) {
		// Must be input
		this.pane.set(pane);
	} */

	// void addChildren(Node... children);
	// void setChildren(Node... children);

	/* default void setChildren(Node... nodes) {
		pane.get().getChildren().setAll(nodes);
	}
	default void addChildren(Node... nodes) {
		pane.get().getChildren().addAll(nodes);
	}
	private List getChildren() {
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
