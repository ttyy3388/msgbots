package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Document extends TabItem {
	private final ObjectProperty<Page> pageProperty = new SimpleObjectProperty();

	public Document(String name, Page page) {
		setPage(page);
		setText(name);
		setContent(page);
	}

	public void setPage(Page page) {
		pageProperty.set(page);
	}
	public Page getPage() {
		return pageProperty.get();
	}
	public ObjectProperty<Page> pageProperty() {
		return pageProperty;
	}
}