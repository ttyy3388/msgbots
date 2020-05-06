package org.beuwi.simulator.platform.ui.editor;

import javafx.collections.ListChangeListener;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.actions.CloseAllTabsAction;
import org.beuwi.simulator.platform.application.views.actions.CloseEditorTabAction;
import org.beuwi.simulator.platform.application.views.actions.SelectEditorTabAction;
import org.beuwi.simulator.platform.application.views.actions.SplitEditorPaneAction;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuButton;
import org.beuwi.simulator.platform.ui.components.IMenuItem;
import org.beuwi.simulator.platform.ui.components.IPos;
import org.beuwi.simulator.platform.ui.components.ISVGGlyph;

import java.util.ArrayList;
import java.util.List;

public class IEditorPane extends AnchorPane
{
	private IContextMenu menu = new IContextMenu();

	private IEditorTabPane pane = new IEditorTabPane();

	// Editor Button Bar
	private HBox bar = new HBox();

	// Editor Split Button
	private IMenuButton split = new IMenuButton();

	// Editor More Tab Button
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

		EditorAreaPart.setSelectedPane(this);

		this.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
		{
			EditorAreaPart.setSelectedPane(this);
		});

		EditorAreaPart.getProperty().addListener((observable, oldValue, newValue) ->
		{
			bar.setVisible(newValue.equals(this));
		});

		pane.setOnKeyPressed(event ->
		{
			if (event.isControlDown())
			{
				switch (event.getCode())
				{
					case W  :
					case F4 : CloseEditorTabAction.update(pane); break;
				}
			}

			if (event.isAltDown())
			{
				switch (event.getCode())
				{
					case LEFT : SelectEditorTabAction.update(pane, IPos.LEFT); break;
					case RIGHT : SelectEditorTabAction.update(pane, IPos.RIGHT); break;
				}
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
					items.add(new IMenuItem(tab.getId(), event -> pane.selectTab(tab)));
				}

				items.add(new SeparatorMenuItem());
				items.add(new IMenuItem("Close", "Ctrl + F4", event -> CloseEditorTabAction.update(pane)));
				items.add(new IMenuItem("Close All", event -> CloseAllTabsAction.update(pane)));

				menu.getItems().setAll(items);

				// 탭이 모두 닫히면 에디터 닫음
				if (pane.getTabs().isEmpty())
				{
					EditorAreaPart.getComponent().getItems().remove(this);
				}
			}
		});

		bar.setVisible(false);
		bar.getChildren().addAll(split, more);
		bar.getStyleClass().add("tab-button-bar");
		bar.setPrefHeight(30);

		split.getStyleClass().add("tab-split-button");
		split.setGraphic(ISVGGlyph.getGlyph("Editor.Split"));
		split.setPrefSize(35, 30);

		more.getStyleClass().add("tab-more-button");
		more.setGraphic(ISVGGlyph.getGlyph("Editor.More"));
		more.setPrefSize(35, 30);
		more.setMenu(menu);

		split.setOnMouseClicked(event ->
		{
			if (MouseButton.PRIMARY.equals(event.getButton()))
			{
				SplitEditorPaneAction.update();
			}
		});

		this.getChildren().addAll(pane, bar);
	}

	public IEditorTabPane getTabPane()
	{
		return this.pane;
	}

	public List<Tab> getTabs()
	{
		return getTabPane().getTabs();
	}
}