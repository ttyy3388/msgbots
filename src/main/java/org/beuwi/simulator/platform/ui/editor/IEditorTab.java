package org.beuwi.simulator.platform.ui.editor;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		IContextMenu tabMenu = new IContextMenu
		(
			new IMenuItem("Close", "Ctrl + F4", event -> CloseEditorTabAction.update(this)),
			new IMenuItem("Close Others", event -> CloseOtherEditorTabsAction.update(this)),
			new IMenuItem("Close All", event -> CloseAllEditorTabsAction.update()),
			new SeparatorMenuItem(),
			new IMenuItem("Move to Right Editor", event -> MoveEditorPaneAction.update(this, IPos.RIGHT)),
			new IMenuItem("Move to Left Editor", event -> MoveEditorPaneAction.update(this, IPos.LEFT)),
			new SeparatorMenuItem(),
			new IMenuItem("Select Next Tab", "Alt + Right", event -> SelectEditorTabAction.update(this, IPos.RIGHT)),
			new IMenuItem("Select Previous Tab", "Alt + Left", event -> SelectEditorTabAction.update(this, IPos.LEFT))
		);

		HBox tabHeader    = new HBox();
		ImageView tabIcon = new ImageView();
		Label tabTitle    = new Label();
		Button tabClose   = new Button();

		tabIcon.setFitWidth(14);
		tabIcon.setFitHeight(14);
		tabIcon.setImage(icon);

		tabClose.setPrefWidth(14);
		tabClose.setPrefHeight(14);
		tabClose.getStyleClass().add("tab-close-button");
		tabClose.setOnMousePressed(event ->
		{
			if (event.isPrimaryButtonDown() || event.isMiddleButtonDown())
			{
				CloseEditorTabAction.update(this);
			}
		});

		tabTitle.setText(title);
		tabTitle.getStyleClass().add("tab-header-label");

		tabHeader.setSpacing(7);
		tabHeader.setAlignment(Pos.CENTER);
		tabHeader.getStyleClass().add("tab-header");
		tabHeader.getChildren().addAll(tabIcon, tabTitle, tabClose);
		tabHeader.setOnMousePressed(event ->
		{
			if (event.isMiddleButtonDown())
			{
				CloseEditorTabAction.update(this);
			}
			if (event.isSecondaryButtonDown())
			{
				tabMenu.show(tabHeader, event);
			}
		});

		tabHeader.onDragOverProperty().addListener((observable, oldValue, newValue) ->
		{
			// this.pseudoClassStateChanged(PseudoClass.getPseudoClass("over"), newValue);
		});

		this.setId(id);
		this.setContent(node);
		this.setGraphic(tabHeader);
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