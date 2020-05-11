package org.beuwi.simulator.platform.ui.editor;

import javafx.collections.ListChangeListener;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuButton;
import org.beuwi.simulator.platform.ui.components.IMenuItem;
import org.beuwi.simulator.platform.ui.components.ISVGGlyph;
import org.beuwi.simulator.platform.ui.components.ITabPane;

import java.util.ArrayList;
import java.util.List;

public class IEditorPane extends ITabPane
{
	// More Button Menu
	private final IContextMenu menu = new IContextMenu();

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

		setOnKeyPressed(event ->
		{
			if (event.isControlDown())
			{
				switch (event.getCode())
				{
					case W  :
					case F4 : break;
				}
			}

			if (event.isAltDown())
			{
				switch (event.getCode())
				{
					case LEFT :
					case RIGHT : break;
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

				menu.getItems().setAll(items);
			}
		});

		hbox.setPrefWidth(70);

		split.getStyleClass().add("tab-split-button");
		split.setGraphic(ISVGGlyph.getGlyph("Editor.Split"));
		split.setMinSize(35, 30);

		more.getStyleClass().add("tab-more-button");
		more.setGraphic(ISVGGlyph.getGlyph("Editor.More"));
		more.setMinSize(35, 30);
		more.setMenu(menu);

		setButtonBar(hbox);
	}
}