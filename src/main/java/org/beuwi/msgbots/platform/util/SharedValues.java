package org.beuwi.msgbots.platform.util;

import javafx.scene.image.Image;
import org.beuwi.msgbots.manager.FileManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

// 주의 : 기본 형식이 아닌 오브젝트는 "try catch" 감싸줘야 함
public class SharedValues /* implements java.io.Serializable */ {
	// 초기화 블럭으로 선언하면 우선순위가 낮아지는 듯 함(NullPointerException)이 발생하므로 "Static Block"로 이동
	public static final SharedMap<String, Object> values = new SharedMap<String, Object>();
	// 주의 : 초기화 블럭 사용 시 "try catch" 감싸줘야 함
	static {
		try {
			values.put(false, "DEFAULT_PROGRAM_TITLE", "Messenger Bot Simulator");
			values.put(false, "DEFAULT_PROGRAM_IMAGE", ResourceUtils.getImage("program"));
			values.put(false, "SYSTEM_WINDOWS_VERSION", System.getProperty("windows.os"));
			values.put(false, "SYSTEM_USER_NAME", System.getProperty("user.name"));

			/* Chrome */ // Strings.xml로 옮기기
			values.put(false, "DEFAULT_USER_AGENT", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");

			/* LINKS */
			values.put(false, "VIEW_LICENSE_LINK", "https://github.com/ttyy3388/msgbot/blob/master/LICENSE");
			values.put(false, "RELEASE_NOTES_LINK", "https://ttyy3388.gitbook.io/messenger-bot-simulator");
			values.put(false, "RELEASED_INFO_LINK", "https://ttyy3388.develope.dev/getData.php?type=Msgbots&value=Info");// Program Info

			/* Paths */
			values.put(false, "MAIN_FOLDER_PATH", System.getProperty("user.dir"));
			values.put(false, "DATA_FOLDER_PATH", System.getProperty("user.dir") + File.separator + "data");
			values.put(true, "BOTS_FOLDER_PATH", System.getProperty("user.dir") + File.separator + "bots");

			values.put(false, "MAIN_FOLDER_FILE", new File(getString("MAIN_FOLDER_PATH"))); // 프로그램 폴더는 경로 강제
			values.put(false, "DATA_FOLDER_FILE", new File(getString("DATA_FOLDER_PATH"))); // 데이터 폴더도 경로 강제
			values.put(true, "BOTS_FOLDER_FILE", new File(getString("BOTS_FOLDER_PATH"))); // 봇 폴더는 커스텀 가능

			// put(false, "CUSTOM_THEME_PATH", "custom_theme.css"); // Strings.xml로 옮기기
			values.put(false, "PROFILE_SENDER_PATH", "profile_sender.png"); // Strings.xml로 옮기기
			values.put(false, "PROFILE_BOT_PATH", "profile_bot.png"); // Strings.xml로 옮기기
			values.put(false, "GLOBAL_LOG_PATH", "global_log.json"); // Strings.xml로 옮기기
			values.put(false, "GLOBAL_CONFIG_PATH", "global_config.json"); // Strings.xml로 옮기기
			values.put(false, "SCRIPT_DEFAULT_PATH", "script_default.js"); // Strings.xml로 옮기기
			values.put(false, "SCRIPT_UNIFIED_PATH", "script_unified.js"); // Strings.xml로 옮기기
			values.put(false, "PROGRAM_DATA_PATH", "program_data.json"); // Strings.xml로 옮기기

			// 아래 파일들은 생성이 안됐다면(유저가 값을 변경할 적이 없다면) 초기값은 NULL임
			// put("CUSTOM_THEME_FILE", FileManager.getDataFile(getString("USER_CUSTOM_THEME_PATH"));
			values.put(false, "PROFILE_SENDER_FILE", FileManager.getDataFile(getString("PROFILE_SENDER_PATH")));
			values.put(false, "PROFILE_BOT_FILE", FileManager.getDataFile(getString("PROFILE_BOT_PATH")));
			values.put(false, "GLOBAL_LOG_FILE", FileManager.getDataFile(getString("GLOBAL_LOG_PATH")));
			values.put(false, "GLOBAL_CONFIG_FILE", FileManager.getDataFile(getString("GLOBAL_CONFIG_PATH")));
			values.put(false, "SCRIPT_DEFAULT_FILE", FileManager.getDataFile(getString("SCRIPT_DEFAULT_PATH")));
			values.put(false, "SCRIPT_UNIFIED_FILE", FileManager.getDataFile(getString("SCRIPT_UNIFIED_PATH")));
			values.put(false, "PROGRAM_DATA_FILE", FileManager.getDataFile(getString("PROGRAM_DATA_PATH")));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public static <T> T getValue(String key) {
		return (T) values.get(key);
	}
	public static void setValue(String key, Object value) {
		values.put(key, value);
	}

	public static Image getImage(String key) {
		return getValue(key);
	}
	public static Object getObject(String key) {
		return getValue(key);
	}
	public static File getFile(String key) {
		return getValue(key);
	}
	public static String getString(String key) {
		String data = getValue(key);
		if (data != null) {
			return String.valueOf("" + data);
		}
		else {
			return null;
		}
	}

	/* static {
		try {
			SYSTEM_WINDOWS_VERSION;
			SYSTEM_USER_NAME ;
		}
		catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	} */

	/* public static final ObjectProperty<File> CURRENT_SCRIPT_FILE = new SimpleObjectProperty();
	public static final ObjectProperty<File> getCurrentScriptFileProperty() {
		return CURRENT_SCRIPT_FILE;
	} */

	// public static final Image PROFILE_SENDER_IMAGE;
	// public static final Image PROFILE_BOT_IMAGE;

	// public static final File BASE_THEME_FILE =  ResourceUtils.getTheme("/themes/base.css");
	// public static final File DARK_THEME_FILE = ResourceUtils.getFile("/themes/dark.css");
	// public static final File LIGHT_THEME_FILE = ResourceUtils.getFile("/themes/light.css");
	// public static final File BLACK_THEME_FILE = ResourceUtils.getFile("/themes/dark.css");
	// public static final File WHITE_THEME_FILE = ResourceUtils.getFile("/themes/dark.css");

	// 주의 : 기본 형식이 아닌 오브젝트는 "try catch" 감싸줘야 함
	/* static {
		try {
			// PROFILE_SENDER_IMAGE = new Image(SharedValues.PROFILE_SENDER_FILE.toURI().toString());
			// PROFILE_BOT_IMAGE = new Image(SharedValues.PROFILE_BOT_FILE.toURI().toString());

			// ABOUT_PROGRAM_DOCUMENT = new Document("ABOUT PROGRAM", ABOUT_PROGRAM_PAGE);
			// PROGRAM_START_DOCUMENT = new Document("START PROGRAM", PROGRAM_START_PAGE);
			// WELCOME_GUIDE_DOCUMENT = new Document("WELCOME GUIDE", WELCOME_GUIDE_PAGE);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	} */

	public static class SharedMap<K, V> extends HashMap<K, V> {
		// 수정 가능 여부를 저장함
		private final Map<Boolean, K> dataMap = new HashMap<>();

		@Override
		public V get(Object key) {
			// System.out.println("Get : " + key + " : " + dataMap.values());
			// 없는 키를 입력했을 경우
			if (!super.containsKey(key)) {
				// 올바르지 않은 접근
				throw new NullPointerException("wrong access key");
			}
			else {
				return super.get(key);
			}
		}

		@Override
		public V put(K key, V value) {
			this.put(false, key, value);
			return null;
		}

		// modifiable이 true라면 final처럼 간주함 (값변경 X)
		public V put(boolean modifiable, K key, V value) {
			// System.out.println("Put : " + key + " : " + dataMap.values());
			// 기존에 있는 값일 경우
			if (dataMap.containsKey(key)) {
				boolean modifiableValue = Boolean.TRUE.equals(dataMap.get(key));
				// 수정이 불가능한데 값을 수정하려고 했다면
				if (!modifiableValue) {
					throw new RuntimeException("cannot assign " + key + " value to final variable " + key);
				}
				// 수정이 가능하다면
				else {
					dataMap.put(modifiable, key);
					super.put(key, value);
				}
			}
			// 새 값일 경우
			else {
				dataMap.put(modifiable, key);
				super.put(key, value);
				// report error
			}

			return null;
		}

		// 값 삭제를 불가능하게 함
		@Override
		public V remove(Object key) {
			return null;
		}
		@Override
		public boolean remove(Object key, Object value) {
			return false;
		}
	}
}