package org.beuwi.simulator.platform.application.actions;

import javafx.scene.Node;
import javafx.scene.image.Image;
import org.beuwi.simulator.platform.application.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.ITab;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class AddEditorTabAction
{
	private static ITabPane pane ;

	public static void initialize()
	{
		pane = EditorAreaPart.getComponent();
	}

	public static void update(Image image, String title, Node node, int type)
	{
		pane.addTab(new ITab(image, title, node, type));
	}
}