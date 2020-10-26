package org.beuwi.msgbots.platform.gui.skins;

public class TabPaneSkin // extends SkinBase<TabPane>
{
	/* private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	private static final double DEFAULT_SCROLL_SPEED = 0.005;

	private static final int DEFAULT_HEADER_WIDTH = 100;
	private static final int DEFAULT_HEADER_HEIGHT = 30;

	private final StackPane content = new StackPane();
	private final ScrollPane scroll = new ScrollPane();
	private final HBox header = new HBox();
	private final VBox root = new VBox();

	private final TabPane pane;

	{
		VBox.setVgrow(content, Priority.ALWAYS);
		HBox.setHgrow(content, Priority.ALWAYS);
	}

	public TabPaneSkin(TabPane pane)
	{
		super(pane);

		this.pane = pane;

		for (Tab tab : pane.getTabs())
		{
			if (tab.isSelected())
			{
				pane.select(tab);
			}

			header.addItem(tab);
		}

		scroll.setHvalue(1.0d);
		scroll.setContent(header);
		scroll.setMinHeight(DEFAULT_HEADER_HEIGHT);
		scroll.setMaxHeight(DEFAULT_HEADER_HEIGHT);
		scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
		scroll.setFitToWidth(true);
		scroll.setFitToHeight(true);

		scroll.setOnScroll(event ->
		{
			scroll.setHvalue(scroll.getHvalue() - (event.getDeltaY() * DEFAULT_SCROLL_SPEED));
		});

		root.addItem(scroll, content);

		if (pane.getSelectedTab() != null)
		{
			pane.getSelectedTab().pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
			content.getChildren().add(pane.getSelectedTab().getContent());
		}

		pane.getTabs().addListener((ListChangeListener<Tab>) change ->
		{
			// header.setVisible(pane.getTabs().isEmpty());
			// content.setVisible(pane.getTabs().isEmpty());

			while (change.next())
			{
				for (Tab tab : change.getRemoved())
				{
					header.remove(tab.getHeader());
				}

				for (Tab tab : change.getAddedSubList())
				{
					header.addItem(tab.getHeader());
				}
			}
		});

		pane.getSelectedProperty().addListener((observable, oldTab, newTab) ->
		{
            if (oldTab != null)
            {
                oldTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
            }

            newTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
		});

		pane.getSelectedProperty().addListener(change ->
		{
			change(pane.getSelectedTab());
		});

		scroll.getStyleClass().add("tab-header-area");
		content.getStyleClass().add("tab-content-area");

		getChildren().add(root);
	}

	// Change Content
	public void change(Tab tab)
	{
		content.getChildren().clear();
		content.getChildren().add(tab.getContent());
	}

	public ScrollPane getHeaderArea()
	{
		return scroll;
	}

	public StackPane getContentArea()
	{
		return content;
	} */
}