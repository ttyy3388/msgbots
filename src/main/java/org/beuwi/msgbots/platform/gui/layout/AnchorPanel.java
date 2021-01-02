package org.beuwi.msgbots.platform.gui.layout;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;

// @DefaultProperty("items")
public class AnchorPanel extends javafx.scene.layout.AnchorPane
{
	private static final String DEFAULT_STYLE_CLASS = "anchor-panel";

	public AnchorPanel()
	{
		this(null);
	}

	public AnchorPanel(Node content) {
		if (content != null) {
			getChildren().setAll(content);
		}
	}

	public void setAnchor(double value) {
		setTopAnchor(this, value);
		setRightAnchor(this, value);
		setBottomAnchor(this, value);
		setLeftAnchor(this, value);
	}

	public void setPadding(double padding) {
		super.setPadding(new Insets(padding));
	}

	/* public ObservableList<Node> getItems() {
		return getChildren();
	} */
}