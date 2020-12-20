package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.skins.TabSkin;
import org.beuwi.msgbots.platform.util.AllSVGIcons;

// @DefaultProperty("content")
public class Tab extends Control
{
	private static final String DEFAULT_STYLE_CLASS = "tab";

	private final ObjectProperty<Node> content = new SimpleObjectProperty(null);
	private final BooleanProperty closable = new SimpleBooleanProperty(true);
	// private final ObjectProperty<Type> type = new SimpleObjectProperty(null);
	private final StringProperty text = new SimpleStringProperty(null);

	private final ContextMenu menu;

	private TabView parent;

	public Tab()
	{
		this(null);
	}

	public Tab(String title)
	{
		this(title, new Pane());
	}

	public Tab(String title, Node content)
	{
		if (title != null)
		{
			setId(title);
			setText(title);
		}

		if (content != null)
		{
			setContent(content);
		}

		menu = new ContextMenu
		(
			new Menu("Close", "Ctrl + W", event -> parent.closeTab(this)),
			new Menu("Close Others", event -> parent.closeOtherTabs(this)),
			new Menu("Close All Tabs", event -> parent.closeAllTabs()),
			new Separator(),
			new Menu("Select Next Tab", "Ctrl + Tab", event -> parent.selectNextTab(this)),
			new Menu("Select Previous Tab", "Ctrl + Shift + Tab", event ->  parent.selectPrevTab(this))
		);

		menu.setNode(this);

		this.setOnMousePressed(event ->
		{
			getView().selectTab(this);
		});

		addStyleClass(DEFAULT_STYLE_CLASS);
	}

	public void setView(TabView parent)
	{
		this.parent = parent;
	}

	public void setText(String text)
	{
		this.text.set(text);
	}

	public void setClosable(boolean closable)
	{
		this.closable.set(closable);
	}

	public void setContent(Node content)
	{
		this.content.set(content);
	}

	public TabView getView()
	{
		return parent;
	}

	public String getText()
	{
		return text.get();
	}

	public Node getContent()
	{
		return content.get();
	}

	public boolean isClosable()
	{
		return closable.get();
	}

	public boolean isSelected()
	{
		return parent.getSelectedTab().equals(this);
	}

	public StringProperty getTextProperty()
	{
		return text;
	}

	public BooleanProperty getClosableProperty()
	{
		return closable;
	}

	@Override
	public Skin<?> setDefaultSkin()
	{
		return new TabSkin(this);
	}
}