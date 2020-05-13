package org.beuwi.simulator.compiler.engine;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ScriptUtils
{
    // Copyright (C) 2020 NenkaLab
	public static Object convert(ScriptableObject object)
	{
		Class<?> clazz = object.getClass();

		ArrayList functions = getFunctions(clazz);

		String[] list = new String[functions.size()];

		functions.toArray(list);

		object.defineFunctionProperties(list, clazz, ScriptableObject.EMPTY);

		return object;
	}

    // Copyright (C) 2020 NenkaLab
	public static ArrayList<String> getFunctions(Class<?> clazz)
	{
		ArrayList list = new ArrayList<>();

		for (Method method : clazz.getMethods())
		{
			if (method.getAnnotation(JSFunction.class) != null)
			{
				list.add(method.getName());
			}
		}

		return list;
	}
}
