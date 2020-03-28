package org.beuwi.simulator.compiler.api;

import org.apache.commons.io.IOUtils;
import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;

import java.net.URL;
import java.util.Base64;

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
			byte[] bytes = IOUtils.toByteArray(new URL(ImageDB.class.getResource("/datas/BorProfile.png").toExternalForm()));

			return Base64.getEncoder().encodeToString(bytes);
		}
		catch (Exception e)
		{
			new ShowErrorDialog(e).display();
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
