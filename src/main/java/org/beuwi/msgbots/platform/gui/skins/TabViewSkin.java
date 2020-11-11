package org.beuwi.msgbots.platform.gui.skins;

import javafx.collections.ListChangeListener;
import javafx.css.PseudoClass;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.control.HBox;
import org.beuwi.msgbots.platform.gui.control.SkinBase;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.control.VBox;
import org.beuwi.msgbots.platform.gui.layout.ScrollPanel;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

public class TabViewSkin // extends SkinBase<TabView>
{
   /*  private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
    private static final PseudoClass PINNED_PSEUDO_CLASS = PseudoClass.getPseudoClass("pinned");

	private static final String HEADER_STYLE_CLASS = "tab-header-area";
	private static final String CONTENT_STYLE_CLASS = "tab-content-area";

	// private static final int DEFAULT_HEADER_WIDTH = 100;
	private static final int DEFAULT_HEADER_HEIGHT = 30;

    /* Tab View [ Header Area [ Headers [ Tab Header ( Main ) ] ] , Content Area [ Tab Content ] ] */

	/* // Tab Content Area
	private final StackPanel content = new StackPanel();

	// Tab Header Area [ Tab Header List ]
	private final ScrollPanel header = new ScrollPanel();

	// Tab Header List
	private final HBox<Tab> tablist = new HBox();
	private final VBox root = new VBox();

	private final TabView control;

	{
		VBox.setVgrow(content, Priority.ALWAYS);
	}

	public TabViewSkin(TabView control)
	{
		super(control);

		this.control = control;

		header.setHvalue(1.0d);
		header.setContent(tablist);
		header.setFitToWidth(false);
		header.setFitToHeight(true);
		header.setMinHeight(DEFAULT_HEADER_HEIGHT);
		header.setMaxHeight(DEFAULT_HEADER_HEIGHT);
		header.setHbarPolicy(ScrollBarPolicy.NEVER);
		header.setVbarPolicy(ScrollBarPolicy.NEVER);

		// header.setMinHeight(DEFAULT_HEADER_HEIGHT);
		header.setPrefHeight(DEFAULT_HEADER_HEIGHT);
		// header.setMaxHeight(DEFAULT_HEADER_HEIGHT);

		header.getStyleClass().add(HEADER_STYLE_CLASS);
		content.getStyleClass().add(CONTENT_STYLE_CLASS);

		tablist.addItems(control.getTabs());
		control.select(control.getSelectedTab());
		control.setType(control.getType());

		control.getTabs().addListener((ListChangeListener<Tab>) change ->
		{
			while (change.next())
			{
				for (Tab tab : change.getRemoved())
				{
					tablist.remove(tab);
				}

				for (Tab tab : change.getAddedSubList())
				{
					tablist.addItem(tab);
					control.select(tab);
				}
			}

			// control.setVisible(!control.getTabs().isEmpty());
		});

		control.getTypeProperty().addListener(change ->
        {
            switch (control.getType())
            {
                case NORMAL :

                    header.setFitToWidth(false);

                    for (Tab tab : control.getTabs())
                    {
                        HBox.setHgrow(tab, Priority.NEVER);
                    }

                    break;

                case SYSTEM :

                    header.setFitToWidth(true);

                    for (Tab tab : control.getTabs())
                    {
                        HBox.setHgrow(tab, Priority.ALWAYS);
                    }

                    break;
            }
        });

		control.getSelectedTabProperty().addListener(change ->
		{
			content.setItem(control.getSelectedTab().getContent());
		});

		control.getSelectedTabProperty().addListener((observable, oldTab, newTab) ->
        {
            if (oldTab != null)
            {
                oldTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
            }

            newTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
        });

        root.setItems(header, content);
        root.setFittable(true);

		// setMinWidth(DEFAULT_MIN_WIDTH);
		// setMinHeight(DEFAULT_MIN_HEIGHT)
		getChildren().setAll(root);
	}

	public ScrollPanel getHeaderArea()
	{
		return header;
	}

	public StackPanel getContentArea()
	{
		return content;
	} */
}