package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.platform.ui.editor.IEditorPane;

public class SaveAllEditorTabsAction
{
	private static IEditorPane pane;

	public static void initialize()
	{
		// pane = EditorAreaPart.getSelectedPane();
	}

	public static void update()
	{
		/* for (Tab tab : pane.getTabs())
		{
			// ID 부분 추후 수정
			if (tab.getId().startsWith("@script::"))
			{
			   FileManager.save(FileManager.getBotScript(ProgramUtils.getName(tab.getId())), ((ICodeArea) tab.getContent()).getText());
			}
		} */
	}
}