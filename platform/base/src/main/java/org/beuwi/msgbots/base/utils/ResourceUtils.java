package org.beuwi.msgbots.base.utils;

import java.io.InputStream;

// 해당 클래스로 접근하는 파일들은 기본값 파일들임 ([FileManager] 클래스와 반대)
public class ResourceUtils {
    public static InputStream getData(String name) {
        return ResourceUtils.class.getResourceAsStream("/data/" + name);
    }
}
