package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.TabPane;

public class PanelAreaPart implements View
{
	private static ObservableMap<String, Object> nameSpace;

	private static FormLoader loader;

	private static StackPane root;

	private static TabPane component;

	@Override
	public void init() throws Exception
	{
		loader = new FormLoader("panel-area-part");

		nameSpace = loader.getNamespace();
		root = loader.getRoot();

		component = (TabPane) loader.getComponent();

		TextArea textArea = (TextArea) nameSpace.get("txaInputArea");

		textArea.setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				if (event.isShiftDown())
				{
					textArea.appendText(System.lineSeparator());
					event.consume();
					return ;
				}

				String text = textArea.getText(); //.replace(/\n/, \g);

				String[] items = text.split(" ");

				switch (items[0])
				{
					case "open" :

						switch (items[1])
						{
							case "log" :
								System.out.println("show log"); break;
						}

						break;

					default : break;
				}

				System.out.println(items);
				// textArea.clear();
				event.consume();
			}
		});

		textArea.requestFocus();
	}

	public static StackPane getRoot()
	{
		return root;
	}

	public static TabPane getComponent()
	{
		return component;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}