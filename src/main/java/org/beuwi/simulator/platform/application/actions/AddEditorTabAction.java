package org.beuwi.simulator.platform.application.actions;

import javafx.scene.Node;
import javafx.scene.image.Image;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.ICodeArea;
import org.beuwi.simulator.platform.ui.components.ITab;
import org.beuwi.simulator.platform.ui.components.ITabPane;
import org.beuwi.simulator.platform.ui.components.ITabType;

public class AddEditorTabAction
{
	private static ITabPane pane;

	public static void initialize()
	{
		pane = (ITabPane) EditorAreaPart.getNameSpace().get("tapEditorArea");
	}

	// Script Tab
	public static void update(String name)
	{
		ICodeArea area = new ICodeArea();

		area.setText(FileManager.read(FileManager.getBotScript(name)));
		area.setOnKeyPressed(event ->
		{
			if (event.isControlDown())
			{
				switch (event.getCode())
				{
					case S : FileManager.save(FileManager.getBotScript(name), area.getText()); break;
				}
			}
		});

		update(name, area, ITabType.SCRIPT);
	}

	// Debug, Log Tab
	public static void update(String title, Node node, int type)
	{
		String id = switch (type)
		{
			case ITabType.DEBUG, ITabType.LOG -> "@tab::" + title;
			case ITabType.SCRIPT -> "@script::" + title;
			default -> null;
		};

		// 탭 중복 검사
		if (pane.tabExists(id))
		{
			pane.selectTab(id);
			return ;
		}

		Image image = switch (type)
		{
			case ITabType.DEBUG  -> new Image(AddEditorTabAction.class.getResource("/images/tab_debug.png").toExternalForm());
			case ITabType.LOG    -> new Image(AddEditorTabAction.class.getResource("/images/tab_log.png").toExternalForm());
			case ITabType.SCRIPT -> new Image(AddEditorTabAction.class.getResource("/images/tab_js.png").toExternalForm());
			default 			 -> null;
		};

		pane.addTab(new ITab(image, id, title, node, type));
	}
}