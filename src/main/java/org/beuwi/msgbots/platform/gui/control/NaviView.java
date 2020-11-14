package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

// Navigation View
public class NaviView extends HBox
{
    private static final String DEFAULT_STYLE_CLASS = "navi-view";

    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

    private static final String HEADER_STYLE_CLASS = "navi-header-area";
    private static final String CONTENT_STYLE_CLASS = "navi-content-area";

    // Selected Tab Property
    private final ObjectProperty<Navi> selected = new SimpleObjectProperty<>(null);
    private final ObservableList<Navi> navis = FXCollections.observableArrayList();

    private static final int DEFAULT_HEADER_WIDTH = 150;
    // private static final int DEFAULT_HEADER_HEIGHT = 30;

    /* Navi View [ Header Area [ Headers [ Navi Header ( Main ) ] ] , Content Area [ Navi Content ] ] */

    // Tab Content Area
    private final StackPanel content = new StackPanel();

    // Navi Header List
    private final VBox<Navi> header = new VBox();

    {
        HBox.setHgrow(content, Priority.ALWAYS);
    }

    public NaviView()
    {
        this(null);
    }

    public NaviView(Navi... navis)
    {
        if (navis != null)
        {
            addNavis(navis);
        }

        header.setMinWidth(DEFAULT_HEADER_WIDTH);
        header.setPrefWidth(DEFAULT_HEADER_WIDTH);
        // header.setMaxWidth(DEFAULT_HEADER_WIDTH);

        header.addStyleClass(HEADER_STYLE_CLASS);
        content.addStyleClass(CONTENT_STYLE_CLASS);

        getNavis().addListener((ListChangeListener<Navi>) change ->
        {
            while (change.next())
            {
                for (Navi navi : change.getRemoved())
                {
                    navi.setView(null);
                    header.remove(navi);
                }

                for (Navi navi : change.getAddedSubList())
                {
                    navi.setView(this);
                    header.addItem(navi);
                    selected.setValue(navi);
                }
            }
        });

        getSelectedNaviProperty().addListener(change ->
        {
            content.setItem(getSelectedNavi().getContent());
        });

        getSelectedNaviProperty().addListener((observable, oldTab, newTab) ->
        {
            if (oldTab != null)
            {
                oldTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
            }

            newTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
        });

        setItems(header, content);
        setFittable(true);
        addStyleClass(DEFAULT_STYLE_CLASS);
    }

    public void select(int index)
    {
        selected.set(getNavi(index));
    }

    public void select(Navi navi)
    {
        selected.set(navi);
    }

    public void addNavi(Navi navi)
    {
        if (contains(navi))
        {
            select(getIndex(navi));
        }
        else
        {
            getNavis().add(navi);
        }
    }

    public void addNavis(Navi... navis)
    {
        for (Navi navi : navis)
        {
            addNavi(navi);
        }
    }

    public boolean contains(Navi navi)
    {
        return getIndex(navi) != -1;
    }

    public boolean contains(String title)
    {
        return getIndex(title) != -1;
    }

    public int getIndex(Navi navi)
    {
        return getIndex(navi.getText());
    }

    // 추후 ID 방식으로 바꿔야함
    public int getIndex(String title)
    {
        for (int index = 0 ; index < getNavis().size() ; index ++)
        {
            if (getNavis().get(index).getText().equals(title))
            {
                return index;
            }
        }

        return -1;
    }

    public Navi getNavi(String title)
    {
        return contains(title) ? getNavi(getIndex(title)) : null;
    }

    public Navi getNavi(int index)
    {
        return getNavis().get(index);
    }

    public Navi getSelectedNavi()
    {
        return selected.get();
    }

    public ObservableList<Navi> getNavis()
    {
        return navis;
    }

    // Selected Tab Property
    public ObjectProperty<Navi> getSelectedNaviProperty()
    {
        return selected;
    }

	/* @Override
	public Skin<?> setDefaultSkin()
	{
		return new TabViewSkin(this);
	} */

    public void setSelectedNavi(Navi navi)
    {
        selected.set(navi);
    }

	/* public void setNaviWidth(double value)
	{
		size.set(value);
	}

	public void setNaviHeight(double value)
	{
		height.set(value);
	} */
}