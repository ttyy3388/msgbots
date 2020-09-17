package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableMap;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;

public class PopupAreaPart implements View
{
	private static final PseudoClass SHOWING_PSEUDO_CLASS = PseudoClass.getPseudoClass("showing");

	private static final int DEFAULT_MARGIN_VALUE = 5;

	private static final int DEFAULT_MAX_WIDTH = 500;
	private static final int DEFAULT_MAX_HEIGHT = 200;

	private static ObservableMap<String, Object> nameSpace;

	private static FormLoader loader;

	private static BorderPane root;

	private static StackPane component;

	@Override
	public void init() throws Exception
	{
		loader = new FormLoader("popup-area-part");
		nameSpace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		// component.setMaxWidth(DEFAULT_MAX_WIDTH);
		// component.setMaxHeight(DEFAULT_MAX_HEIGHT);

		component.getChildren().addListener((ListChangeListener.Change<? extends Node> change) ->
		{
			boolean isEmpty = component.getChildren().isEmpty();

			if (!isEmpty)
			{
				Pane content = (Pane) component.getChildren().get(0);

				/* if (content.getMaxWidth() > 0)
				{
					component.setMaxWidth(content.getPrefWidth());
				}
				if (content.getMaxHeight() > 0)
				{
					component.setMaxHeight(content.getPrefHeight());
				} */
			}

			component.pseudoClassStateChanged(SHOWING_PSEUDO_CLASS, !isEmpty);
		});

		BorderPane.setAlignment(component, Pos.TOP_CENTER);
		BorderPane.setMargin(component, new Insets(DEFAULT_MARGIN_VALUE));
	}

	public static BorderPane getRoot()
	{
		return root;
	}

	public static StackPane getComponent()
	{
		return component;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}
