package org.beuwi.simulator.platform.ui.components;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;

public class ITab extends Tab
{
	public ITab(Image icon, String id, String title, Node node, int type)
	{
		HBox      tabHeader = new HBox();
		ImageView tabIcon   = new ImageView();
		Label     tabTitle  = new Label();
		Button    tabClose  = new Button();

		tabIcon.setFitWidth(14);
		tabIcon.setFitHeight(14);
		tabIcon.setImage(icon);

		tabClose.setPrefWidth(8);
		tabClose.getStyleClass().add("tab-close-button");
		tabClose.setOnMousePressed(event ->
		{
			if (event.isPrimaryButtonDown() || event.isMiddleButtonDown())
			{

			}
		});

		tabTitle.setText(title);
		tabTitle.getStyleClass().add("tab-title-label");

		tabHeader.setSpacing(5);
		tabHeader.setMinHeight(35);
		tabHeader.setAlignment(Pos.CENTER);
		tabHeader.getStyleClass().add("tab-header");
		tabHeader.getChildren().addAll(tabIcon, tabTitle, tabClose);
		tabHeader.setOnMousePressed(event ->
		{
			if (event.isMiddleButtonDown())
			{

			}
		});

		this.setId(id);
		this.setContent(node);
		this.setGraphic(tabHeader);
	}
}