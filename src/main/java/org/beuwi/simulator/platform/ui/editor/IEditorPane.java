package org.beuwi.simulator.platform.ui.editor;

import javafx.collections.ListChangeListener;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.views.actions.CloseTabAction;
import org.beuwi.simulator.platform.application.views.actions.DeleteEditorPaneAction;
import org.beuwi.simulator.platform.application.views.actions.SelectEditorTabAction;
import org.beuwi.simulator.platform.application.views.actions.SplitEditorPaneAction;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuButton;
import org.beuwi.simulator.platform.ui.components.IMenuItem;
import org.beuwi.simulator.platform.ui.components.ISVGGlyph;
import org.beuwi.simulator.platform.ui.components.ITabPane;

import java.util.ArrayList;
import java.util.List;

public class IEditorPane extends ITabPane
{
	// Editor Menu
	private final IContextMenu menu = new IContextMenu
	(
		new IMenuItem("Close Editor", event -> DeleteEditorPaneAction.update(this))
	);

	// Split Button
	private final IMenuButton split = new IMenuButton();

	// More Button
	private final IMenuButton more = new IMenuButton();

	// Button Bar
	private final HBox hbox = new HBox(split, more);

	public IEditorPane()
	{
		this(null);
	}

	public IEditorPane(IEditorTab... tabs)
	{
		setTabMinHeight(30);
		setTabMaxHeight(30);

		addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
		{
			EditorAreaPart.setSelectedPane(this);

			if (MouseButton.SECONDARY.equals(event.getButton()))
			{
				if (getTabs().isEmpty())
				{
					menu.show(this, event);
				}
			}
		});

		addEventFilter(MouseEvent.MOUSE_PRESSED, event ->
		{
			if (menu.isShowing())
			{
				menu.hide();
			}
		});

		setOnKeyPressed(event ->
		{
			if (event.isControlDown())
			{
				switch (event.getCode())
				{
					case W  :
					case F4 : CloseTabAction.update(this); break;
				}
			}

			if (event.isAltDown())
			{
				switch (event.getCode())
				{
					case LEFT :	SelectEditorTabAction.update(this, Side.LEFT); break;
					case RIGHT : SelectEditorTabAction.update(this, Side.RIGHT); break;
				}
			}
		});

		if (tabs != null)
		{
			getTabs().addAll(tabs);
		}

		getTabs().addListener((ListChangeListener.Change<? extends Tab> change) ->
		{
			while (change.next())
			{
				List<Tab> list = new ArrayList<>(getTabs());

				List<MenuItem> items = new ArrayList<>();

				for (Tab tab : list)
				{
					items.add(new IMenuItem(tab.getId(), event -> selectTab(tab)));
				}

				items.add(new SeparatorMenuItem());
				items.add(new IMenuItem("Close", "Ctrl + F4"));
				items.add(new IMenuItem("Close All"));

				more.setMenus(items);

				List<Node> panes = EditorAreaPart.getComponent().getItems();

				/* if (list.isEmpty())
				{
					DeleteEditorPaneAction.update(this);
				} */
			}
		});

		hbox.setPrefWidth(70);

		split.getStyleClass().add("tab-split-button");
		split.setGraphic(ISVGGlyph.getGlyph("Editor.Split"));
		split.setMinSize(35, 30);
		split.setOnAction(event ->
		{
			SplitEditorPaneAction.update();
		});

		more.getStyleClass().add("tab-more-button");
		more.setGraphic(ISVGGlyph.getGlyph("Editor.More"));
		more.setMinSize(35, 30);

		setButtonBar(hbox);

		EditorAreaPart.setSelectedPane(this);
	}
}