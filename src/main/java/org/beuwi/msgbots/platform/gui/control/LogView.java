package org.beuwi.msgbots.platform.gui.control;

import org.beuwi.msgbots.manager.LogManager;

public class LogView extends ListView<Log>
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

		this.setCellFactory(param -> new ListCell<Log>()
		{
			{
				//.subtract(2)
				getPrefWidthProperty().bind(this.getWidthProperty());
				// setMaxWidth(Control.USE_PREF_SIZE);
			}

			@Override
			protected void updateItem(Log item, boolean empty)
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
		});

		// setSpacing(DEFAULT_GAP_VALUE);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
		// getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
}