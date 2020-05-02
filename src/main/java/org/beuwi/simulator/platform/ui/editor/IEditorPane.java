package org.beuwi.simulator.platform.ui.editor;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.views.actions.CloseEditorPaneAction;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuButton;
import org.beuwi.simulator.platform.ui.components.IMenuItem;

public class IEditorPane extends AnchorPane
{
	private IContextMenu imenu = new IContextMenu
	(
		new IMenuItem("Close Editor", e -> CloseEditorPaneAction.update(this))
	);

	private IContextMenu menu = new IContextMenu();

	private IEditorTabPane pane = new IEditorTabPane();
	private HBox bar = new HBox();

	private IMenuButton split = new IMenuButton();
	private IMenuButton more = new IMenuButton();
	
	// Drag Target Tab
	// private Tab target = null;

	public IEditorPane()
	{
		AnchorPane.setTopAnchor(pane, .0);
		AnchorPane.setRightAnchor(pane, .0);
		AnchorPane.setBottomAnchor(pane, .0);
		AnchorPane.setLeftAnchor(pane, .0);

		AnchorPane.setTopAnchor(bar, .0);
		AnchorPane.setRightAnchor(bar, .0);

		/* this.setFocusTraversable(true);
		this.focusedProperty().addListener((observable, oldValue, newValue) ->
		{
			System.out.println(newValue);
		}); */

		/* EditorAreaPart.setSelectedPane(this);

		EditorAreaPart.getProperty().addListener((observable, oldValue, newValue) ->
		{
			bar.setVisible(newValue.equals(this));
		});

		pane.setOnMouseClicked(event ->
		{
			EditorAreaPart.setSelectedPane(this);
		});

		pane.setOnMousePressed(event ->
		{
			if (imenu.isShowing())
			{
				imenu.hide();
			}
		});

		pane.getTabs().addListener((ListChangeListener.Change<? extends Tab> change) ->
		{
			while (change.next())
			{
				bar.setVisible(pane.getTabs().size() != 0);

				List<Tab> tabs = new ArrayList<>(pane.getTabs());

				List<MenuItem> items = new ArrayList<>();

				for (Tab tab : tabs)
				{
					items.add(new IMenuItem(tab.getId(), event -> selectTab(tab)));
				}

				items.add(new SeparatorMenuItem());
				items.add(new IMenuItem("Close", "Ctrl + F4", event -> CloseTabAction.update()));
				items.add(new IMenuItem("Close All", event -> CloseAllTabsAction.update()));

				menu.getItems().setAll(items);
			}
		});

		bar.setVisible(false);
		bar.getChildren().addAll(split, more);
		bar.getStyleClass().add("tab-button-bar");
		split.getStyleClass().add("tab-split-button");
		more.getStyleClass().add("tab-more-button");

		split.setGraphic(ISVGGlyph.getGlyph("Editor.Split"));
		more.setGraphic(ISVGGlyph.getGlyph("Editor.More"));

		bar.setPrefHeight(30);
		split.setPrefSize(35, 30);
		more.setPrefSize(35, 30);

		more.setMenu(menu);

		// more.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

		split.setOnMouseClicked(event ->
		{
			if (MouseButton.PRIMARY.equals(event.getButton()))
			{
				SplitEditorPaneAction.update();
			}
		}); */

		this.getChildren().addAll(pane, bar);
	}
}