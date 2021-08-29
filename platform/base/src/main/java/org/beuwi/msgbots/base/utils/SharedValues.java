package org.beuwi.msgbots.base.utils;

import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

// 주의 : 기본 형식이 아닌 오브젝트는 [try / catch]로 감싸줘야 함
public class SharedValues /* implements java.io.Serializable */ {
	// 초기화 블럭으로 선언하면 우선순위가 낮아지는 듯 함
	public static final SharedMap<String, Object> values = new SharedMap();
	static {
		try {

			values.put(false, "PROGRAM_TITLE", "Messenger Bot Simulator");
			values.put(false, "PROGRAM_IMAGE", ResourceUtils.getImage("program"));
			// values.put(false, "PROGRAM_THEME", GlobalSettings.getString("program.colorTheme"));

			values.put(false, "WINDOWS_VERSION", System.getProperty("windows.os"));
			values.put(false, "USER_NAME", System.getProperty("user.name"));

			/* Agent */ // [Strings.xml]로 옮기기
			values.put(false, "USER_AGENT", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");

			/* Links */
			values.put(false, "VIEW_LICENSE_LINK", "https://github.com/ttyy3388/msgbot/blob/master/LICENSE");
			values.put(false, "RELEASE_NOTES_LINK", "https://ttyy3388.gitbook.io/messenger-bot-simulator");
			values.put(false, "RELEASED_INFO_LINK", "https://ttyy3388.develope.dev/getData.php?type=Msgbots&value=Info");// Program Info

			/* Paths */
			values.put(false, "MAIN_FOLDER_PATH", System.getProperty("user.dir"));
			values.put(false, "DATA_FOLDER_PATH", System.getProperty("user.dir") + File.separator + "data");
			values.put(true, "BOT_FOLDER_PATH", System.getProperty("user.dir") + File.separator + "bots"); // 봇 폴더 커스텀 가능
			values.put(true, "SAVE_FOLDER_PATH", System.getProperty("user.dir") + File.separator + "save"); // 세이브 폴더 커스텀 가능

			/* Files */
			values.put(false, "MAIN_FOLDER_FILE", new File(getString("MAIN_FOLDER_PATH"))); // 프로그램 폴더 경로 강제
			values.put(false, "DATA_FOLDER_FILE", new File(getString("DATA_FOLDER_PATH"))); // 데이터 폴더 경로 강제
			values.put(true, "BOT_FOLDER_FILE", new File(getString("BOT_FOLDER_PATH"))); // 봇 폴더 커스텀 가능
			values.put(true, "SAVE_FOLDER_FILE", new File(getString("SAVE_FOLDER_PATH"))); // 세이브 폴더 커스텀 가능
			// 아래 파일들은 유저가 값을 변경할 적이 없다면 초기값은 [null]임
			// ㄴ 초기값은 리소스에 내두고 파일 변경 시 데이터 폴더에 생성하는 방식으로 했었으나
			// ㄴ 그렇게 하면 곳곳에 예외에 관한 코드를 작성해야 하기에 그렇게 까지 할 필요는 없을 거 같아 기존 방식 이용
			// ㄴ 따라서 "04월 03일"부터 임시로 데이터 폴더에 처음부터 생성된 상태로 내뒀음
			// ㄴ "06월 01일" 이후로 [Dfile] 클래스를 만들어서 문제점들을 해결함
			values.put(true, "CUSTOM_THEME_DFILE", new Dfile("custom_theme.css"));
			values.put(true, "COLOR_MAP_DFILE", new Dfile("color_map.json"));
			values.put(true, "PROFILE_SENDER_DFILE", new Dfile("profile_sender.png"));
			values.put(true, "PROFILE_BOT_DFILE", new Dfile("profile_bot.png"));
			values.put(true, "GLOBAL_LOG_DFILE", new Dfile("global_log.json"));
			values.put(true, "GLOBAL_CONFIG_DFILE", new Dfile("global_config.json"));
			values.put(true, "SCRIPT_DEFAULT_DFILE", new Dfile("script_default.js"));
			values.put(true, "SCRIPT_UNIFIED_DFILE", new Dfile("script_unified.js"));

			/* Values */
			// 프로그램 너비나 높이도 글로벌 값으로 냅둘건지에 대한건 추후 결정 (현재는 기록만 남김)
			values.put(false, "PROGRAM_MAX_WIDTH", 0);
			values.put(false, "PROGRAM_MAX_HEIGHT", 0);
			values.put(false, "PROGRAM_PREF_WIDTH", 0);
			values.put(false, "PROGRAM_PREF_HEIGHT", 0);
			values.put(false, "PROGRAM_MIN_WIDTH", 0);
			values.put(false, "PROGRAM_MIN_HEIGHT", 0);

			/* Resources */
			/* values.put(false, "ABOUT_PROGRAM_PAGE", ResourceUtils.getHtml("about-program-page"));
			values.put(false, "PROGRAM_START_PAGE", ResourceUtils.getHtml("program-start-page"));
			values.put(false, "RELEASE_NOTES_PAGE", ResourceUtils.getHtml("release-notes-page"));
			values.put(false, "VIEW_LICENSE_PAGE", ResourceUtils.getHtml("view-license-page"));
			values.put(false, "WELCOME_GUIDE_PAGE", ResourceUtils.getHtml("welcome-guide-page"));

			values.put(false, "LIGHT_THEME", ResourceUtils.getTheme("light"));
			values.put(false, "DARK_THEME", ResourceUtils.getTheme("dark"));
			values.put(false, "BASE_THEME", ResourceUtils.getTheme("base")); */
		}
		catch (Exception e) {
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
	public static Dfile getDfile(String key) {
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

	private static class SharedMap<K, V> extends HashMap<K, V> {
		// 수정 가능 여부를 저장함
		private final Map<K, Boolean> map = new HashMap<>();

		@Override
		public V get(Object key) {
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

		// [modifiable]이 [true]라면 [final]처럼 간주함 (값변경 X)
		public V put(boolean modifiable, K key, V value) {
			// 기존에 있는 값일 경우
			if (map.containsKey(key)) {
				boolean check = Boolean.TRUE.equals(map.get(key));
				// 수정이 불가능한데 값을 수정하려고 했다면
				if (!check) {
					throw new RuntimeException("cannot assign " + key + " value to final variable " + value);
				}
				// 수정이 가능하다면
				else {
					map.put(key, modifiable);
					super.put(key, value);
				}
			}
			// 새 값일 경우
			else {
				map.put(key, modifiable);
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