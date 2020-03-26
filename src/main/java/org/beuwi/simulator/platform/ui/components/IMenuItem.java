package org.beuwi.simulator.platform.ui.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;

public class IMenuItem extends MenuItem
{
	public IMenuItem(String text)
	{
		this(text, null);
	}

	public IMenuItem(String text, String command)
	{
		this(text, command, null);
	}

	public IMenuItem(String text, String command, EventHandler<ActionEvent> handler)
	{
		this(null, text, command, handler);
	}

	public IMenuItem(Image image, String text, String command, EventHandler<ActionEvent> handler)
	{
		HBox cell = new HBox();
		cell.setPrefWidth(150);

		Label name = new Label(text);
		name.setPrefWidth(150);

		if (command != null)
		{
			setAccelerator(KeyCombination.keyCombination(command));
		}

		cell.getChildren().addAll(new ImageView(image), name);

		setGraphic(cell);
		setOnAction(handler);
	}
}