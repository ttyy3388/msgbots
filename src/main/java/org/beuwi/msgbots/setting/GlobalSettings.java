package org.beuwi.msgbots.setting;

import org.beuwi.msgbots.base.Dfile;
import org.beuwi.msgbots.base.JObject;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.utils.SharedValues;

import java.util.Map;

/*
 * 프로그램 세팅 파일(defaultFile)과 유저 세팅 파일(userFile)을 따로 분리함
 * 유저가 지정한 설정(세팅 파일에 포함)이 있다면 가져오고
 * 없다면 프로그램 기본 설정 파일을 가져옴
 *
 * 그리고 설정 초기화 옵션 시 유저 설정 파일을 Clean 처리함

	private class DefaultSettings {

	}
	private class CustomSettings {

	}
 */

// 기존에는 파일 변경 시 감지했지만 시작 시 값으로 진행함. -> 다시 실시간 감지로 변경
public class GlobalSettings {
	// 프로그램 기본 세팅
	private static final Dfile dfile = SharedValues.getDfile("dfile.globalConfig");
	private static final JObject config = new JObject(dfile.toResource());

	protected static <T> T getData(String address) {
		String[] data = address.split("\\.");

		String name = data[0];
		String option = data[1];

		// 프로그램의 기본값을 가져옴
		Map map = config.getMap(name);

		// 만약 유저 세팅 파일이 생성되었다면
		if (dfile.isCreated()) {
			JObject user = new JObject(dfile.toFile());
			// 파일에 해당 값이 있다면 가져옴
			if (user.getMap(option) != null) {
				map = user.getMap(name);
			}
		}

		// throw new NullPointerException("this address does not exists");

		if (map == null) {
			throw new NullPointerException("map name " + name + " does not exist");
		}

		return (T) map.get(option);
	}

	public static int getInt(String address) {
		return Integer.valueOf("" + getData(address));
	}

	public static String getString(String address) {
		String data = getData(address);
		if (data != null) {
			return String.valueOf("" + data);
		}
		else {
			return null;
		}
	}

	public static boolean getBoolean(String address) {
		return Boolean.valueOf("" + getData(address));
	}

	// 값을 입력하는거는 프로그램 기본값에 관련없이, 무조건 유저 커스텀 값으로 입력함
	public static <T> void setData(String address, T value) {
		String[] data = address.split("\\.");

		String name = data[0];
		String option = data[1];

		// 유저 세팅 파일이 생성되지 않았다면
		if (!dfile.isCreated()) {
			dfile.create(); // 생성
		}

		JObject user = new JObject(dfile.toFile());

		// 만약 해당 맵이 없다면
		if (user.getMap(name) == null) { // or getMap(name) == null
			user.put(name, new JObject());
		}
		// 기본 타입이 아닐 경우
		if (!(value instanceof String ||
			  value instanceof Double ||
			  value instanceof Boolean ||
			  value instanceof Integer ||
			  value instanceof Long)) {
			// 문자열 타입으로 변환
			user.getMap(name).put(option, value.toString());
		}
		else {
			user.getMap(name).put(option, value);
		}

		dfile.setData(user.toString());
	}
}