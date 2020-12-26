package org.beuwi.msgbots.platform.util;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.JsonObject;
import org.beuwi.msgbots.openapi.JsonParser;
import org.beuwi.msgbots.platform.app.view.actions.DisplayErrorDialogAction;
import org.beuwi.msgbots.platform.app.view.dialogs.ShowErrorDialog;
import org.json.simple.parser.ParseException;

public class ProgramData {

	// Program Data Change Property
	private static final ObjectProperty<JsonObject> property = new SimpleObjectProperty();

	static {
		 // Default Value
		property.set(new JsonObject(SharedValues.PROGRAM_DATA_FILE));

		// 파일 관찰해서 변경 시 Property 발동
		FileManager.link(SharedValues.PROGRAM_DATA_FILE, () -> {
			property.set(new JsonObject(SharedValues.PROGRAM_DATA_FILE));
		});
	}

	public static ReadOnlyObjectProperty<JsonObject> changeProperty() {
		return property;
	}
}
