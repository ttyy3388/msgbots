package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.Node;
import javafx.scene.image.Image;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.ICodeArea;
import org.beuwi.simulator.platform.ui.components.ITab;
import org.beuwi.simulator.platform.ui.components.ITabPane;
import org.beuwi.simulator.platform.ui.components.ITabType;
import org.beuwi.simulator.utils.ResourceUtils;

public class AddEditorTabAction
{
	private static ITabPane pane;

	public static void initialize()
	{
		 pane = EditorAreaPart.getComponent();
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
					case S : SaveEditorTabAction.update(name); break;
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
			case ITabType.CHAT, ITabType.LOG -> "@tab::" + title;
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
			case ITabType.CHAT   -> ResourceUtils.getImage("tab_chat.png");
			case ITabType.LOG    -> ResourceUtils.getImage("tab_log.png");
			case ITabType.SCRIPT -> ResourceUtils.getImage("tab_js.png");
			default 			 -> null;
		};

		pane.addTab(new ITab(image, id, title, node, type));
	}
}