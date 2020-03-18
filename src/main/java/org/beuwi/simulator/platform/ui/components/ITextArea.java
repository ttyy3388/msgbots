package org.beuwi.simulator.platform.ui.components;

import javafx.scene.layout.AnchorPane;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

public class ITextArea extends AnchorPane
{
	VirtualizedScrollPane SCROLL_PANE = null;
	CodeArea CODE_AREA = new CodeArea();

	{
		SCROLL_PANE = new VirtualizedScrollPane(CODE_AREA);

		AnchorPane.setTopAnchor   (SCROLL_PANE, .0);
		AnchorPane.setRightAnchor (SCROLL_PANE, .0);
		AnchorPane.setBottomAnchor(SCROLL_PANE, .0);
		AnchorPane.setLeftAnchor  (SCROLL_PANE, .0);
	}

	public ITextArea()
	{
		getChildren().add(SCROLL_PANE);

		// getStyleClass().add("i-text-area");
	}

	public void setText(String text)
	{
		CODE_AREA.replaceText(0, 0, text);
	}

	public String getText()
	{
		return CODE_AREA.getText();
	}
}