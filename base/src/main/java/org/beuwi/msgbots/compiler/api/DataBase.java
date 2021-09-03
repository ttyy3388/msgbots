package org.beuwi.msgbots.compiler.api;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

@SuppressWarnings("serial")
public class DataBase  extends ScriptableObject {
    @Override
    public String getClassName() {
        return "DataBase";
    }

    final String name;

    public DataBase(ScriptableObject object, String name) {
		super(object, object.getPrototype());

		this.name = name;
	}

	@JSFunction
	public String appendDataBase(String fileName, String date) {
		return null;
	}

    @JSFunction
	public String setDataBase(String fileName, String data) {
		return null;
	}

    @JSFunction
	public String getDataBase(String fileName) {
		return null;
	}
	
    @JSFunction
	public Boolean removeDataBase(String fileName) {
		return null;
	}
}
