package org.beuwi.msgbots.platform.app.view.parts;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class ActiveAreaPart extends View
{
	private static Pane resize;

	@Override
	public void init() throws Exception
	{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ResourceUtils.getForm("ActiveAreaPart"));
        loader.setController(null);
        loader.load();

        nameSpace = loader.getNamespace();

        root = loader.getRoot();
	}

	public static HBox getComponent()
	{
		return (HBox) root.getChildren().get(0);
	}

	public static VBox getActivityBar()
	{
		return (VBox) getComponent().getChildren().get(0);
	}

	public static StackPane getSideBar()
	{
		return (StackPane) getComponent().getChildren().get(1);
	}

	public static Pane getResizeBar()
	{
		return (Pane) getRoot().getChildren().get(1);
	}
}