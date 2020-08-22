package org.beuwi.msgbots.platform.gui.area;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.control.TabPane;
import org.beuwi.msgbots.platform.util.ResourceUtils;

import java.util.List;

public class EditorPane extends SplitPane
{
	private final ObjectProperty<Pane> selected = new SimpleObjectProperty();

	public EditorPane()
	{
		getItems().addListener((ListChangeListener.Change<? extends Node> change) ->
		{
			while (change.next())
			{
				for (Node pane : change.getRemoved())
				{
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

		getStyleClass().add("editor-area");
		getStylesheets().add(ResourceUtils.getStyle("editor-area-part"));
	}

	public TabPane getSelectedPane()
	{
	    List<Node> list = getItems();

		if (list.isEmpty())
		{
			list.add(new TabPane());
		}

		return (TabPane) selected.get();
	}
}