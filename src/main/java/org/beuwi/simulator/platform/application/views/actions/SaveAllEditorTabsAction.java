package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.platform.ui.components.ITabPane;

public class SaveAllEditorTabsAction
{
    private static ITabPane pane;

    public static void initialize()
    {
        // pane = EditorAreaPart.getComponent();
    }

    public static void update()
	{
		/* if (pane.getTabs() != null)
		{
			for (Tab tab : pane.getTabs())
			{
				FileManager.save(FileManager.getBotScript(tab.getId()), ((ICodeArea) tab.getContent()).getText());
			}
		} */
	}
}
