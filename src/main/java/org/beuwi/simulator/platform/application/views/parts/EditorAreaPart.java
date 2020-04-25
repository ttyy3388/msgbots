package org.beuwi.simulator.platform.application.views.parts;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.ui.editor.IEditorPane;
import org.beuwi.simulator.utils.ResourceUtils;

import java.util.List;

public class EditorAreaPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static ObjectProperty<IEditorPane> property = new SimpleObjectProperty<>();

	private static SplitPane component;

	private static AnchorPane root;

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
			int size = component.getItems().size();

			for (int i = 0 ; i < size - 1 ; i ++)
			{
				component.setDividerPosition(i, (1 / (double) size) * (i + 1));
			}
		});
	}

	public static Node getRoot()
	{
		return root;
	}

	// Selected Editor Pane Property
	public static ObjectProperty getProperty()
	{
		return property;
	}

	public static SplitPane getComponent()
	{
		return (SplitPane) root.getChildren().get(0);
	}

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