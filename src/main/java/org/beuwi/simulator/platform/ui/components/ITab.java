package org.beuwi.simulator.platform.ui.components;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;

public class ITab extends Tab
{
	public ITab(Image icon, String title, Node node, int type)
	{
		HBox      tabHeader = new HBox();
		ImageView tabIcon   = new ImageView();
		Label     tabTitle  = new Label();
		Button    tabClose  = new Button();

		tabIcon.setFitWidth(15);
		tabIcon.setFitHeight(15);
		tabIcon.setImage(icon);

		tabClose.setPrefWidth(8);
		tabClose.getStyleClass().add("tab-close-button");

		tabTitle.setText(title);
		tabTitle.getStyleClass().add("tab-title-label");

		tabHeader.setSpacing(5);
		tabHeader.getStyleClass().add("tab-header");
		tabHeader.getChildren().addAll(tabIcon, tabTitle, tabClose);

		switch (type)
		{
			// @tab::debug
			case ITabType.DEBUG  :
				// @tab::log
			case ITabType.LOG    : this.setId("@tab::" + title);    break;
			// @tab::name (@tab::test)
			case ITabType.SCRIPT : this.setId("@script::" + title); break;
		}

		this.setContent(node);
		this.setGraphic(tabHeader);
	}
}