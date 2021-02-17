package org.beuwi.msgbots.platform.util;

import java.io.File;
import java.lang.invoke.MethodHandles;

import javafx.application.Platform;
import javafx.scene.image.Image;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.JSONObject;
import org.beuwi.msgbots.platform.gui.control.Document;
import org.beuwi.msgbots.platform.gui.control.Page;
import org.beuwi.msgbots.setting.GlobalSettings;

// 주의 : 기본 형식이 아닌 오브젝트는 "try catch" 감싸줘야 함
public class SharedValues /* implements java.io.Serializable */ {

	public static final String DEFAULT_PROGRAM_TITLE = "Messenger Bot Simulator";
	public static final Image DEFAULT_PROGRAM_IMAGE;

	public static final String SYSTEM_WINDOWS_VERSION = System.getProperty("windows.os");
	public static final String SYSTEM_USER_NAME = System.getProperty("user.name");

	/* static {
		try {
			SYSTEM_WINDOWS_VERSION;
			SYSTEM_USER_NAME ;
		}
		catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	} */

	// Chrome
	public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";

	/* LINKS */
	public static final String VIEW_LICENSE_LINK = "https://github.com/ttyy3388/msgbots/blob/master/LICENSE";
	public static final String RELEASE_NOTES_LINK = "https://ttyy3388.gitbook.io/messenger-bot-simulator";

	/* public static final ObjectProperty<File> CURRENT_SCRIPT_FILE = new SimpleObjectProperty();
	public static final ObjectProperty<File> getCurrentScriptFileProperty() {
		return CURRENT_SCRIPT_FILE;
	} */

	// Program Info
	public static final String RELEASED_INFO_LINK = "https://ttyy3388.develope.dev/getData.php?type=Msgbots&value=Info";

	/* Paths */
	public static final String MAIN_FOLDER_PATH = System.getProperty("user.dir");
	public static final String DATA_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "data";
	public static final String BOTS_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "bots";

	// public static final String CUSTOM_THEME_PATH = "custom_theme.css";
    public static final String PROFILE_SENDER_PATH = "profile_sender.png";
	public static final String PROFILE_BOT_PATH = "profile_bot.png";
	public static final String GLOBAL_LOG_PATH = "global_log.json";
	public static final String GLOBAL_CONFIG_PATH = "global_config.json";
	public static final String SCRIPT_DEFAULT_PATH = "script_default.js";
	public static final String SCRIPT_UNIFIED_PATH = "script_unified.js";
	public static final String PROGRAM_DATA_PATH = "program_data.json";

	/* Files */
	public static final File MAIN_FOLDER_FILE;
	public static final File DATA_FOLDER_FILE;
	public static final File BOTS_FOLDER_FILE;

	// public static final File CUSTOM_THEME_FILE;
	public static final File PROFILE_SENDER_FILE;
	public static final File PROFILE_BOT_FILE;
	public static final File GLOBAL_LOG_FILE;
	public static final File GLOBAL_CONFIG_FILE;
	public static final File SCRIPT_DEFAULT_FILE;
	public static final File SCRIPT_UNIFIED_FILE;
	public static final File PROGRAM_DATA_FILE;

	// public static final Image PROFILE_SENDER_IMAGE;
	// public static final Image PROFILE_BOT_IMAGE;

	// public static final File BASE_THEME_FILE =  ResourceUtils.getTheme("/themes/base.css");
	// public static final File DARK_THEME_FILE = ResourceUtils.getFile("/themes/dark.css");
	// public static final File LIGHT_THEME_FILE = ResourceUtils.getFile("/themes/light.css");
	// public static final File BLACK_THEME_FILE = ResourceUtils.getFile("/themes/dark.css");
	// public static final File WHITE_THEME_FILE = ResourceUtils.getFile("/themes/dark.css");

	/* Pages */
	public static final Page ABOUT_PROGRAM_PAGE;
	public static final Page PROGRAM_START_PAGE;
	// public static final Page RELEASE_NOTES_PAGE;
	// public static final Page VIEW_LICENSE_PAGE;
	public static final Page WELCOME_GUIDE_PAGE;

	/* Documents */
	public static final Document ABOUT_PROGRAM_DOCUMENT;
	public static final Document PROGRAM_START_DOCUMENT;
	// public static final Document RELEASE_NOTES_DOCUMENT = new Document("RELEASE NOTES", RELEASE_NOTES_PAGE);
	// public static final Document VIEW_LICENSE_DOCUMENT = new Document("VIEW LICENSE", VIEW_LICENSE_PAGE);
	public static final Document WELCOME_GUIDE_DOCUMENT;

	// 주의 : 기본 형식이 아닌 오브젝트는 "try catch" 감싸줘야 함
	static {
		try {
			MAIN_FOLDER_FILE = new File(SharedValues.MAIN_FOLDER_PATH);
			DEFAULT_PROGRAM_IMAGE = ResourceUtils.getImage("program");
			DATA_FOLDER_FILE = new File(SharedValues.DATA_FOLDER_PATH);
			BOTS_FOLDER_FILE = new File(SharedValues.BOTS_FOLDER_PATH);

			// 파일 부분은 가장 먼저 선언되어야함
			// CUSTOM_THEME_FILE = FileManager.getDataFile(CUSTOM_THEME_PATH);
			PROFILE_SENDER_FILE = FileManager.getDataFile(PROFILE_SENDER_PATH);
			PROFILE_BOT_FILE = FileManager.getDataFile(PROFILE_BOT_PATH);
			GLOBAL_LOG_FILE = FileManager.getDataFile(GLOBAL_LOG_PATH);
			GLOBAL_CONFIG_FILE = FileManager.getDataFile(GLOBAL_CONFIG_PATH);
			SCRIPT_DEFAULT_FILE = FileManager.getDataFile(SCRIPT_DEFAULT_PATH);
			SCRIPT_UNIFIED_FILE = FileManager.getDataFile(SCRIPT_UNIFIED_PATH);
			PROGRAM_DATA_FILE = FileManager.getDataFile(PROGRAM_DATA_PATH);

			ABOUT_PROGRAM_PAGE = new Page("about-program-page");
			PROGRAM_START_PAGE = new Page("program-start-page");
			WELCOME_GUIDE_PAGE = new Page("welcome-guide-page");
			// RELEASE_NOTES_PAGE = new Page("release-notes-page");
			// VIEW_LICENSE_PAGE = new Page("view-license-page");

			// PROFILE_SENDER_IMAGE = new Image(SharedValues.PROFILE_SENDER_FILE.toURI().toString());
			// PROFILE_BOT_IMAGE = new Image(SharedValues.PROFILE_BOT_FILE.toURI().toString());

			ABOUT_PROGRAM_DOCUMENT = new Document("ABOUT PROGRAM", ABOUT_PROGRAM_PAGE);
			PROGRAM_START_DOCUMENT = new Document("START PROGRAM", PROGRAM_START_PAGE);
			WELCOME_GUIDE_DOCUMENT = new Document("WELCOME GUIDE", WELCOME_GUIDE_PAGE);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
}