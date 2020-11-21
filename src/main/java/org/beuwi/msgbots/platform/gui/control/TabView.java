package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.layout.ScrollPanel;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

// @DefaultProperty("tabs")
public class TabView extends VBox
{
	private static final String DEFAULT_STYLE_CLASS = "tab-view";

	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
	private static final PseudoClass PINNED_PSEUDO_CLASS = PseudoClass.getPseudoClass("pinned");

	private static final String HEADER_STYLE_CLASS = "tab-header-area";
	private static final String CONTENT_STYLE_CLASS = "tab-content-area";

	// Selected Tab Property
	private final ObjectProperty<Tab> selected = new SimpleObjectProperty(null);
	private final ObjectProperty<Type> type = new SimpleObjectProperty(null);

	private final ObservableList<Tab> tabs = FXCollections.observableArrayList();

	// private static final int DEFAULT_HEADER_WIDTH = 100;
	private static final int DEFAULT_HEADER_HEIGHT = 30;

	/* Tab View [ Header Area [ Headers [ Tab Header ( Main ) ] ] , Content Area [ Tab Content ] ] */

	// Tab Content Area
	private final StackPanel content = new StackPanel();

	// Tab Header Area [ Tab Header List ]
	private final ScrollPanel header = new ScrollPanel();

	// Tab Header List
	private final HBox<Tab> headers = new HBox();

	{
		VBox.setVgrow(content, Priority.ALWAYS);
	}

	public enum Type
	{
		NORMAL, SYSTEM
	}

	public TabView()
	{
		this(null);
	}

	public TabView(Tab... tabs)
	{
		if (tabs != null)
		{
			addTabs(tabs);
		}

		header.setHvalue(1.0d);
		header.setContent(headers);
		header.setFitToWidth(false);
		header.setFitToHeight(true);
		header.setMinHeight(DEFAULT_HEADER_HEIGHT);
		header.setMaxHeight(DEFAULT_HEADER_HEIGHT);
		header.setHbarPolicy(ScrollBarPolicy.NEVER);
		header.setVbarPolicy(ScrollBarPolicy.NEVER);

		// header.setMinHeight(DEFAULT_HEADER_HEIGHT);
		header.setPrefHeight(DEFAULT_HEADER_HEIGHT);
		// header.setMaxHeight(DEFAULT_HEADER_HEIGHT);

		header.getStyleClass().add(HEADER_STYLE_CLASS);
		content.getStyleClass().add(CONTENT_STYLE_CLASS);

		getTabs().addListener((ListChangeListener<Tab>) change ->
		{
			while (change.next())
			{
				for (Tab tab : change.getRemoved())
				{
					tab.setView(null);
					headers.remove(tab);
				}

				for (Tab tab : change.getAddedSubList())
				{
					tab.setView(this);
					headers.addItem(tab);
					selected.setValue(tab);

					if (getType() != null && getType().equals(Type.SYSTEM))
					{
						HBox.setHgrow(tab, Priority.ALWAYS);
					}
				}

				this.setVisible(!getTabs().isEmpty());
			}
		});

		getSelectedTabProperty().addListener(change ->
		{
			content.setItem(getSelectedTab().getContent());
		});

		getSelectedTabProperty().addListener((observable, oldTab, newTab) ->
		{
			if (oldTab != null)
			{
				oldTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}

			newTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
		});

		getTypeProperty().addListener(change ->
        {
			switch (getType())
			{
				case NORMAL :

					header.setFitToWidth(false);

					for (Tab tab : getTabs())
					{
						HBox.setHgrow(tab, Priority.NEVER);
					}

					break;

				case SYSTEM :

					header.setFitToWidth(true);

					for (Tab tab : getTabs())
					{
						HBox.setHgrow(tab, Priority.ALWAYS);
					}

					break;
			}
        });

		setItems(header, content);
		setFittable(true);
		addStyleClass(DEFAULT_STYLE_CLASS);
	}

	public void select(Tab tab)
	{
		selected.set(tab);
	}

	public void select(int index)
	{
		selected.set(getTab(index));
	}

	public void select(String name)
	{
		selected.set(getTab(name));
	}

	public void addTab(Tab tab)
	{
		if (contains(tab))
		{
			select(getIndex(tab));
		}
		else
		{
			getTabs().add(tab);
		}
	}

	public void addTabs(Tab... tabs)
	{
		for (Tab tab : tabs)
		{
			addTab(tab);
		}
	}

	public boolean contains(Tab tab)
	{
		return getIndex(tab) != -1;
	}

	public boolean contains(String title)
	{
		return getIndex(title) != -1;
	}

	public int getIndex(Tab tab)
	{
		return getIndex(tab.getText());
	}

	// 추후 ID 방식으로 바꿔야함
	public int getIndex(String title)
	{
		for (int index = 0 ; index < tabs.size() ; index ++)
		{
			if (tabs.get(index).getText().equals(title))
			{
				return index;
			}
		}

		return -1;
	}

	public void close()
	{
		tabs.clear();
	}

	public void close(Tab tab)
	{
		if (!tab.isClosable())
		{
			return ;
		}

		int index = getIndex(tab), size = tabs.size();

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

	public Tab getTab(String title)
	{
		return contains(title) ? getTab(getIndex(title)) : null;
	}

	public Tab getTab(int index)
	{
		return tabs.get(index);
	}

    public Type getType()
    {
        return type.get();
    }

	public Tab getSelectedTab()
	{
		return selected.get();
	}

	public ObservableList<Tab> getTabs()
	{
		return tabs;
	}

	public ObjectProperty<Type> getTypeProperty()
    {
        return type;
    }

	// Selected Tab Property
	public ObjectProperty<Tab> getSelectedTabProperty()
	{
		return selected;
	}

	public BooleanProperty getVisibleProperty()
	{
		return visibleProperty();
	}

	/* @Override
	public Skin<?> setDefaultSkin()
	{
		return new TabViewSkin(this);
	} */

    public void setType(Type type)
    {
        this.type.set(type);
    }

	public void setSelectedTab(Tab tab)
	{
		selected.set(tab);
	}

	/* public void setTabWidth(double value)
	{
		size.set(value);
	}

	public void setTabHeight(double value)
	{
		height.set(value);
	} */
}