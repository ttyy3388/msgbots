package org.beuwi.msgbots.openapi;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.view.actions.DisplayErrorDialogAction;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.util.List;

public class JSONArray extends org.json.simple.JSONArray {
	public JSONArray() {
		super();
	}

	public JSONArray(File file) {
		try {
			this.addAll((List) new JSONParser().parse(FileManager.read(file)));
		}
		catch (ParseException e) {
			DisplayErrorDialogAction.execute(e);
		}
	}
}