package org.beuwi.msgbots.platform.util;

import java.io.File;

import javafx.scene.image.Image;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.gui.control.Document;
import org.beuwi.msgbots.platform.gui.control.Page;

public class SharedValues
{
	public static final String DEFAULT_PROGRAM_TITLE = "Messenger Bot Simulator";
	public static final Image DEFAULT_PROGRAM_IMAGE = ResourceUtils.getImage("program");

	// public static final String USER_WINDOWS_VERSION = System.getProperty("windows.os");

	static {
	}

	public static final String SYSTEM_USER_NAME = System.getProperty("user.name");

	/* Pages */
	public static final Page ABOUT_PROGRAM_PAGE = new Page("about-program-page");
	public static final Page PROGRAM_START_PAGE = new Page("program-start-page");
	public static final Page RELEASE_NOTES_PAGE = new Page("release-notes-page");
	public static final Page VIEW_LICENSE_PAGE = new Page("view-license-page");
	public static final Page WELCOME_GUIDE_PAGE = new Page("welcome-guide-page");

	/* Documents */
	public static final Document ABOUT_PROGRAM_DOCUMENT = new Document("ABOUT PROGRAM", ABOUT_PROGRAM_PAGE);
	public static final Document PROGRAM_START_DOCUMENT = new Document("START PROGRAM", PROGRAM_START_PAGE);
	// public static final Document RELEASE_NOTES_DOCUMENT = new Document("RELEASE NOTES", RELEASE_NOTES_PAGE);
	public static final Document VIEW_LICENSE_DOCUMENT = new Document("VIEW LICENSE", VIEW_LICENSE_PAGE);
	public static final Document WELCOME_GUIDE_DOCUMENT = new Document("WELCOME GUIDE", WELCOME_GUIDE_PAGE);

	public static final String RELEASE_NOTES_LINK = "https://blog.naver.com/PostList.nhn?blogId=ttyy3388&from=postList&categoryNo=27";

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

	/* Files */
	public static final File MAIN_FOLDER_FILE = new File(SharedValues.MAIN_FOLDER_PATH);
	public static final File DATA_FOLDER_FILE = new File(SharedValues.DATA_FOLDER_PATH);
	public static final File BOTS_FOLDER_FILE = new File(SharedValues.BOTS_FOLDER_PATH);

	public static final File PROFILE_USER_FILE = FileManager.getDataFile(PROFILE_USER_PATH);
	public static final File PROFILE_BOT_FILE = FileManager.getDataFile(PROFILE_BOT_PATH);
	public static final File GLOBAL_LOG_FILE = FileManager.getDataFile(GLOBAL_LOG_PATH);
	public static final File GLOBAL_CONFIG_FILE = FileManager.getDataFile(GLOBAL_CONFIG_PATH);
	public static final File SCRIPT_DEFAULT_FILE = FileManager.getDataFile(SCRIPT_DEFAULT_PATH);
	public static final File SCRIPT_UNIFIED_FILE = FileManager.getDataFile(SCRIPT_UNIFIED_PATH);

	public static final File DARK_THEME_FILE = ResourceUtils.getFile("/themes/dark.css");
	public static final File LIGHT_THEME_FILE = ResourceUtils.getFile("/themes/light.css");
	// public static final File BLACK_THEME_FILE = ResourceUtils.getFile("/themes/dark.css");
	// public static final File WHITE_THEME_FILE = ResourceUtils.getFile("/themes/dark.css");
}