package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.control.Skin;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.enums.SelectType;
import org.beuwi.msgbots.platform.gui.enums.ControlType;
import org.beuwi.msgbots.platform.gui.skins.TabViewSkin;

// @DefaultProperty("tabs")
public class TabView extends Control
{
	private static final String DEFAULT_STYLE_CLASS = "tab-view";

	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
	private static final PseudoClass PINNED_PSEUDO_CLASS = PseudoClass.getPseudoClass("pinned");

	private final ObjectProperty<ControlType> type = new SimpleObjectProperty(null);
	// Selected Tab Property
	private final ObjectProperty<Tab> selected = new SimpleObjectProperty(null);

	private final ObservableList<Tab> tabs = FXCollections.observableArrayList();

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

		setOnKeyPressed(event ->
		{
			if (event.isControlDown())
			{
				switch (event.getCode())
				{
					case TAB : select(SelectType.NEXT); break;
				}

				if (event.isShiftDown())
				{
					switch (event.getCode())
					{
						case TAB : select(SelectType.PREVIOUS); break;
					}
				}
			}
		});

		getTabs().addListener((ListChangeListener<Tab>) change ->
		{
			while (change.next())
			{
				for (Tab tab : change.getRemoved())
				{
					tab.setView(null);
				}

				for (Tab tab : change.getAddedSubList())
				{
					tab.setView(this);

					if (getType() != null && getType().equals(ControlType.SYSTEM))
					{
						HBox.setHgrow(tab, Priority.ALWAYS);
					}

					selected.setValue(tab);
				}

				this.setVisible(!getTabs().isEmpty());
			}
		});

		getSelectedTabProperty().addListener((observable, oldTab, newTab) ->
		{
			if (oldTab != null)
			{
				oldTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}

			newTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
		});

		// addStyleClass(DEFAULT_STYLE_CLASS);
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

	public void select(SelectType type)
	{
		select(getSelectedTab(), type);
	}

	public void select(Tab tab, SelectType type)
	{
		int index = getIndex(tab), size = tabs.size();

		if (size < 1 || index < 0)
		{
			return ;
		}

		switch (type)
		{
			case NEXT :
				// If have a next tab
				if (size > index + 1)
				{
					select(index + 1);
				}
				// Select first tab
				else
				{
					select(0);
				}
				break;
			case PREVIOUS :
				// If have a previous tab
				if (size > index - 1)
				{
					select(index - 1);
				}
				// Select last tab
				else
				{
					select(size - 1);
				}
				break;
		}
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

	public void setType(ControlType type)
	{
		this.type.set(type);
	}

	public void setSelectedTab(Tab tab)
	{
		selected.set(tab);
	}

	public Tab getTab(String title)
	{
		return contains(title) ? getTab(getIndex(title)) : null;
	}

	public Tab getTab(int index)
	{
		return tabs.get(index);
	}

    public ControlType getType()
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

	public ObjectProperty<ControlType> getTypeProperty()
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

	@Override
	public Skin<?> setDefaultSkin()
	{
		return new TabViewSkin(this);
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