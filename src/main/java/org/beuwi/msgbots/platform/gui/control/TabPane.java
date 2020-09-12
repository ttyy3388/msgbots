package org.beuwi.msgbots.platform.gui.control;

import com.sun.javafx.scene.control.TabObservableList;
import javafx.beans.DefaultProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.geometry.Side;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

@DefaultProperty("tabs")
public class TabPane extends VBox
{
	// public static final EventType<Event> SELECTION_CHANGED_EVENT = new EventType<Event> (Event.ANY, "SELECTION_CHANGED_EVENT");

	private static final String DEFAULT_STYLE_CLASS = "tab-pane";

	private static final double DEFAULT_MIN_WIDTH = 200;
	private static final double DEFAULT_MIN_HEIGHT = 500;

	private static final double DEFAULT_TAB_WIDTH = 100;
	private static final double DEFAULT_TAB_HEIGHT = 30;

	private static final double DEFAULT_SCROLL_SPEED = 0.005;

	// private static final double DEFAULT_TAB_WIDTH = Double.MAX_VALUE;
	// private static final double DEFAULT_TAB_HEIGHT = Double.MAX_VALUE;
	// private static final double DEFAULT_TAB_HEIGHT = Double.MAX_VALUE;

	private static final PseudoClass DEFAULT_TAB_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	// Tab Type Property
	private final ObjectProperty<Type> type = new SimpleObjectProperty<>(Type.NORMAL);

	// Tab Side Property
	private final ObjectProperty<Side> side = new SimpleObjectProperty<>(Side.TOP);

	// Selected Tab Property
	private final ObjectProperty<Tab> selected = new SimpleObjectProperty();

	// Tab List
	private final ObservableList<Tab> tabs = new TabObservableList<>(new ArrayList<>());

	// Tab Content Pane
	private final StackPane content = new StackPane();

	// Tab Header Scroll Pane
	private ScrollPane scroll = new ScrollPane();

	// Tab Header Area (Default : HBox)
	private HBox header = new HBox();

	private VBox root = new VBox();

	public enum Type
	{
		NORMAL, SYSTEM
	};

	// Scroll Bar Pos
	private int pos = 0;

	{
		VBox.setVgrow(content, Priority.ALWAYS);
		HBox.setHgrow(content, Priority.ALWAYS);
	}

	public TabPane()
	{
		this(null);
	}

	public TabPane(Tab... tabs)
	{
		if (tabs != null)
		{
			getTabs().addAll(tabs);
		}

		scroll.setHvalue(1.0d);
		scroll.setContent(header);
		scroll.setMinHeight(DEFAULT_TAB_HEIGHT);
		scroll.setMaxHeight(DEFAULT_TAB_HEIGHT);
		scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
		scroll.setFitToHeight(true);

		scroll.setOnScroll(event ->
		{
			scroll.setHvalue(scroll.getHvalue() - (event.getDeltaY()  * DEFAULT_SCROLL_SPEED));
		});

		this.tabs.addListener((ListChangeListener<Tab>) change ->
		{
			while (change.next())
			{
				for (Tab tab : change.getRemoved())
				{
					tab.setTabPane(null);

					header.getChildren().remove(tab);

					if (change.getList().isEmpty())
					{
						getChildren().remove(scroll);
					}
				}

				for (Tab tab : change.getAddedSubList())
				{
					tab.setTabPane(this);

					header.getChildren().add(tab);

					selected.set(tab);

					if (!getChildren().contains(scroll))
					{
						getChildren().add(0, scroll);
					}
				}
			}
		});

		scroll.getStyleClass().add("tab-header-area");
		content.getStyleClass().add("tab-content-area");

		type.addListener((observable, oldType, newType) ->
		{
			switch (newType)
			{
				case NORMAL : break;
				case SYSTEM :

					/* if (getSide().isVertical())
					{
						return ;
					} */

					List<Tab> list = this.tabs;

					widthProperty().addListener(change ->
					{
						for (Tab tab : list)
						{
							tab.setMinWidth(getWidth() / list.size());
						}
					});

					break;
			}
		});

		selected.addListener((observable, oldTab, newTab) ->
		{
			if (oldTab != null)
			{
				content.getChildren().remove(oldTab.getContent());
				content.getChildren().add(newTab.getContent());

				oldTab.pseudoClassStateChanged(DEFAULT_TAB_PSEUDO_CLASS, false);
				newTab.pseudoClassStateChanged(DEFAULT_TAB_PSEUDO_CLASS, true);
			}
			else
			{
				/* if (getTabs().size() > 1)
				{

				}
				else
				{
					getSelectedTab().pseudoClassStateChanged(DEFAULT_TAB_PSEUDO_CLASS, true);
				} */

				getSelectedTab().pseudoClassStateChanged(DEFAULT_TAB_PSEUDO_CLASS, true);
			}
		});

		// setMinWidth(DEFAULT_MIN_WIDTH);
		// setMinHeight(DEFAULT_MIN_HEIGHT);
		getChildren().addAll(scroll, content);
		getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}

	public void select(int index)
	{
		setSelectedTab(getTab(index));
	}

	public void select(Tab tab)
	{
		setSelectedTab(tab);
	}

	public void addTab(Tab... tabs)
	{
		for (Tab tab : tabs)
		{
			if (!isContains(tab))
			{
				this.tabs.add(tab);
			}
		}
	}

	public boolean isContains(Tab tab)
	{
		return tabs.indexOf(tab) != -1;
	}

	public int getIndex(Tab tab)
	{
		return findTab(tab);
	}

	public int findTab(Tab tab)
	{
		return tabs.indexOf(tab);
	}

	public void close(Tab tab)
	{
		if (!tab.isClosable())
		{
			return ;
		}

		int index = findTab(tab),
			size = tabs.size();

		if (size > 1 && index != -1)
		{
			// If have a next tab
			if (size > index + 1)
			{
				select(index + 1);
			}
			// If have a previous tab
			else if (size > index)
			{
				select(index - 1);
			}
		}

		tabs.remove(tab);
	}

	public Tab getTab(int index)
	{
		return tabs.get(index);
	}

	public ObservableList<Tab> getTabs()
	{
		return tabs;
	}

	public Side getSide()
	{
		return side.get();
	}

	public void setSelectedTab(Tab tab)
	{
		selected.set(tab);
	}

	public void moveSelectTab(int start, int delta)
	{
		if (getTabs().isEmpty())
		{
			return;
		}

		int index = start + delta;

		if (index != -1)
		{
			if (tabs.size() < index + 1)
			{
				select(0);
			}
			else
			{
				select(index);
			}
		}
		else
		{
			select(tabs.size() - 1);
		}
	}

	public void selectNextTab(Tab tab)
	{
		moveSelectTab(getIndex(tab), 1);
	}

	public void selectPreviousTab(Tab tab)
	{
		moveSelectTab(getIndex(tab),-1);
	}

	public Type getType()
	{
		return type.get();
	}

	public void setSide(Side side)
	{
		this.side.set(side);
	}

	/* public void setSide(Side side)
	{
		// scroll = new ScrollPane();

		switch (side)
		{
			case TOP -> root = new VBox(scroll, content);
			case RIGHT ->
			{
				scroll.setRotate(90);
				root = new HBox(content, scroll);
			}
			case BOTTOM -> root = new VBox(content, scroll);
			case LEFT   ->
			{
				scroll.setRotate(270);
				root = new HBox(scroll, content);
			}
		}

		getChildren().clear();
		getChildren().add(root);

		this.side.set(side);
	} */

	public void setType(Type type)
	{
		this.type.set(type);
	}

	public HBox getHeaderArea()
	{
		return header;
	}

	public Pane getContentArea()
	{
		return content;
	}

	public Tab getSelectedTab()
	{
		return selected.get();
	}
}