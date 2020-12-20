package org.beuwi.msgbots.platform.gui.editor;

import netscape.javascript.JSObject;

import java.util.function.Function;

public class JFunction implements Function<JSObject, Object>
{
	// Actual Callable
	private Function<Object[], Object> callable;

	public JFunction(Function<Object[], Object> callable)
	{
		this.callable = callable;
	}

	@Override
	public Object apply(JSObject args)
	{
		Integer numArgs = 0;
		boolean isArray = false;

		if (args != null)
		{
			try
			{
				numArgs = (Integer) args.getMember("length");
				isArray = true;
			}
			catch (NullPointerException e)
			{
				// length not available
				e.printStackTrace();
			}
		}

		if (isArray)
		{
			Object[] array = new Object[numArgs];

			for (int i = 0; i < numArgs; i++)
			{
				array[i] = args.getSlot(i);
			}

			return callable.apply(array);
		}

		return callable.apply(new Object[] { args });
	}
}