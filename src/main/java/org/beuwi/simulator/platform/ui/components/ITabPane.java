package org.beuwi.simulator.platform.ui.components;

import javafx.collections.ListChangeListener.Change;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;

import java.util.concurrent.atomic.AtomicLong;

public class ITabPane extends AnchorPane
{
	private Tab currentDraggingTab ;

	private static final AtomicLong idGenerator = new AtomicLong();

	private final String draggingID = "DraggingTabPaneSupport-" + idGenerator.incrementAndGet();

	private final TabPane TAB_PANE = new TabPane();

	public ITabPane()
	{
		// super();

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

		this.getChildren().addAll(TAB_PANE, pane);

		TAB_PANE.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		TAB_PANE.getTabs().forEach(this::addDragHandlers);
		TAB_PANE.getTabs().addListener((Change<? extends Tab> change) ->
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
			if (draggingID.equals(event.getDragboard().getString()) && currentDraggingTab != null && currentDraggingTab.getTabPane() != TAB_PANE)
			{
				event.acceptTransferModes(TransferMode.MOVE);
			}
		});

		setOnDragDropped(event ->
		{
			if (draggingID.equals(event.getDragboard().getString()) && currentDraggingTab != null && currentDraggingTab.getTabPane() != TAB_PANE)
			{
				currentDraggingTab.getTabPane().getTabs().remove(currentDraggingTab);
				TAB_PANE.getTabs().add(currentDraggingTab);
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
		TAB_PANE.getTabs().add(tab);
		selectTab(tab);
	}

	public void closeTab(Tab tab)
	{
		TAB_PANE.getTabs().remove(tab);
	}

	public void selectTab(String id)
	{
		selectTab(getTabItem(id));
	}

	public void selectTab(Tab tab)
	{
		TAB_PANE.getSelectionModel().select(tab);
	}

	public boolean tabExists(String id)
	{
		return getTabIndex(id) != -1;
	}

	public Tab getTabItem(String id)
	{
		return getTabIndex(id) != -1 ? TAB_PANE.getTabs().get(getTabIndex(id)) : null;
	}

	public int getTabIndex(String id)
	{
		for (int index = 0 ; index < TAB_PANE.getTabs().size() ; index ++)
		{
			if (TAB_PANE.getTabs().get(index).getId().equals(id))
			{
				return index;
			}
		}

		return -1;
	}
}