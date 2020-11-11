package org.beuwi.msgbots.platform.gui.layout;

import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

// @DefaultProperty("content")
public class ShadowPanel extends StackPanel
{
    private static final String DEFAULT_STYLE_CLASS = "shadow-panel";
    private static final DropShadow DEFAULT_DROP_SHADOW = new DropShadow(BlurType.THREE_PASS_BOX, Color.color(0, 0, 0, 0.8), 10, 0, 0, 0);

    public ShadowPanel()
	{
		this(null);
	}

	public ShadowPanel(Node content)
	{
		if (content != null)
		{
			setItem(content);
		}

		setEffect(DEFAULT_DROP_SHADOW);
		addStyleClass(DEFAULT_STYLE_CLASS);
	}

	public void setContent(Node content)
	{
		setItem(content);
	}

	public Node getContent()
	{
		return getItem(0);
	}

	/* @Override
	public void setPadding(double padding)
	{
		super.setPadding(padding);
	} */
}