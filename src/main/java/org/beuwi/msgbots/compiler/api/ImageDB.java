package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.platform.app.view.actions.DisplayErrorDialogAction;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

public class ImageDB {
    public String getProfileImage() {
        return getProfileBase64();
    }

    public String getProfileBase64() {
		try {
			File file = SharedValues.getFile("PROFILE_BOT_FILE");
			FileInputStream inputStream = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			inputStream.read(bytes);
			return Base64.getEncoder().encodeToString(bytes);
		}
		catch (Exception e) {
			DisplayErrorDialogAction.execute(e);
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