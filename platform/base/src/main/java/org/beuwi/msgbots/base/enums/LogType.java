package org.beuwi.msgbots.base.enums;

public enum LogType {
    INFO {
        @Override
        public String toString() {
            return "info";
        }
    },
    ERROR {
        @Override
        public String toString() {
            return "error";
        }
    },
    DEBUG {
        @Override
        public String toString() {
            return "debug";
        }
    };

    public static <T extends Enum<T>> LogType convert(String value) {
        return LogType.valueOf(value.toUpperCase());
    }
}
