package org.beuwi.simulator.compiler.api;

import org.beuwi.simulator.settings.Settings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.annotations.JSStaticFunction;

public class Utils extends ScriptableObject
{
    @Override
    public String getClassName() 
    {
        return "Utils";
    }

	@JSStaticFunction
	public static String getWebText(String url)
	{
		try
		{
			return Jsoup.connect(url)
					.ignoreContentType(true)
					.timeout(Settings.getPublicSetting("script").getInt("htmlTimeOut"))
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
					.referrer("http://www.google.com")
					.get().toString();
		}
		catch (Exception e)
		{
			Context.reportError(e.toString());
		}

		return null;
	}

	@JSStaticFunction
	public static Document parse(String url)
	{
		try
		{
			return Jsoup.connect(url)
					.ignoreContentType(true)
					.timeout(Settings.getPublicSetting("script").getInt("htmlTimeOut"))
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
					.referrer("http://www.google.com")
					.get();
		}
		catch (Exception e)
		{
			Context.reportError(e.toString());
		}

		return null;
	}

	@JSStaticFunction
	public static Boolean isUndefined(Object value)
	{
		return Undefined.isUndefined(value);
	}

	@JSStaticFunction
	public static int getAndroidVersionCode()
	{
		return 28;
	}

	@JSStaticFunction
	public static String getAndroidVersionName()
	{
		return "9";
	}

	@JSStaticFunction
	public static String getPhoneBrand()
	{
		return "samsung";
	}

	@JSStaticFunction
	public static String getPhoneModel()
	{
		return "greatlteks";
	}
}