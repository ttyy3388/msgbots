package org.beuwi.simulator.platform.ui.components;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.utils.ResourceUtils;

public class ITab extends Tab
{
	HBox 	  header = new HBox();
	ImageView image  = new ImageView();
	Label 	  label  = new Label();
	Button 	  button = new Button();

	public ITab()
	{
		this(null);
	}

	public ITab(String title)
	{
		this(title, null);
	}

	public ITab(String title, Node node)
	{
		this(title, title, node);
	}

	public ITab(String id, String title, Node node)
	{
		this(ResourceUtils.getImage("tab_default"), id, title, node);
	}

	public ITab(Image icon, String id, String title, Node node)
	{
		image.setImage(icon);
		image.setFitWidth(14);
		image.setFitHeight(14);

		button.setGraphic(ISVGGlyph.getGlyph("Tab.Close"));
		button.setPrefWidth(14);
		button.setPrefHeight(14);
		button.getStyleClass().add("tab-close-button");

		label.setText(title);
		label.getStyleClass().add("tab-header-label");

		header.setSpacing(7);
		header.setAlignment(Pos.CENTER);
		header.getStyleClass().add("tab-header");
		header.getChildren().addAll(image, label, button);

		this.setId(id);
		this.setContent(node);
		this.setGraphic(header);
	}

	public HBox getHeader()
	{
		return this.header;
	}

	public Button getCloseButton()
	{
		return this.button;
	}

	public void setMenu(IContextMenu menu)
	{
		menu.setNode(header);
	}

	public ITabPane getITabPane()
	{
		return (ITabPane) this.getTabPane();
	}
}
