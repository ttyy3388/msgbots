package org.beuwi.msgbots.shared;

import javafx.scene.image.Image;

import org.beuwi.msgbots.base.Dfile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

// 주의 : 기본 형식이 아닌 오브젝트는 [try / catch]로 감싸줘야 함
public class SharedValues /* implements java.io.Serializable */ {
	// 초기화 블럭으로 선언하면 우선순위가 낮아지는 듯 함
	private static final SharedMap<String, Object> values = new SharedMap();

	static {
		try {
			/* Program */
			register(false, "program.title", "Messenger Bot Simulator");
			// register(false, "program.image", ResourceUtils.getImage("program"));
			// register(false, "program.theme", GlobalSettings.getString("program.colorTheme"));
			/* Value */
			// 프로그램 너비나 높이도 글로벌 값으로 냅둘건지에 대한건 추후 결정 (현재는 기록만 남김)
			register(false, "program.maxWidth", 0);
			register(false, "program.maxHeight", 0);
			register(false, "program.prefWidth", 0);
			register(false, "program.prefHeight", 0);
			register(false, "program.minWidth", 0);
			register(false, "program.minHeight", 0);

			/* User */
			register(false, "user.os", System.getProperty("windows.os"));
			register(false, "user.name", System.getProperty("user.name"));
			// [Strings.xml]로 옮기기
			register(false, "user.agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");

			/* Link */
			register(false, "link.viewLicense", "https://github.com/ttyy3388/msgbot/blob/master/LICENSE");
			register(false, "link.releaseNotes", "https://ttyy3388.gitbook.io/messenger-bot-simulator");
			register(false, "link.releaseInfo", "https://ttyy3388.develope.dev/getData.php?type=Msgbots&value=Info");// Program Info

			/* Dfile */
			register(true, "dfile.customTheme", new Dfile("custom_theme.css"));
			register(true, "dfile.colorMap", new Dfile("color_map.json"));
			register(true, "dfile.profileSender", new Dfile("profile_sender.png"));
			register(true, "dfile.profileBot", new Dfile("profile_bot.png"));
			register(true, "dfile.globalLog", new Dfile("global_log.json"));
			register(true, "dfile.globalConfig", new Dfile("global_config.json"));
			register(true, "dfile.scriptDefault", new Dfile("script_default.js"));
			register(true, "dfile.scriptUnified", new Dfile("script_unified.js"));

			/* Resource */
			/* register(false, "resource.lightTheme", ResourceUtils.getTheme("light"));
			register(false, "resource.darkTheme", ResourceUtils.getTheme("dark"));
			register(false, "resource.baseTheme", ResourceUtils.getTheme("base")); */
		}
		catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static void register(boolean modifiable, String key, Object value) {
		values.register(modifiable, key, value);
	}

	public static <T> T getValue(String key) {
		return (T) values.get(key);
	}
	public static void setValue(String key, Object value) {
		if (!values.containsKey(key)) {
			throw new NullPointerException("wrong access key");
		}
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
		return null;
	}

	/* public static final ObjectProperty<File> CURRENT_SCRIPT_FILE = new SimpleObjectProperty();
	public static final ObjectProperty<File> getCurrentScriptFileProperty() {
		return CURRENT_SCRIPT_FILE;
	} */

	// 굳이 'SharedMap' 클래스를 생성 안하고도 'SharedValue' 클래스를 만드는 방법도 있음
	private static class SharedMap<K, V> extends HashMap<K, V> {
		// 수정 가능 여부를 저장함
		private final Map<K, Boolean> map = new HashMap<>();

		@Override
		public V get(Object key) {
			// 없는 키를 입력했을 경우
			if (!super.containsKey(key)) {
				// 올바르지 않은 접근
				throw new NullPointerException("wrong access key '" + key + "'");
			}
			else {
				return super.get(key);
			}
		}

		@Override
		public V put(K key, V value) {
			// 기존에 없는 값일 경우
			if (!map.containsKey(key)) {
				throw new NullPointerException("wrong access key '" + key + "'");
			}
			// 기존에 있는 값일 경우
			else {
				boolean modifiable = map.get(key);
				// 수정이 불가능한데 값을 수정하려고 했다면
				if (!modifiable) {
					throw new RuntimeException("cannot assign " + key + " value to final variable " + value);
				}
				// 수정이 가능하다면
				else {
					map.put(key, modifiable);
					super.put(key, value);
				}
			}
			return null;
		}

		// [modifiable]이 [true]라면 [final]처럼 간주함 (값변경 X)
		public void register(boolean modifiable, K key, V value) {
			// 이미 등록되어 있는 경우 중복 오류
			if (map.containsKey(key)) {
				throw new RuntimeException("the '" + key + "' key is already registered");
			}
			else {
				map.put(key, modifiable);
				super.put(key, value);
			}
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