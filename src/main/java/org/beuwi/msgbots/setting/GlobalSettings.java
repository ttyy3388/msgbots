package org.beuwi.msgbots.setting;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.JSONObject;
import org.beuwi.msgbots.platform.gui.base.Listener;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
	// User Custom Setting File
	// private static final File custom = SharedValues.DATA_FOLDER_FILE;
	// private static final File file = SharedValues.GLOBAL_CONFIG_FILE;
	// private static JSONObject json = new JSONObject(file);

	private static final InputStream defaultFile = ResourceUtils.getInputStream("/data/global_config.json");
	private static final JSONObject defaultData = new JSONObject(defaultFile);

	private static File userFile = SharedValues.getFile("GLOBAL_CONFIG_FILE");
	private static JSONObject userData;

	// private static final List<Listener> listeners = new ArrayList<>();

	// 파일 변경 이벤트 시 호출
	// private static final BooleanProperty changedProperty = new SimpleBooleanProperty(false);

	// 외부에서 파일을 변경한 것에 한해 이벤트가 발생함
	/* public static void addChangeListener(Listener listener) {
		listeners.add(listener);
	} */
	// 실시간 기능 구현 시 건들 부분(에디터 등)이 많아지므로 보류함

	static {
		// 생성된 파일이 있다면 값들을 불러옴
		if (userFile.exists()) {
			userData = new JSONObject(userFile);
		}
		// 생성된 설정 파일이 없으면
		else {
			// 새 맵 입력
			userData = new JSONObject();
		}

		// 데이터 폴더 변경 이벤트 시
		FileManager.link(SharedValues.getValue("DATA_FOLDER_FILE"), () -> {
			// 파일이 존재한다면 | 새로 생성됐다면 | 변경됐다면
			if (userFile.exists()) {
				// listeners.forEach(Listener::changed); // Change Event
				userData = new JSONObject(userFile);
				// 혹시나 유저 데이터가 입력이 안된 경우가 있다면 ?
				if (userData == null) {
					throw new NullPointerException();
				}
			}
			// 파일이 삭제됐다면
			else {
				// 새 맵 입력
				userData = null;
			}
		});
		/* FileManager.link(file, () -> {
			for (Listener listener : listeners) {
				listener.changed();
			}
		}); */
	}

	/* public static void addChangedListener(Listener listener) {
		listeners.add(listener);
	} */

	// 유저가 커스텀한 세팅에 포함되어 있는지 확인
	private static boolean checkData(String name, String option) {
		// 유저 세팅 파일이 없다면
		if (userFile == null
			/*  || userData == null */) {
			return false;
		}

		// 해당 데이터가 있는지 확인
		Map map = userData.getMap(name);
		if (map == null) {
			return false;
		}

		// 해당 데이터가 있는지 확인
		return map.containsKey(option);
	}

	protected static <T> T getData(String address) {
		String[] data = address.split(":");

		String name = data[0];
		String option = data[1];

		Map map = null;
		// 유저 세팅 파일에 포함되어 있다면 유저가 지정한 값을 가져옴
		if (checkData(name, option)) {
			map = userData.getMap(name);
		}
		// 아니면 프로그램의 기본값을 가져옴
		else {
			map = defaultData.getMap(name);
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

	// 값을 입력하는거는 프로그램 기본값에 관련없이
	// 무조건 유저 커스텀 값으로 입력함
	public static <T> void setData(String address, T value) {
		String[] data = address.split(":");

		String name = data[0];
		String option = data[1];

		// 만약 해당 맵이 없다면
		if (!userData.containsKey(name)) { // or getMap(name) == null
			userData.put(name, new JSONObject());
		}
		// 기본 타입이 아닐 경우
		if (!(value instanceof String ||
			  value instanceof Double ||
			  value instanceof Boolean ||
			  value instanceof Integer ||
			  value instanceof Long)) {
			// 문자열 타입으로 변환
			userData.getMap(name).put(option, value.toString());
		}
		else {
			userData.getMap(name).put(option, value);
		}

		FileManager.save(userFile, userData.toString());
	}
}