package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.DefaultProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

@DefaultProperty("tabs")
public class TabPane extends VBox<Pane>
{
	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	private static final String DEFAULT_STYLE_CLASS = "tab-pane";

	private static final int DEFAULT_HEADER_WIDTH = 100;
	private static final int DEFAULT_HEADER_HEIGHT = 30;

	// Selected Tab Property
	private final ObjectProperty<Tab> selected = new SimpleObjectProperty<>(null);
    private final ObjectProperty<Type> type = new SimpleObjectProperty<>(null);
	private final ObservableList<Tab> tabs = FXCollections.observableArrayList();

	private final StackPane content = new StackPane();
	private final ScrollPane scroll = new ScrollPane();
	private final HBox header = new HBox();

    public enum Type
    {
        NORMAL, SYSTEM
    }

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
		scroll.setContent(header);
		scroll.setFitToWidth(false);
		scroll.setMinHeight(DEFAULT_HEADER_HEIGHT);
		scroll.setMaxHeight(DEFAULT_HEADER_HEIGHT);
		scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollBarPolicy.NEVER);

		if (tabs != null)
		{
			addTab(tabs);
		}

		getTabs().addListener((ListChangeListener<Tab>) change ->
		{
			while (change.next())
			{
				for (Tab tab : change.getRemoved())
				{
					tab.setTabPane(null);
					header.remove(tab);
				}

				for (Tab tab : change.getAddedSubList())
				{
					tab.setTabPane(this);
					header.addItem(tab);
					selected.setValue(tab);
				}
			}

			this.setVisible(!getTabs().isEmpty());
		});

		getTypeProperty().addListener(change ->
        {
            switch (getType())
            {
                case NORMAL :

                    scroll.setFitToWidth(false);

                    for (Tab tab : getTabs())
                    {
                        HBox.setHgrow(tab, Priority.NEVER);
                    }

                    break;

                case SYSTEM :

                    scroll.setFitToWidth(true);

                    for (Tab tab : getTabs())
                    {
                        HBox.setHgrow(tab, Priority.ALWAYS);
                    }

                    break;
            }
        });

        getSelectedProperty().addListener(change ->
        {
            content.getChildren().clear();
            content.getChildren().add(getSelectedTab().getContent());
        });

		getSelectedProperty().addListener((observable, oldTab, newTab) ->
		{
			/* if (newTab.getBoundsInParent().getMaxX() > getWidth())
			{
				scroll.setHvalue(newTab.getBoundsInParent().getMaxX() / header.getBoundsInLocal().getWidth());
			} */

			if (oldTab != null)
			{
				oldTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}

			newTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
		});

		scroll.getStyleClass().add("tab-header-area");
		content.getStyleClass().add("tab-content-area");

		setFitContent(true);
		// setMinWidth(DEFAULT_MIN_WIDTH);
		// setMinHeight(DEFAULT_MIN_HEIGHT);
		getChildren().add(scroll);
		getChildren().add(content);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void select(int index)
	{
		selected.set(getTab(index));
	}

	public void select(Tab tab)
	{
		selected.set(tab);
	}

	public void addTab(Tab... tabs)
	{
		for (Tab tab : tabs)
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
		return getIndex(tab.getTitle());
	}

	// 추후 ID 방식으로 바꿔야함
	public int getIndex(String title)
	{
		for (int index = 0 ; index < tabs.size() ; index ++)
		{
			if (tabs.get(index).getTitle().equals(title))
			{
				return index;
			}
		}

		return -1;
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

	public ObservableList<Tab> getTabs()
	{
		return tabs;
	}

	public ScrollPane getHeaderArea()
    {
        return scroll;
    }

    public StackPane getContentArea()
    {
        return content;
    }

	public ObjectProperty<Type> getTypeProperty()
    {
        return type;
    }

	// Selected Tab Property
	public ObjectProperty<Tab> getSelectedProperty()
	{
		return selected;
	}

	public Tab getSelectedTab()
	{
		return selected.get();
	}

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