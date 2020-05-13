package org.beuwi.simulator.platform.ui.skins;

import javafx.scene.control.skin.TabPaneSkin;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class ITabPaneSkin extends TabPaneSkin
{
	final ITabPane pane;
	final StackPane area;
	final StackPane back;
	final StackPane bar;

	public ITabPaneSkin(ITabPane pane)
	{
		super(pane);

		this.pane = pane;

		area = (StackPane) getChildren().get(0);
		back = (StackPane) area.getChildren().get(0);
		bar  = (StackPane) area.getChildren().get(2);
	}

	public StackPane getHeaderArea()
	{
		return area;
	}

	public StackPane getHeaderBackGround()
	{
		return back;
	}

	public StackPane getButtonBar()
	{
		return bar;
	}

	public void setButtonBar(HBox hbox)
	{
		bar.getChildren().clear();

		bar.setPrefWidth(hbox.getPrefWidth());
		bar.setPrefHeight(pane.getTabMinHeight());

		hbox.setPrefHeight(pane.getTabMinHeight());

		bar.visibleProperty().addListener((observable, oldValue, newValue) ->
		{
			bar.setVisible(true);
		});

		bar.setVisible(true);
		bar.getChildren().add(hbox);
	}
}