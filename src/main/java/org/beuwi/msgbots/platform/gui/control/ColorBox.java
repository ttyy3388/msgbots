package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

public class ColorBox extends HBox<StackPanel>
{
	private static final String DEFAULT_STYLE_CLASS = "color-box";

	private final StackPanel content = new StackPanel();
	private final ColorBar header = new ColorBar();

	{
		HBox.setHgrow(content, Priority.ALWAYS);
	}

	public ColorBox()
	{
		setItems(header, content);

		addStyleClass(DEFAULT_STYLE_CLASS);
	}

	public void setHeader(Node header)
	{
		this.header.setItem(header);
	}

	public void setContent(Node content)
	{
		this.content.setItem(content);
	}

	public ColorBar getHeader()
	{
		return header;
	}

	public Node getContent()
	{
		return content.getItem(0);
	}

	public ColorBar getColorBar()
	{
		return header;
	}
}