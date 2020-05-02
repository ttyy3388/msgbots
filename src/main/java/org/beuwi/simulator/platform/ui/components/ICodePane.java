package org.beuwi.simulator.platform.ui.components;

import javafx.scene.layout.StackPane;

public class ICodePane extends StackPane
{
	{
		this.getStyleClass().add("ifx-code-pane");
	}

	final private IScrollPane pane;
	final private ICodeArea area;

	public ICodePane(String text)
	{
		area = new ICodeArea(text);
		pane = new IScrollPane(area);

		this.getChildren().add(pane);
	}

	public ICodeArea getCodeArea()
	{
		return area;
	}

	public IScrollPane getScrollPane()
	{
		return pane;
	}
}