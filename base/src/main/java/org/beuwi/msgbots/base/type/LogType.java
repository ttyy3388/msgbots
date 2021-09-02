package org.beuwi.msgbots.base.type;

public enum LogType {
    INFO("info"),
    ERROR("error"),
    DEBUG("debug");

    private final String name;
    LogType(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }

    public boolean match(String data) {
        return name.equals(data);
    }

    public static <T extends Enum<T>> LogType convert(String value) {
        return LogType.valueOf(value.toUpperCase());
    }
}
