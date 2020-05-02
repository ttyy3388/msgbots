package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.ui.components.ICodePane;
import org.beuwi.simulator.utils.ResourceUtils;

public class OpenScriptTabAction
{
	public static void update(String name)
	{
		AddEditorTabAction.update
		(
			ResourceUtils.getImage("tab_script"),
			"@script::" + name, name,
			new ICodePane(FileManager.read(FileManager.getBotScript(name)))
		);
	}
}