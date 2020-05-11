package org.beuwi.simulator.platform.ui.editor;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.ui.components.ISVGGlyph;
import org.beuwi.simulator.platform.ui.components.ITab;
import org.beuwi.simulator.utils.ResourceUtils;

public class IEditorTab extends ITab
{
	// Tab Header
	HBox header = new HBox();

	// Tab Image
	ImageView image = new ImageView();

	// Tab Title
	Label label = new Label();

	// Tab Close Button
	Button button = new Button();

	public IEditorTab()
    {
        this(null);
    }

	public IEditorTab(String title)
    {
        this(title, null);
    }

	public IEditorTab(String title, Node node)
	{
		this(title, title, node);
	}

	public IEditorTab(String id, String title, Node node)
	{
		this(ResourceUtils.getImage("tab_default"), id, title, node);
	}

	public IEditorTab(Image icon, String id, String title, Node node)
	{
		image.setImage(icon);
		image.setFitWidth(14);
		image.setFitHeight(14);

		button.setGraphic(ISVGGlyph.getGlyph("Tab.Close"));
		button.setPrefWidth(14);
		button.setPrefHeight(14);
		button.getStyleClass().add("tab-close-button");
		// button.setOnAction(event -> CloseTabAction.update(this));

		label.setText(title);
		label.getStyleClass().add("tab-header-label");

		header.setSpacing(7);
		header.setPrefHeight(30);
		header.setAlignment(Pos.CENTER);
		header.getStyleClass().add("tab-header-box");
		header.getChildren().addAll(image, label, button);

		setId(id);
		setHeader(header);
		setContent(node);
	}

	public IEditorPane getEditorPane()
	{
		return (IEditorPane) getTabPane();
	}
}
