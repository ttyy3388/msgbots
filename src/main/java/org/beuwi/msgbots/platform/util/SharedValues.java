package org.beuwi.msgbots.platform.util;

import org.beuwi.msgbots.manager.FileManager;

import java.io.File;

public class SharedValues
{
	public static final String DEFAULT_PROGRAM_TITLE = "Messenger Bot Simulator";

	static {
	}

	/* Paths */

	public static final String MAIN_FOLDER_PATH = System.getProperty("user.dir");
	public static final String DATA_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "data";
	public static final String BOTS_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "bots";

    public static final String PROFILE_USER_PATH = "profile_user.png";
	public static final String PROFILE_BOT_PATH = "profile_bot.png";
	public static final String GLOBAL_LOG_PATH = "global_log.json";
	public static final String GLOBAL_SETTING_PATH = "global_setting.json";
	public static final String SCRIPT_DEFAULT_PATH = "script_default.js";
	public static final String SCRIPT_UNIFIED_PATH = "script_unified.js";


	/* Files */

	public static final File MAIN_FOLDER_FILE = new File(SharedValues.MAIN_FOLDER_PATH);
	public static final File DATA_FOLDER_FILE = new File(SharedValues.DATA_FOLDER_PATH);
	public static final File BOTS_FOLDER_FILE = new File(SharedValues.BOTS_FOLDER_PATH);

	public static final File PROFILE_USER_FILE = FileManager.getDataFile(PROFILE_USER_PATH);
	public static final File PROFILE_BOT_FILE = FileManager.getDataFile(PROFILE_BOT_PATH);
	public static final File GLOBAL_LOG_FILE = FileManager.getDataFile(GLOBAL_LOG_PATH);
	public static final File GLOBAL_SETTING_FILE = FileManager.getDataFile(GLOBAL_SETTING_PATH);
	public static final File SCRIPT_DEFAULT_FILE = FileManager.getDataFile(SCRIPT_DEFAULT_PATH);
	public static final File SCRIPT_UNIFIED_FILE = FileManager.getDataFile(SCRIPT_UNIFIED_PATH);
}