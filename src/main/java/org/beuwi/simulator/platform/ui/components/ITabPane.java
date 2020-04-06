package org.beuwi.simulator.platform.ui.components;

import javafx.collections.ListChangeListener.Change;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.util.concurrent.atomic.AtomicLong;

public class ITabPane extends TabPane
{
	private Tab currentDraggingTab ;

	private static final AtomicLong idGenerator = new AtomicLong();

	private final String draggingID = "DraggingTabPaneSupport-" + idGenerator.incrementAndGet();

	/*
	Button button = new Button();

		button.getStyleClass().add("tab-more-button");

		AnchorPane pane = new AnchorPane(button);

		pane.setPrefSize(32, 32);

		pane.getStyleClass().add("tab-more-area");

		AnchorPane.setTopAnchor(button, .0);
		AnchorPane.setRightAnchor(button, .0);
		AnchorPane.setBottomAnchor(button, .0);
		AnchorPane.setLeftAnchor(button, .0);

		AnchorPane.setTopAnchor(pane, .0);
		AnchorPane.setRightAnchor(pane, .0);

		AnchorPane.setTopAnchor(TAB_PANE, .0);
		AnchorPane.setRightAnchor(TAB_PANE, .0);
		AnchorPane.setBottomAnchor(TAB_PANE, .0);
		AnchorPane.setLeftAnchor(TAB_PANE, .0);
	 */

	public ITabPane()
	{
		// super();

		setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		getTabs().forEach(this::addDragHandlers);
		getTabs().addListener((Change<? extends Tab> change) ->
		{
			while (change.next())
			{
				if (change.wasAdded())
				{
					change.getAddedSubList().forEach(this::addDragHandlers);
				}
				if (change.wasRemoved())
				{
					change.getRemoved().forEach(this::removeDragHandlers);
				}
			}
		});

		setOnDragOver(event ->
		{
			if (draggingID.equals(event.getDragboard().getString()) && currentDraggingTab != null && currentDraggingTab.getTabPane() != this)
			{
				event.acceptTransferModes(TransferMode.MOVE);
			}
		});

		setOnDragDropped(event ->
		{
			if (draggingID.equals(event.getDragboard().getString()) && currentDraggingTab != null && currentDraggingTab.getTabPane() != this)
			{
				currentDraggingTab.getTabPane().getTabs().remove(currentDraggingTab);
				getTabs().add(currentDraggingTab);
				currentDraggingTab.getTabPane().getSelectionModel().select(currentDraggingTab);
			}
		});
	}

	private void addDragHandlers(Tab tab)
	{
		if (tab.getText() != null && ! tab.getText().isEmpty())
		{
			Label label = new Label(tab.getText(), tab.getGraphic());
			tab.setText(null);
			tab.setGraphic(label);
		}

		Node graphic = tab.getGraphic();

		graphic.setOnDragDetected(event ->
		{
			Dragboard dragboard = graphic.startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			content.putString(draggingID);
			dragboard.setContent(content);
			dragboard.setDragView(graphic.snapshot(null, null));
			currentDraggingTab = tab ;
		});
		
		graphic.setOnDragOver(event -> 
		{
			if (draggingID.equals(event.getDragboard().getString()) && currentDraggingTab != null && currentDraggingTab.getGraphic() != graphic) 
			{
				event.acceptTransferModes(TransferMode.MOVE);
			}
		});
		
		graphic.setOnDragDropped(event ->
		{
			if (draggingID.equals(event.getDragboard().getString()) && currentDraggingTab != null && currentDraggingTab.getGraphic() != graphic)
			{
				int index = tab.getTabPane().getTabs().indexOf(tab) ;
				currentDraggingTab.getTabPane().getTabs().remove(currentDraggingTab);
				tab.getTabPane().getTabs().add(index, currentDraggingTab);
				currentDraggingTab.getTabPane().getSelectionModel().select(currentDraggingTab);
			}
		});
		
		graphic.setOnDragDone(event -> currentDraggingTab = null);
	}

	private void removeDragHandlers(Tab tab) 
	{
		tab.getGraphic().setOnDragDetected(null);
		tab.getGraphic().setOnDragOver(null);
		tab.getGraphic().setOnDragDropped(null);
		tab.getGraphic().setOnDragDone(null);
	}

	public void addTab(Tab tab)
	{
		getTabs().add(tab);
		selectTab(tab);
	}

	public void closeTab(Tab tab)
	{
		getTabs().remove(tab);
	}

	public void selectTab(String id)
	{
		selectTab(getTabItem(id));
	}

	public void selectTab(Tab tab)
	{
		getSelectionModel().select(tab);
	}

	public boolean tabExists(String id)
	{
		return getTabIndex(id) != -1;
	}

	public Tab getTabItem(String id)
	{
		return getTabIndex(id) != -1 ? getTabs().get(getTabIndex(id)) : null;
	}

	public int getTabIndex(String id)
	{
		for (int index = 0 ; index < getTabs().size() ; index ++)
		{
			if (getTabs().get(index).getId().equals(id))
			{
				return index;
			}
		}

		return -1;
	}
}