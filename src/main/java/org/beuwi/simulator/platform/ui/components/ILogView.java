package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.Control;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.managers.LogManager;

import java.util.List;

public class ILogView extends ListView
{
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

		this.setCellFactory(param -> new ListCell<AnchorPane>()
		{
			{
				//.subtract(2)
				prefWidthProperty().bind(this.widthProperty());
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
		});

		this.getStyleClass().add("log-view");
	}

	public void setItems(List list)
	{
		getItems().addAll(list);
	}
}