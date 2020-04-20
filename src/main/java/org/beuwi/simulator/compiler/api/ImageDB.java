package org.beuwi.simulator.compiler.api;

import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialogBox;

public class ImageDB 
{
    public static String getProfileImage()
    {
        return getProfileBase64();
    }

    public static String getProfileBase64()
	{
		try
		{
			// byte[] bytes = IOUtils.toByteArray(FileManager.getDataFile("profile_bot.png"));

			// return Base64.getEncoder().encodeToString(bytes);
		}
		catch (Exception e)
		{
			new ShowErrorDialogBox(e).display();
		}

		return null;
	}

	public static int getProfileHash()
	{
		return getProfileBase64().hashCode();
	}

	public static Object getProfileBitmap()
	{
		return null;
	}

	public static String getImage()
	{
		return null;
	}
}
