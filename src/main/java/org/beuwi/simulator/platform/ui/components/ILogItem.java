package org.beuwi.simulator.platform.ui.components;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.utils.ResourceUtils;
import static org.beuwi.simulator.platform.ui.components.ILogType.*;

public class ILogItem extends AnchorPane
{
	public ILogItem(String text, String date, int type)
	{
		String itemType = switch (type)
		{
			case ERROR -> "error";
			case EVENT -> "event";
			case DEBUG -> "debug";
			default    -> null;
		};

		ImageView  image = new ImageView(ResourceUtils.getImage(itemType));
		Label 	   label = new Label(text);
		Label      idate = new Label(date);

		image.setFitWidth(20);
		image.setFitHeight(20);

		AnchorPane.setTopAnchor(image, 12.0);
		AnchorPane.setLeftAnchor(image, 12.0);

		AnchorPane.setTopAnchor(label, 12.0);
		AnchorPane.setLeftAnchor(label, 45.0);
		AnchorPane.setBottomAnchor(label, 45.0);
		AnchorPane.setRightAnchor(label, 12.0);

		AnchorPane.setLeftAnchor(idate, 45.0);
		AnchorPane.setBottomAnchor(idate, 12.0);

		label.setWrapText(true);
		label.getStyleClass().add(itemType);
		idate.getStyleClass().add(itemType);

		this.setId(text);
		this.setCursor(Cursor.HAND);
		this.setMinWidth(90);
		this.getStyleClass().add("list-item");
		this.getChildren().addAll(image, label, idate);
	}
}