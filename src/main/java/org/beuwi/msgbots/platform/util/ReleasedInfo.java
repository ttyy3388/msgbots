package org.beuwi.msgbots.platform.util;

import org.beuwi.msgbots.manager.WebManager;
import org.beuwi.msgbots.openapi.JSONObject;

// 최신 버전의 프로그램 정보를 가져옴
public class ReleasedInfo {
    public static final JSONObject json = new JSONObject(
        WebManager.getText(SharedValues.RELEASED_INFO_LINK)
    );

    public static int getVersionCode() {
        return json.getInt("version_code");
    }

    public static String getVersionName() {
        return json.getString("version_name");
    }
}
