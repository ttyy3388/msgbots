package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.Node;
import javafx.scene.image.Image;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.editor.IEditorPane;
import org.beuwi.simulator.platform.ui.editor.IEditorTab;
import org.beuwi.simulator.utils.ResourceUtils;

public class AddEditorTabAction
{
	private static IEditorPane pane;

	public static void update(String id, String title, Node node)
	{
		update(ResourceUtils.getImage("tab_default.png"), id, title, node);
	}

	public static void update(Image image, String id, String title, Node node)
	{
		pane = EditorAreaPart.getSelectedPane();

		if (pane.tabExists(id))
		{
			pane.selectTab(id);
			return ;
		}

		pane.addTab(new IEditorTab(image, id, title, node));
	}
}