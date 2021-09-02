package org.beuwi.msgbots.base.type;

public enum ToastType {
	INFO("info"),
	ERROR("error"),
	WARNING("warning"),
	TIP("tip");

	private final String name;
	ToastType(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}

	public static <T extends Enum<T>> ToastType convert(String value) {
		return ToastType.valueOf(value.toUpperCase());
	}
}
