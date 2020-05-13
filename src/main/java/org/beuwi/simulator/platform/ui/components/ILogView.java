package org.beuwi.simulator.platform.ui.components;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Control;
import javafx.scene.control.ListCell;
import javafx.scene.control.SelectionMode;
import org.beuwi.simulator.managers.LogManager;

public class ILogView extends IListView
{
	{
		getStyleClass().add("ifx-log-view");
	}

	// 인자 없으면 Global Log
	public ILogView()
	{
		this(null);
	}

	public ILogView(String name)
	{
		if (name != null)
		{
			setItems(LogManager.load(name));
		}
		else
		{
			setItems(LogManager.load());
		}

		getItems().addListener((ListChangeListener.Change change) ->
		{
			while (change.next())
			{
				for (Object object : getItems())
				{
					ILogItem item = (ILogItem) object;

					item.setView(this);
				}
			}
		});

		setCellFactory(param -> new ListCell<ILogItem>()
		{
			{
				prefWidthProperty().bind(this.widthProperty());
				setMaxWidth(Control.USE_PREF_SIZE);
			}

			@Override
			protected void updateItem(ILogItem item, boolean empty)
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

		getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
}