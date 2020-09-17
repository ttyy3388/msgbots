package org.beuwi.msgbots.platform.util;

import java.io.File;

public class SharedValues
{
	static
	{

	}
    public static final String PROFILE_USER_FILENAME = "profile_user.png";
	public static final String PROFILE_BOT_FILENAME = "profile_bot.png";
	public static final String GLOBAL_LOG_FILENAME = "global_log.json";
	public static final String GLOBAL_SETTING_FILENAME = "global_setting.json";
	public static final String SCRIPT_DEFAULT_FILENAME = "script_default.js";
	public static final String SCRIPT_UNIFIED_FILENAME = "script_unified.js";

	public static final String FONT_CONSOLAS_FILENAME = "consolas.font";

	// Main Folder : Program Folder
	public static final String MAIN_FOLDER_PATH = System.getProperty("user.dir");
	public static final String DATA_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "data";
	public static final String BOTS_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "bots";
}