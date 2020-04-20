package org.beuwi.simulator.platform.ui.components;

import javafx.scene.layout.AnchorPane;

public class ICodePane extends AnchorPane
{
	final private IScrollPane pane;

	public ICodePane(ICodeArea area)
	{
		pane = new IScrollPane(area);

		AnchorPane.setTopAnchor	  (pane, .0);
		AnchorPane.setRightAnchor (pane, .0);
		AnchorPane.setBottomAnchor(pane, .0);
		AnchorPane.setLeftAnchor  (pane, .0);

		getChildren().add(pane);
	}
}