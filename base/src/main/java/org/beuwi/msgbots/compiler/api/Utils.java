package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.setting.GlobalSettings;

import org.beuwi.msgbots.shared.SharedValues;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.annotations.JSFunction;

public class Utils extends ScriptableObject {
	protected static String toScriptName(String inputName) {
		return FileManager.getBaseName(inputName);
	}

    @Override
    public String getClassName() {
        return "Utils";
    }

    public Utils(ScriptableObject object) {
		super(object, object.getPrototype());
	}

	@JSFunction
	public String getWebText(String url) {
		try {
			return Jsoup.connect(url)
					.ignoreContentType(true)
					.timeout(GlobalSettings.getInt("debug.htmlLoadTimeout"))
					.userAgent(SharedValues.getString("user.agent"))
					.referrer("https://www.google.com")
					.get().toString();
		}
		catch (Exception e) {
			Context.reportError(e.toString());
		}
		return null;
	}

	@JSFunction
	public Document parse(String url) {
		try {
			return Jsoup.connect(url)
					.ignoreContentType(true)
					.timeout(GlobalSettings.getInt("debug.htmlLoadTimeout"))
					.userAgent(SharedValues.getString("user.agent"))
					.referrer("https://www.google.com")
					.get();
		}
		catch (Exception e) {
			Context.reportError(e.toString());
		}
		return null;
	}

	@JSFunction
	public Boolean isUndefined(Object value) {
		return Undefined.isUndefined(value);
	}

	@JSFunction
	public int getAndroidVersionCode() {
		return 28;
	}

	@JSFunction
	public String getAndroidVersionName() {
		return "9";
	}

	@JSFunction
	public String getPhoneBrand() {
		return "samsung";
	}

	@JSFunction
	public String getPhoneModel() {
		return "greatlteks";
	}
}