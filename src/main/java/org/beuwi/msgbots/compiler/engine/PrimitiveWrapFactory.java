package org.beuwi.msgbots.compiler.engine;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.WrapFactory;

public class PrimitiveWrapFactory extends WrapFactory 
{
	@Override
    public Object wrap(Context context, Scriptable scope, Object object, Class<?> staticType)
  	{
		if (object instanceof String || object instanceof Number || object instanceof Boolean)
		{
			return object;
		}

		else if (object instanceof Character) 
		{
			return new String(new char[] { ((Character) object).charValue() });
		}

		return super.wrap(context, scope, object, staticType);
  	}
}