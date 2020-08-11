package org.beuwi.msgbots.platform.gui.control;

import com.sun.javafx.scene.control.TabObservableList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TabPane extends VBox
{
	// public static final EventType<Event> SELECTION_CHANGED_EVENT = new EventType<Event> (Event.ANY, "SELECTION_CHANGED_EVENT");
	// private static final double DEFAULT_TAB_WIDTH = Double.MAX_VALUE;
	// private static final double DEFAULT_TAB_HEIGHT = Double.MAX_VALUE;
	// private static final double DEFAULT_TAB_HEIGHT = Double.MAX_VALUE;

	private static final double DEFAULT_HEADER_HEIGHT = 40;

	private static final PseudoClass DEFAULT_TAB_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	// Selected Tab Property
	private static final ObjectProperty<Tab> property = new SimpleObjectProperty();

	private static final ObservableList<Tab> tabs = new TabObservableList<>(new ArrayList<>());

	// Tab Header Scroll Pane
	private ScrollPane scroll = new ScrollPane();

	// Tab Header Area
	private HBox header = new HBox();

	// Tab Content Pane
	private StackPane content = new StackPane();

	{
		VBox.setVgrow(content, Priority.ALWAYS);
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

		scroll.setContent(header);
		scroll.setPrefHeight(DEFAULT_HEADER_HEIGHT);
		scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
		scroll.setFitToHeight(true);

		this.tabs.addListener((ListChangeListener<Tab>) change ->
		{
			while (change.next())
			{
				for (Tab tab : change.getRemoved())
				{
					header.getChildren().remove(tab);
					// content.getChildren().remove(tab);
				}

				for (Tab tab : change.getAddedSubList())
				{
					if (tab != null)
					{
						tab.setTabPane(this);

						header.getChildren().add(tab.getHeader());
						content.getChildren().add(tab.getContent());

						header.getStyleClass().add("tab-header-area");
						content.getStyleClass().add("tab-content-area");

						// property.set(tab);

						this.setSelectedTab(tab);
						this.getChildren().addAll(scroll, content);
					}
				}
			}
		});

		property.addListener((observable, oldValue, newValue) ->
		{
			Node content = newValue.getContent();
			Node target = oldValue.getContent();

			target.setVisible(false);
			target.setDisable(true);

			content.setVisible(true);
			content.setDisable(false);

			oldValue.pseudoClassStateChanged(DEFAULT_TAB_PSEUDO_CLASS, false);
			newValue.pseudoClassStateChanged(DEFAULT_TAB_PSEUDO_CLASS, true);
		});

		getStyleClass().setAll("tab-pane");
	}

	// Select Tab
	/* private void select(int index) {
		select(getTab(index));
	} */

	public Tab getSelectedTab()
	{
		return property.get();
	}

	public void setSelectedTab(Tab tab)
	{
		property.set(tab);
	}

	public void setClosable(boolean value)
	{
		if (!value)
		{
			return ;
		}

		for (int i = 0 ; i < tabs.size() ; i ++)
		{
			tabs.get(i).getChildren().remove(tabs.get(i).getButton());
		}
	}

	public boolean isContains(Tab tab)
	{
		for (int i = 0 ; i < tabs.size() ; i ++)
		{
			if (tabs.get(i).getId().equals(tab.getId()))
			{
				return true;
			}
		}

		return false;
	}

	public int findTab(Tab tab)
	{
		for (int index = 0 ; index < tabs.size() ; index ++)
		{
			if (tabs.get(index).equals(tab))
			{
				return index;
			}
		}

		return -1;
	}

	public void close(Tab tab)
	{
		int index = findTab(tab);

		if (tabs.size() > 0 && index != -1)
		{
			if (tabs.size() > index + 1)
			{
				select(index + 1);
			}
			else if (tabs.get(index - 1) != null)
			{
				select(index - 1);
			}
		}

		tabs.remove(tab);
	}

	public void select(int index)
	{
		setSelectedTab(getTab(index));
	}

	public void select(Tab tab)
	{
		setSelectedTab(tab);
	}

	public void addTab(Tab tab)
	{
		if (!isContains(tab))
		{
			tabs.add(tab);
		}
	}

	public Tab getTab(int index)
	{
		return tabs.get(index);
	}

	public ObservableList<Tab> getTabs()
	{
		return tabs;
	}

	/* public Tab getSelectedTab()
	{
		return tab;
	} */
}