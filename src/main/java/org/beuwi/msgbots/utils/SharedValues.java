package org.beuwi.msgbots.utils;

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
			values.put(false, "program.title", "Messenger Bot Simulator");
			values.put(false, "program.image", ResourceUtils.getImage("program"));
			// values.put(false, "program.theme", GlobalSettings.getString("program.colorTheme"));
			/* Value */
			// 프로그램 너비나 높이도 글로벌 값으로 냅둘건지에 대한건 추후 결정 (현재는 기록만 남김)
			values.put(false, "program.maxWidth", 0);
			values.put(false, "program.maxHeight", 0);
			values.put(false, "program.prefWidth", 0);
			values.put(false, "program.prefHeight", 0);
			values.put(false, "program.minWidth", 0);
			values.put(false, "program.minHeight", 0);

			/* User */
			values.put(false, "user.os", System.getProperty("windows.os"));
			values.put(false, "user.name", System.getProperty("user.name"));
			// [Strings.xml]로 옮기기
			values.put(false, "user.agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");

			/* Link */
			values.put(false, "link.viewLicense", "https://github.com/ttyy3388/msgbot/blob/master/LICENSE");
			values.put(false, "link.releaseNotes", "https://ttyy3388.gitbook.io/messenger-bot-simulator");
			values.put(false, "link.releaseInfo", "https://ttyy3388.develope.dev/getData.php?type=Msgbots&value=Info");// Program Info

			/* Path */
			values.put(false, "path.mainFolder", System.getProperty("user.dir"));
			values.put(false, "path.dataFolder", System.getProperty("user.dir") + File.separator + "data");
			values.put(true, "path.botFolder", System.getProperty("user.dir") + File.separator + "bots"); // 봇 폴더 커스텀 가능
			values.put(true, "path.saveFolder", System.getProperty("user.dir") + File.separator + "save"); // 세이브 폴더 커스텀 가능

			/* File */
			values.put(false, "file.mainFolder", new File(getString("path.mainFolder"))); // 프로그램 폴더 경로 강제
			values.put(false, "file.dataFolder", new File(getString("path.dataFolder"))); // 데이터 폴더 경로 강제
			values.put(true, "file.botFolder", new File(getString("path.botFolder"))); // 봇 폴더 커스텀 가능
			values.put(true, "file.saveFolder", new File(getString("path.saveFolder"))); // 세이브 폴더 커스텀 가능

			/* Dfile */
			values.put(true, "dfile.customTheme", new Dfile("custom_theme.css"));
			values.put(true, "dfile.colorMap", new Dfile("color_map.json"));
			values.put(true, "dfile.senderProfile", new Dfile("profile_sender.png"));
			values.put(true, "dfile.botProfile", new Dfile("profile_bot.png"));
			values.put(true, "dfile.globalLog", new Dfile("global_log.json"));
			values.put(true, "dfile.globalConfig", new Dfile("global_config.json"));
			values.put(true, "dfile.defaultScript", new Dfile("script_default.js"));
			values.put(true, "dfile.unifiedScript", new Dfile("script_unified.js"));

			/* Resource */
			/* values.put(false, "resource.lightTheme", ResourceUtils.getTheme("light"));
			values.put(false, "resource.darkTheme", ResourceUtils.getTheme("dark"));
			values.put(false, "resource.baseTheme", ResourceUtils.getTheme("base")); */
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