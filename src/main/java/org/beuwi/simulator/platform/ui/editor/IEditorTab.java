package org.beuwi.simulator.platform.ui.editor;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.views.actions.*;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuItem;
import org.beuwi.simulator.platform.ui.components.IPos;

public class IEditorTab extends Tab
{
	private IEditorPane editor = null;

	public IEditorTab(Image icon, String id, String title, Node node)
	{
		IContextMenu menu = new IContextMenu
		(
			new IMenuItem("Close", "Ctrl + F4", event -> CloseEditorTabAction.update(this)),
			new IMenuItem("Close Others", event -> CloseOtherEditorTabsAction.update(this)),
			new IMenuItem("Close All", event -> CloseAllEditorTabsAction.update()),
			new SeparatorMenuItem(),
			new IMenuItem("Move To Right Editor", event -> MoveEditorPaneAction.update(this, IPos.RIGHT)),
			new IMenuItem("Move To Left Editor", event -> MoveEditorPaneAction.update(this, IPos.LEFT)),
			new SeparatorMenuItem(),
			new IMenuItem("Select Next Tab", "Alt + Right", event -> SelectEditorTabAction.update(this, IPos.RIGHT)),
			new IMenuItem("Select Previous Tab", "Alt + Left", event -> SelectEditorTabAction.update(this, IPos.LEFT)),
			new SeparatorMenuItem(),
			new IMenuItem("Split Right", event -> SplitEditorPaneAction.update(this, IPos.RIGHT)),
			new IMenuItem("Split Left", event -> SplitEditorPaneAction.update(this, IPos.LEFT))
		);

		HBox 	  header = new HBox();
		ImageView image  = new ImageView();
		Label     label  = new Label();
		Button    button = new Button();

		image.setFitWidth(14);
		image.setFitHeight(14);
		image.setImage(icon);

		button.setPrefWidth(14);
		button.setPrefHeight(14);
		button.getStyleClass().add("tab-close-button");
		button.setOnMouseClicked(event ->
		{
			if (MouseButton.PRIMARY.equals(event.getButton()) || MouseButton.MIDDLE.equals(event.getButton()))
			{
				CloseEditorTabAction.update(this);
			}
		});

		label.setText(title);
		label.getStyleClass().add("tab-header-label");

		header.setSpacing(7);
		header.setAlignment(Pos.CENTER);
		header.getStyleClass().add("tab-header");
		header.getChildren().addAll(image, label, button);
		header.setOnMouseClicked(event ->
		{
			if (MouseButton.MIDDLE.equals(event.getButton()))
			{
				CloseEditorTabAction.update(this);
			}
		});

		menu.setNode(header);

		header.onDragOverProperty().addListener((observable, oldValue, newValue) ->
		{
			// this.pseudoClassStateChanged(PseudoClass.getPseudoClass("over"), newValue);
		});

		this.setId(id);
		this.setContent(node);
		this.setGraphic(header);
	}

	public void setEditorPane(IEditorPane editor)
	{
		this.editor = editor;
	}

	public IEditorPane getEditorPane()
	{
		return editor;
	}
}