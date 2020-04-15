package org.beuwi.simulator.platform.application.views.tabs;

import javafx.scene.control.ListView;

public class GlobalLogTab
{
	private static ListView component;

	public static void initialize()
	{
		/* ListView<AnchorPane> listView = getLogListView();

		listView.setCellFactory(param -> new ListCell<AnchorPane>()
		{
			{
				//.subtract(2)
				prefWidthProperty().bind(listView.widthProperty());
				setMaxWidth(Control.USE_PREF_SIZE);
			}

			@Override
			protected void updateItem(AnchorPane item, boolean empty)
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
	}

	public static ListView getComponent()
	{
		return component;
	}
}
