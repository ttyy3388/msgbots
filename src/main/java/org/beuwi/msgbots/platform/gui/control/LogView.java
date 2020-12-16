package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;
import org.beuwi.msgbots.manager.LogManager;

public class LogView extends ListView<LogBox>
{
	private static final String DEFAULT_STYLE_CLASS = "log-view";
	private static final int DEFAULT_GAP_VALUE = 10;

	public LogView()
	{
		this(null);
	}

	// Name : Script Name
	public LogView(String name)
	{
		if (name != null)
		{
			setItems(LogManager.load(name));
		}
		else
		{
			setItems(LogManager.load());
		}

		getItems().addListener((ListChangeListener<LogBox>) change ->
		{
			while (change.next())
			{
				for (LogBox logbox : change.getRemoved())
				{
					logbox.setView(null);
				}

				for (LogBox logbox : change.getAddedSubList())
				{
					logbox.setView(this);
				}
			}
		});

		/* this.setCellFactory(param -> new ListCell<LogBox>()
		{
			{
				getPrefWidthProperty().bind(this.getWidthProperty());
				getMaxWidthProperty().bind(this.getWidthProperty());
			}

			@Override
			protected void updateItem(LogBox item, boolean empty)
			{
				super.updateItem(item, empty);

				if (item != null && !empty)
				{
					setGraphic(item);
				}
				else
				{
					setGraphic(null);
				}
			}
		}); */

		// setSpacing(DEFAULT_GAP_VALUE);
		addStyleClass(DEFAULT_STYLE_CLASS);
		// setSelectionMode(SelectionMode.MULTIPLE);
	}
}