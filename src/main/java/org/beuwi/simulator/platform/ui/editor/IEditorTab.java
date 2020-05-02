package org.beuwi.simulator.platform.ui.editor;

import javafx.scene.Node;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import org.beuwi.simulator.platform.application.views.actions.CloseAllTabsAction;
import org.beuwi.simulator.platform.application.views.actions.CloseOtherTabsAction;
import org.beuwi.simulator.platform.application.views.actions.CloseTabAction;
import org.beuwi.simulator.platform.application.views.actions.SelectEditorTabAction;
import org.beuwi.simulator.platform.application.views.actions.SplitEditorPaneAction;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuItem;
import org.beuwi.simulator.platform.ui.components.IPos;
import org.beuwi.simulator.platform.ui.components.ITab;

public class IEditorTab extends ITab
{
	private IEditorPane editor = null;

	public IEditorTab(Image icon, String id, String title, Node node)
	{
		super(icon, id, title, node);

		this.setMenu(new IContextMenu
		(
			new IMenuItem("Close", "Ctrl + F4", event -> CloseTabAction.update(this)),
			new IMenuItem("Close Others", event -> CloseOtherTabsAction.update(this)),
			new IMenuItem("Close All", event -> CloseAllTabsAction.update(this)),
			// new SeparatorMenuItem(),
			// new IMenuItem("Move To Right Editor", event -> MoveEditorPaneAction.update(this, IPos.RIGHT)),
			// new IMenuItem("Move To Left Editor", event -> MoveEditorPaneAction.update(this, IPos.LEFT)),
			new SeparatorMenuItem(),
			new IMenuItem("Select Next Tab", "Alt + Right", event -> SelectEditorTabAction.update(this, IPos.RIGHT)),
			new IMenuItem("Select Previous Tab", "Alt + Left", event -> SelectEditorTabAction.update(this, IPos.LEFT)),
			new SeparatorMenuItem(),
			new IMenuItem("Split Right", event -> SplitEditorPaneAction.update(this, IPos.RIGHT)),
			new IMenuItem("Split Left", event -> SplitEditorPaneAction.update(this, IPos.LEFT))
		));
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