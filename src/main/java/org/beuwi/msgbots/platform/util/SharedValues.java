package org.beuwi.msgbots.platform.util;

import java.io.File;

import javafx.scene.image.Image;

public class SharedValues {
	public static final String DEFAULT_PROGRAM_TITLE = "Messenger Bot Simulator";
	public static final Image DEFAULT_PROGRAM_IMAGE = ResourceUtils.getImage("program");

	// public static final String USER_WINDOWS_VERSION = System.getProperty("windows.os");

	static {
		// 정적 변수에서 초기화(파일이 없다거나 등...) 에러가 발생하면 노답이기 때문에 최대한 모든 에러를 잡아서 해결해 줘야 함
		/* try {
			new SharedValues();
		} catch (Throwable t) {
			t.printStackTrace(System.err);
		} */
	}

	public static final String SYSTEM_USER_NAME = System.getProperty("user.name");

	/* public static final ObjectProperty<File> CURRENT_SCRIPT_FILE = new SimpleObjectProperty();
	public static final ObjectProperty<File> getCurrentScriptFileProperty() {
		return CURRENT_SCRIPT_FILE;
	} */

	/* Paths */
	public static final String MAIN_FOLDER_PATH = System.getProperty("user.dir");
	public static final String DATA_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "data";
	public static final String BOTS_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "bots";

    public static final String PROFILE_USER_PATH = "profile_user.png";
	public static final String PROFILE_BOT_PATH = "profile_bot.png";
	public static final String GLOBAL_LOG_PATH = "global_log.json";
	public static final String GLOBAL_CONFIG_PATH = "global_config.json";
	public static final String SCRIPT_DEFAULT_PATH = "script_default.js";
	public static final String SCRIPT_UNIFIED_PATH = "script_unified.js";
	public static final String PROGRAM_DATA_PATH = "program_data.json";

	/* Files */
	public static final File MAIN_FOLDER_FILE = new File(SharedValues.MAIN_FOLDER_PATH);
	public static final File DATA_FOLDER_FILE = new File(SharedValues.DATA_FOLDER_PATH);
	public static final File BOTS_FOLDER_FILE = new File(SharedValues.BOTS_FOLDER_PATH);

	public static final File PROFILE_USER_FILE = ResourceUtils.getData(PROFILE_USER_PATH);
	public static final File PROFILE_BOT_FILE = ResourceUtils.getData(PROFILE_BOT_PATH);
	public static final File GLOBAL_LOG_FILE = ResourceUtils.getData(GLOBAL_LOG_PATH);
	public static final File GLOBAL_CONFIG_FILE = ResourceUtils.getData(GLOBAL_CONFIG_PATH);
	public static final File SCRIPT_DEFAULT_FILE = ResourceUtils.getData(SCRIPT_DEFAULT_PATH);
	public static final File SCRIPT_UNIFIED_FILE = ResourceUtils.getData(SCRIPT_UNIFIED_PATH);
	public static final File PROGRAM_DATA_FILE = ResourceUtils.getData(PROGRAM_DATA_PATH);

	public static final File BASE_THEME_FILE =  ResourceUtils.getFile("/themes/base.css");
	public static final File DARK_THEME_FILE = ResourceUtils.getFile("/themes/dark.css");
	// public static final File LIGHT_THEME_FILE = ResourceUtils.getFile("/themes/light.css");
	public static final File USER_THEME_FILE = ResourceUtils.getFile("/themes/user.css");
	// public static final File BLACK_THEME_FILE = ResourceUtils.getFile("/themes/dark.css");
	// public static final File WHITE_THEME_FILE = ResourceUtils.getFile("/themes/dark.css");
}