package org.beuwi.simulator.compiler.api;

import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;

public class ImageDB 
{
    public String getProfileImage()
    {
        return getProfileBase64();
    }

    public String getProfileBase64()
	{
		try
		{
			// byte[] bytes = IOUtils.toByteArray(FileManager.getDataFile("profile_bot.png"));

			// return Base64.getEncoder().encodeToString(bytes);
		}
		catch (Exception e)
		{
			new ShowErrorDialog(e).display();
		}

		return null;
	}

	public int getProfileHash()
	{
		return getProfileBase64().hashCode();
	}

	public Object getProfileBitmap()
	{
		return null;
	}

	public String getImage()
	{
		return null;
	}
}
