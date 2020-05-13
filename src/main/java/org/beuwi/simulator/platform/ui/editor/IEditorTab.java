package org.beuwi.simulator.platform.ui.editor;

import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.views.actions.CloseAllTabsAction;
import org.beuwi.simulator.platform.application.views.actions.CloseOtherTabsAction;
import org.beuwi.simulator.platform.application.views.actions.CloseTabAction;
import org.beuwi.simulator.platform.application.views.actions.MoveEditorTabAction;
import org.beuwi.simulator.platform.application.views.actions.SelectEditorTabAction;
import org.beuwi.simulator.platform.application.views.actions.SplitEditorPaneAction;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuItem;
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
		image.setFitWidth(12);
		image.setFitHeight(12);

		button.setGraphic(ISVGGlyph.getGlyph("Tab.Close"));
		button.setPrefWidth(10);
		button.getStyleClass().add("tab-close-button");
		button.setOnAction(event -> CloseTabAction.update(this));

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
		setMenu(new IContextMenu
		(
			new IMenuItem("Close", "Ctrl + F4", event -> CloseTabAction.update(this)),
			new IMenuItem("Close Others", event -> CloseOtherTabsAction.update(this)),
			new IMenuItem("Close All", event -> CloseAllTabsAction.update(this)),
			new SeparatorMenuItem(),
			new IMenuItem("Move to Right Editor", event -> MoveEditorTabAction.update(this, Side.RIGHT)),
			new IMenuItem("Move to Left Editor", event -> MoveEditorTabAction.update(this, Side.LEFT)),
			new SeparatorMenuItem(),
			new IMenuItem("Select Next Tab", "Alt + \u202bRight", event -> SelectEditorTabAction.update(this, Side.RIGHT)),
			new IMenuItem("Select Previous Tab", "Alt + \u202bLeft", event -> SelectEditorTabAction.update(this, Side.LEFT)),
			new SeparatorMenuItem(),
			new IMenuItem("Split Right", event -> SplitEditorPaneAction.update(this, Side.RIGHT)),
			new IMenuItem("Split Left", event -> SplitEditorPaneAction.update(this, Side.LEFT))
		));
	}
}
