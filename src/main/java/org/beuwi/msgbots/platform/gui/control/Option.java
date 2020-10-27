package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.DefaultProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;

@DefaultProperty("content")
public class Option extends Label
{
	public static final String DEFAULT_STYLE_CLASS = "option";

	private static final Pos DEFAULT_TEXT_ALIGNMENT = Pos.CENTER_LEFT;
	private static final int DEFAULT_OPTION_HEIGHT = 30;

	private final ObjectProperty<Region> content = new SimpleObjectProperty(null);

	private OptionView parent;

	public Option()
	{
		this(null);
	}

	public Option(String title)
	{
		this(title, new Region());
	}

	public Option(String title, Region content)
	{
		if (title != null)
		{
			setText(title);
		}

		if (content != null)
		{
			setContent(content);
		}

		this.setOnMousePressed(event ->
		{
			parent.select(this);
		});

		setPadding(new Insets(0, 0, 0, 5));
		setAlignment(DEFAULT_TEXT_ALIGNMENT);
		setMinHeight(DEFAULT_OPTION_HEIGHT);
		setMaxHeight(DEFAULT_OPTION_HEIGHT);
		getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}

	/* @Override
	public void setHeight(double height)
	{
		super.setMinHeight(height);
		super.setPrefHeight(height);
		super.setMaxHeight(height);
	} */

	public String getTitle()
	{
		return getText();
	}

	public OptionView getView()
	{
		return parent;
	}

	public Region getContent()
	{
		return content.get();
	}

	public void setView(OptionView parent)
	{
		this.parent = parent;
	}

	public void setTitle(String title)
	{
		this.setText(title);
	}

	public void setContent(Region content)
	{
		this.content.set(content);
	}
}