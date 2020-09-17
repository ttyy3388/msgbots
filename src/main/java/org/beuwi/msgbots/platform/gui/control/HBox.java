package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.Node;
import javafx.scene.layout.Region;

import java.util.List;

public class HBox extends javafx.scene.layout.HBox
{
	public static final String DEFAULT_STYlE_CLASS = "hbox";

	private int index = 0;

	public HBox()
	{
		/* getChildren().addListener((ListChangeListener) change ->
		{
			while (change.next())
			{
				if (change.wasAdded())
				{
				    Node item = getItems().get(index);

					if (item instanceof Region)
                    {
                        ((Region) item).setMinHeight(getMinHeight());
                        ((Region) item).setPrefHeight(getPrefHeight());
                    }

					index ++;
				}
			}
		}); */

		heightProperty().addListener(change ->
        {
            for (Node item : getItems())
            {
                if (item instanceof Region)
                {
                    // ((Region) item).setMinHeight(getHeight());
                    ((Region) item).setPrefHeight(getHeight());
                }
            }
        });

		getStyleClass().add(DEFAULT_STYlE_CLASS);
	}

	public void addItem(Node item)
	{
		getChildren().add(item);
	}

	public Node getItem(int index)
	{
		return getItems().get(index);
	}

	public List<Node> getItems()
	{
		return getChildren();
	}
}