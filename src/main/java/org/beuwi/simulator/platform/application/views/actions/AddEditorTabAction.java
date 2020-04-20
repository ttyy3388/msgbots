package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.editor.IEditorPane;
import org.beuwi.simulator.platform.ui.editor.IEditorTab;
import org.beuwi.simulator.utils.ResourceUtils;

import java.util.List;

public class AddEditorTabAction
{
	private static SplitPane component;

	public static void initialize()
	{
		component = EditorAreaPart.getComponent();
	}

	public static void update(String id, String title, Node content)
	{
		update(ResourceUtils.getImage("tab_default.png"), id, title, content);
	}

	public static void update(Image image, String id, String title, Node content)
	{
		List<Node> items = component.getItems();

		for (Node node : items)
		{
			IEditorPane pane = (IEditorPane) node;

			if (pane.tabExists(id))
			{
				pane.selectTab(id);

				return ;
			}
		}

		EditorAreaPart.getSelectedPane().addTab(new IEditorTab(image, id, title, content));
	}
}