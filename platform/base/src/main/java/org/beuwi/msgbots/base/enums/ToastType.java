package org.beuwi.msgbots.base.type;

public enum ToastType {
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
    WARNING {
        @Override
        public String toString() {
            return "warning";
        }
    };

	public static <T extends Enum<T>> LogType convert(String value) {
		return LogType.valueOf(value.toUpperCase());
	}
}
