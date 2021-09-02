package org.beuwi.msgbots.base.type;

public enum ConfigType {
    GLOBAL("global"), PROJECT("project");

    private final String name;
    ConfigType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean match(String data) {
        return name.equals(data);
    }

    public static <T extends Enum<T>> ConfigType convert(String value) {
        return ConfigType.valueOf(value.toUpperCase());
    }
}
