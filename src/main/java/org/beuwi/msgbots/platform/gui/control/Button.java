package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;

public class Button extends javafx.scene.control.Button
{
	private final double DEFAULT_WIDTH = 70;
	private final double DEFAULT_HEIGHT = 30;

	private final BooleanProperty styled = new SimpleBooleanProperty(false);

	public Button()
	{
		setPrefWidth(DEFAULT_WIDTH);
		setPrefHeight(DEFAULT_HEIGHT);

		styled.addListener((observable, oldValue, newValue) ->
		{
			pseudoClassStateChanged(PseudoClass.getPseudoClass("styled"), newValue);
		});
	}

	public boolean getStyled()
	{
		return styled.get();
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
