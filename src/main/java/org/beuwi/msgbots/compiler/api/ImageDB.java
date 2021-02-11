package org.beuwi.msgbots.compiler.api;

public class ImageDB {
    public String getProfileImage() {
        return getProfileBase64();
    }

    public String getProfileBase64() {
		try {
			// byte[] bytes = IOUtils.toByteArray(FileManager.getDataFile("profile_bot.png"));

			// return Base64.getEncoder().encodeToString(bytes);
		}
		catch (Exception e) {
			// new ShowErrorDialog(e).display();
		}

		return null;
	}

	public int getProfileHash() {
		return getProfileBase64().hashCode();
	}

	public String getProfileMD5() {
		return null;
	}

	public String getProfileSHA() {
		return null;
	}

	public Object getProfileBitmap() {
		return null;
	}

	public String getImage() {
		return null;
	}

	public String toSource() {
		return null;
	}
}
