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

	private static final String ACTION_STYLE_CLASS = "action";
	private static final String CANCEL_STYLE_CLASS = "cancel";

	private static final int DEFAULT_PREF_WIDTH = 70;
	private static final int DEFAULT_PREF_HEIGHT = 30;

	private final ObjectProperty<Type> type = new SimpleObjectProperty(null);

	private final BooleanProperty styled = new SimpleBooleanProperty(false);

	public enum Type
	{
		ACTION, CANCEL
	}

	public Button()
	{
		setPrefWidth(DEFAULT_PREF_WIDTH);
		setPrefHeight(DEFAULT_PREF_HEIGHT);

		styled.addListener(change ->
		{
			pseudoClassStateChanged(PseudoClass.getPseudoClass("styled"), getStyled());
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

	public Type getType()
	{
		return type.get();
	}

	public boolean getStyled()
	{
		return styled.get();
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
