package org.beuwi.msgbots.compiler.api;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.annotations.JSFunction;

public class Utils extends ScriptableObject
{
    @Override
    public String getClassName() 
    {
        return "Utils";
    }

    final String name;

    public Utils(ScriptableObject object, String name)
	{
		super(object, object.getPrototype());

		this.name = name;
	}

	@JSFunction
	public String getWebText(String url)
	{
		return null;
	}

	@JSFunction
	public String parse(String url)
	{
		return null;
	}

	@JSFunction
	public Boolean isUndefined(Object value)
	{
		return Undefined.isUndefined(value);
	}

	@JSFunction
	public int getAndroidVersionCode()
	{
		return 28;
	}

	@JSFunction
	public String getAndroidVersionName()
	{
		return "9";
	}

	@JSFunction
	public String getPhoneBrand()
	{
		return "samsung";
	}

	@JSFunction
	public String getPhoneModel()
	{
		return "greatlteks";
	}
}