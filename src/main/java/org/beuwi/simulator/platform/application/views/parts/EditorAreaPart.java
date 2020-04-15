package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.ui.editor.IEditorPane;

import java.util.List;

public class EditorAreaPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static IEditorPane pane;

	private static AnchorPane root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(EditorAreaPart.class.getResource("/forms/EditorAreaPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();
	}

	public static Node getRoot()
	{
		return root;
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

		return pane;
	}

	public static void setSelectedPane(IEditorPane editor)
	{
		pane = editor;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}