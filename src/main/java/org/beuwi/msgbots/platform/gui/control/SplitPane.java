package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class SplitPane extends HBox
{
	private static final String DEFAULT_STYLE_CLASS = "split-pane";

	// private final ObjectProperty<Orientation> orientation = new SimpleObjectProperty<>();

	private final ObjectProperty<Pane> selected = new SimpleObjectProperty();

	private final ObservableList<Pane> items = FXCollections.observableArrayList();

	public SplitPane()
	{
		items.addListener((ListChangeListener.Change<? extends Pane> change) ->
		{
			while (change.next())
			{
				for (Pane pane : change.getRemoved())
				{
					getChildren().remove(pane);

					/* if (selected.get().equals(pane))
					{
						if (ite)
					} */
				}

				for (Pane pane : change.getAddedSubList())
				{
					HBox.setHgrow(pane, Priority.ALWAYS);

					pane.setOnMousePressed(event ->
					{
						selected.set(pane);
					});

					selected.set(pane);

					getChildren().add(pane);

					System.out.println(pane);
				}
			}
		});

		widthProperty().addListener(change ->
		{
			for (Pane pane : items)
			{
				pane.setPrefWidth(getWidth() / items.size());
			}
		});

		/* orientation.addListener(change ->
		{
			switch (orientation.get())
			{
				// LEFT RIGHT
				case HORIZONTAL : root = new HBox(); break;

				// TOP BOTTOM
				case VERTICAL : root = new VBox(); break;
			}

			root.getChildren().setAll(items);
		}); */

		getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}

	public void addItem(Pane pane)
	{
		items.add(pane);
	}

	public Pane getItem(int index)
	{
		return items.get(index);
	}

	public ObservableList<Pane> getItems()
	{
		return items;
	}

	public Pane getSelectedPane()
	{
		return selected.get();
	}

	// spane : Selected Pane, tpane : Target Pane
	public void split(SplitPos pos, Pane pane)
	{
		int index = items.indexOf(pane);

		switch (pos)
		{
			case UP : case DOWN : break;
			case LEFT :

				if (index != 0)
				{
					getChildren().add(index - 1, new TabPane());
				}
				else
				{
					getChildren().add(0, new TabPane());
				}

				break;

			case RIGHT : getChildren().add(index + 1, new TabPane()); break;
		}
	}

	/* public void split(Orientation orientation)
	{
		setOrientation(orientation);
	}

	/* public Orientation getOrientation()
	{
		return orientation.get();
	}

	public void setOrientation(Orientation orientation)
	{
		this.orientation.set(orientation);
	} */

	public void setSelectedPane(Pane pane)
	{
		selected.set(pane);
	}
}
