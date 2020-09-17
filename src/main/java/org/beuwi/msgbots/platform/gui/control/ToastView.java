package org.beuwi.msgbots.platform.gui.control;

import java.util.List;

public class ToastView extends ListView<HBox>
{
	private static final String DEFAULT_STYLE_CLASS = "toast-view";
	private static final int DEFAULT_MIN_WIDTH = 500;

	private final int MAX_MESSAGE = 10;

	public ToastView()
	{
		List<HBox> items = this.getItems();

		/* getItems().addListener((ListChangeListener.Change<? extends AnchorPane> change) ->
		{
			while (change.next())
			{
				if (change.wasAdded())
				{
					if (items.size() > MAX_MESSAGE)
					{
						getItems().remove(0);
					}
				}
			}
		});


		for (int i = 0 ; i < MAX_MESSAGE ; i ++)
		{
			AnchorPane pane = new AnchorPane();
			pane.setMinHeight(100);
			pane.setPrefHeight(100);
			addItem(pane);
		} */

		setMinHeight(DEFAULT_MIN_WIDTH);
	    getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}
