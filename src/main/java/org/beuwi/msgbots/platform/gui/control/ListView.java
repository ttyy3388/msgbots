package org.beuwi.msgbots.platform.gui.control;

public class ListView<T> extends javafx.scene.control.ListView<T>
{
	public void addItem(T item)
	{
		getItems().add(item);
		scrollTo(item);
	}

	public T getItem(int index)
	{
		return getItems().get(index);
	}
}
