package org.beuwi.msgbots.platform.gui.layout;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;

// @DefaultProperty("items")
public class AnchorPanel extends javafx.scene.layout.AnchorPane
{
	private static final String DEFAULT_STYLE_CLASS = "anchor-panel";

	public AnchorPanel()
	{
		this(null);
	}

	public AnchorPanel(Node content)
	{
		if (content != null)
		{
			setItem(content);
		}
	}

	public void addItem(Node... items)
	{
		getItems().addAll(items);
	}

	public void setItem(Node... items)
	{
		getItems().setAll(items);
	}

	public void setPadding(double padding)
	{
		super.setPadding(new Insets(padding));
	}

	public void addStyleClass(String style)
	{
		getStyleClass().add(style);
	}

	public Node getItem(int index)
	{
		return getItems().get(index);
	}

	public ObservableList<Node> getItems()
	{
		return getChildren();
	}

	public ReadOnlyObjectProperty<Parent> getParentProperty()
	{
		return parentProperty();
	}

	public static void setFitContent(Node node)
	{
        AnchorPanel.setTopAnchor(node, 0.0);
        AnchorPanel.setRightAnchor(node, 0.0);
        AnchorPanel.setBottomAnchor(node, 0.0);
        AnchorPanel.setLeftAnchor(node, 0.0);
	}
}