package org.beuwi.simulator.platform.ui.editor;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.actions.CloseAllTabsAction;
import org.beuwi.simulator.platform.application.actions.CloseTabAction;
import org.beuwi.simulator.platform.application.views.actions.CloseOtherEditorTabsAction;
import org.beuwi.simulator.platform.application.views.actions.MoveEditorPaneAction;
import org.beuwi.simulator.platform.application.views.actions.SelectEditorTabAction;
import org.beuwi.simulator.platform.application.views.actions.SplitEditorPaneAction;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuItem;
import org.beuwi.simulator.platform.ui.components.IPos;
import org.beuwi.simulator.platform.ui.components.ISVGGlyph;
import org.beuwi.simulator.platform.ui.components.ITab;

public class IEditorTab extends ITab
{
	HBox 	  header = new HBox();
	ImageView image  = new ImageView();
	Label 	  label  = new Label();
	Button 	  button = new Button();

	public IEditorTab(Image icon, String id, String title, Node node)
	{
		image.setImage(icon);
		image.setFitWidth(14);
		image.setFitHeight(14);

		button.setGraphic(ISVGGlyph.getGlyph("Tab.Close"));
		button.setPrefWidth(14);
		button.setPrefHeight(14);
		button.getStyleClass().add("tab-close-button");
		button.setOnAction(event -> CloseTabAction.update(this));

		label.setText(title);
		label.getStyleClass().add("tab-header-label");

		header.setSpacing(7);
		header.setPrefHeight(30);
		header.setAlignment(Pos.CENTER);
		header.getStyleClass().add("tab-header-box");
		header.getChildren().addAll(image, label, button);

		this.setId(id);
		this.setHeader(header);
		this.setContent(node);
		this.setMenu(new IContextMenu
		(
			new IMenuItem("Close", "Ctrl + F4", event -> CloseTabAction.update(this)),
			new IMenuItem("Close Others", event -> CloseOtherEditorTabsAction.update(this)),
			new IMenuItem("Close All", event -> CloseAllTabsAction.update(this)),
			new SeparatorMenuItem(),
			new IMenuItem("Move To Right Editor", event -> MoveEditorPaneAction.update(this, IPos.RIGHT)),
			new IMenuItem("Move To Left Editor", event -> MoveEditorPaneAction.update(this, IPos.LEFT)),
			new SeparatorMenuItem(),
			new IMenuItem("Select Next Tab", "Alt + Right", event -> SelectEditorTabAction.update(this, IPos.RIGHT)),
			new IMenuItem("Select Previous Tab", "Alt + Left", event -> SelectEditorTabAction.update(this, IPos.LEFT)),
			new SeparatorMenuItem(),
			new IMenuItem("Split Right", event -> SplitEditorPaneAction.update(this, IPos.RIGHT)),
			new IMenuItem("Split Left", event -> SplitEditorPaneAction.update(this, IPos.LEFT))
		));
	}

	public IEditorPane getEditorPane()
	{
		return (IEditorPane) this.getEditorTabPane().getParent();
	}

	public IEditorTabPane getEditorTabPane()
	{
		return (IEditorTabPane) this.getTabPane();
	}
}