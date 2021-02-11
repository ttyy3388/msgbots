package org.beuwi.msgbots.platform.gui.enums;

// 텍스트 렉더링 타입
public enum TextType {
    GRAY, LCD;

    public static <T extends Enum<T>> TextType parse(String value) {
        return valueOf(value.toUpperCase());
    }
}
