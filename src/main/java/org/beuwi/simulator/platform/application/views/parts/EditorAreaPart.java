package org.beuwi.simulator.platform.application.views.parts;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.ui.editor.IEditorPane;
import org.beuwi.simulator.utils.ResourceUtils;

import java.util.List;

public class EditorAreaPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static ObjectProperty<IEditorPane> property = new SimpleObjectProperty<>();

	private static SplitPane component;

	private static StackPane root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("EditorAreaPart"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		component = getComponent();

		component.getItems().addListener((ListChangeListener.Change <? extends Node> change) ->
		{
			List<Node> list = component.getItems();

			int size = list.size();

			// 삭제 시 자동으로 1번 에디터가 선택되도록
			while (change.next())
			{
				if (change.wasRemoved())
				{
					if (size > 0)
					{
						property.set((IEditorPane) list.get(0));
					}
				}
			}

			for (int i = 0 ; i < size - 1 ; i ++)
			{
				component.setDividerPosition(i, (1 / (double) size) * (i + 1));
			}
		});
	}

	public static StackPane getRoot()
	{
		return root;
	}

	public static SplitPane getComponent()
	{
		return (SplitPane) root.getChildren().get(0);
	}

	// Selected Editor Pane Property
	public static ObjectProperty getProperty()
	{
		return property;
	}

	/* public static List<IEditorPane> getEditorPanes()
	{
		return component.getItems().stream().map(node -> (IEditorPane) node).collect(Collectors.toList());
	} */

	public static IEditorPane getSelectedPane()
	{
		List<Node> list = getComponent().getItems();

		if (list.size() == 0)
		{
			list.add(new IEditorPane());
		}

		return property.get();
	}

	public static void setSelectedPane(IEditorPane editor)
	{
		property.set(editor);
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}