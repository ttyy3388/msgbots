package org.beuwi.simulator.platform.ui.components;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ITab extends Tab
{
	HBox      tabHeader = new HBox();

	public ITab(Image icon, String title, Node node/*, ITabType type */)
	{
		ImageView tabIcon   = new ImageView();
		Label     tabTitle  = new Label();
		Button    tabClose  = new Button();
		ImageView imvClose  = new ImageView();

		tabIcon.setFitWidth(15);
		tabIcon.setFitHeight(15);

		imvClose.setFitWidth(14);
		imvClose.setFitHeight(14);

		tabIcon.setImage(icon);

		tabClose.setPrefWidth(8);

		tabTitle.setText(title);
		tabTitle.getStyleClass().add("tab-label");

        tabHeader.setSpacing(5);
        tabHeader.getChildren().addAll(tabIcon, tabTitle, tabClose);

        this.setId(title);
        this.setContent(node);
        this.setGraphic(tabHeader);
	}
	
	public void setOnMousePressed(EventHandler<MouseEvent> handler)
	{
		tabHeader.setOnMousePressed(handler);
	}
}