package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;

import java.util.List;

public class Button extends javafx.scene.control.Button
{
	private static final String DEFAULT_STYLE_CLASS = "button";
	private static final String ACTION_STYLE_CLASS = "button-action";
	private static final String CANCEL_STYLE_CLASS = "button-cancel";

	private final double DEFAULT_WIDTH = 70;
	private final double DEFAULT_HEIGHT = 30;

	private final ObjectProperty<Type> type = new SimpleObjectProperty<>(null);

	private final BooleanProperty styled = new SimpleBooleanProperty(false);

	public enum Type
	{
		ACTION, CANCEL
	}

	public Button()
	{
		setPrefWidth(DEFAULT_WIDTH);
		setPrefHeight(DEFAULT_HEIGHT);

		styled.addListener(change ->
		{
			pseudoClassStateChanged(PseudoClass.getPseudoClass("styled"), isStyled());
		});

		type.addListener(change ->
		{
			List<String> list = getStyleClass();

			if (list.size() > 1)
			{
				list.remove(list.get(1));
			}

			switch (getType())
			{
				case ACTION : list.add(ACTION_STYLE_CLASS); break;
				case CANCEL : list.add(CANCEL_STYLE_CLASS); break;
			}
		});

		// setType(Type.CANCEL);
	}

	public boolean isStyled()
	{
		return styled.get();
	}

	public Type getType()
	{
		return type.get();
	}

	public void setType(Type type)
	{
		this.type.set(type);
	}

	public void setMenu(ContextMenu menu)
	{
		menu.setNode(this);
	}

	public void setStyled(boolean styled)
	{
		this.styled.set(styled);
	}
}
