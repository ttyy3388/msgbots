package org.beuwi.simulator.platform.ui.editor;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.application.views.actions.CloseAllEditorTabsAction;
import org.beuwi.simulator.platform.application.views.actions.CloseEditorPaneAction;
import org.beuwi.simulator.platform.application.views.actions.CloseEditorTabAction;
import org.beuwi.simulator.platform.application.views.actions.SplitEditorPaneAction;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuItem;

import java.util.ArrayList;
import java.util.List;

public class IEditorPane extends AnchorPane
{
	private IContextMenu imenu = new IContextMenu
	(
		new IMenuItem("Close Editor", e -> CloseEditorPaneAction.update(this))
	);

	private IContextMenu menu = new IContextMenu();

	private TabPane pane = new TabPane();
	private HBox bar = new HBox();

	private Button split = new Button();
	private Button more = new Button();
	
	// Drag Target Tab
	private Tab target = null;

	public IEditorPane()
	{
		AnchorPane.setTopAnchor(pane, .0);
		AnchorPane.setRightAnchor(pane, .0);
		AnchorPane.setBottomAnchor(pane, .0);
		AnchorPane.setLeftAnchor(pane, .0);

		AnchorPane.setTopAnchor(bar, .0);
		AnchorPane.setRightAnchor(bar, .0);

		EditorAreaPart.setSelectedPane(this);

		pane.setOnMousePressed(event ->
		{
			if (event.isPrimaryButtonDown())
			{
				EditorAreaPart.setSelectedPane(this);
			}

			if (event.isSecondaryButtonDown())
			{
				imenu.show(this, event);
			}
			else
			{
				imenu.hide();
			}
		});

		pane.setTabMinHeight(24);
		pane.setTabMaxHeight(30);
		pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		pane.getTabs().addListener((ListChangeListener.Change<? extends Tab> change) ->
		{
			while (change.next())
			{
				List<Tab> tabs = new ArrayList<>(pane.getTabs());

				List<MenuItem> items = new ArrayList<>();

				for (Tab tab : tabs)
				{
					items.add(new IMenuItem(tab.getId(), event -> selectTab(tab)));
				}

				items.add(new SeparatorMenuItem());
				items.add(new IMenuItem("Close", "Ctrl + F4", event -> CloseEditorTabAction.update()));
				items.add(new IMenuItem("Close All", event -> CloseAllEditorTabsAction.update()));

				menu = new IContextMenu(items);
			}
		});

		bar.getChildren().addAll(split, more);
		bar.getStyleClass().add("tab-button-bar");
		split.getStyleClass().add("tab-split-button");
		more.getStyleClass().add("tab-more-button");

		bar.setPrefHeight(30);
		split.setPrefSize(35, 30);
		more.setPrefSize(35, 30);

		split.setOnMousePressed(event ->
		{
			if (event.isPrimaryButtonDown())
			{
				SplitEditorPaneAction.update();
			}
		});

		more.setOnMousePressed(event ->
		{
			if (event.isPrimaryButtonDown())
			{
				menu.show(more, Side.BOTTOM);
			}
		});

		this.getChildren().addAll(pane, bar);
	}

	public StackPane getHeaderArea()
	{
		for (Node node : pane.getChildrenUnmodifiable())
		{
			if (node.getStyleClass().contains("tab-header-area"))
			{
				return (StackPane) node;
			}
		}

		return null;
	}

	public void addTab(IEditorTab tab)
	{
		pane.getTabs().add(tab);
		tab.setEditorPane(this);
		selectTab(tab);
	}

	public ObservableList<Tab> getTabs()
	{
		return pane.getTabs();
	}

	public void closeTab(Tab tab)
	{
		removeTab(tab);
	}

	public void removeTab(Tab tab)
	{
		pane.getTabs().remove(tab);

		if (pane.getTabs().size() == 0)
		{
			CloseEditorPaneAction.update(this);
		}
	}

	public void selectTab(String id)
	{
		selectTab(getTabItem(id));
	}

	public void selectTab(Tab tab)
	{
		pane.getSelectionModel().select(tab);
	}

	public boolean tabExists(String id)
	{
		return getTabIndex(id) != -1;
	}

	public IEditorTab getSelectedTab()
	{
		return (IEditorTab) pane.getSelectionModel().getSelectedItem();
	}

	public int getSelectedIndex()
	{
		return pane.getSelectionModel().getSelectedIndex();
	}

	public Tab getTabItem(String id)
	{
		return getTabIndex(id) != -1 ? pane.getTabs().get(getTabIndex(id)) : null;
	}

	public int getTabIndex(String id)
	{
		for (int index = 0 ; index < pane.getTabs().size() ; index ++)
		{
			if (pane.getTabs().get(index).getId().equals(id))
			{
				return index;
			}
		}

		return -1;
	}
}