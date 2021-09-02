package org.beuwi.msgbots.compiler.engine;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ScriptUtils {
	public static ScriptableObject convert(ScriptableObject object) {
	    Class clazz = object.getClass();
		String[] list = getJSFunctions(clazz).toArray(new String[0]);
		object.defineFunctionProperties(list, clazz, ScriptableObject.EMPTY);
		return object;
	}

	public static List<String> getJSFunctions(Class clazz) {
        List<String> list = new ArrayList<>();
		for (Method method : clazz.getMethods()) {
			if (method.getAnnotation(JSFunction.class) != null) {
				list.add(method.getName());
			}
		}
		return list;
    }
}
