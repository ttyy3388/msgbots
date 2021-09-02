package org.beuwi.msgbots.base.type;

import java.io.File;

public enum ThemeType {
	DARK("dark"),
	LIGHT("light"),
	BLACK("black"),
	WHITE("white"),
	CUSTOM("user");

	private final String name;
	ThemeType(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}

	public File toFile() {
		return null; /* switch (this) {
			case DARK -> SharedValues.DARK_THEME_FILE;
			case LIGHT -> SharedValues.LIGHT_THEME_FILE;
			case BLACK -> null; // SharedValues.BLACK_THEME_FILE;
			case WHITE -> null; // SharedValues.WHITE_THEME_FILe;
			case USER -> SharedValues.USER_THEME_FILE;
		}; */
	}

	public static <T extends Enum<T>> ThemeType parse(String value) {
		return valueOf(value.toUpperCase());
		/* // 대문자 변환 시 일치하는게 없다면
		ThemeType result = valueOf(value.toUpperCase());

		if (result != null) {
			return result;
		}

		// 없다면 문자열로 변환한 거에서 비교
		for (ThemeType type : result.values()) {
			if (type.toString().equals(value)) {
				result = type;
			}
		}

		return result; */
	}
}
