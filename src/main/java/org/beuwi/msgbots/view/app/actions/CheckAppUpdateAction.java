package org.beuwi.msgbots.view.app.actions;

import org.beuwi.msgbots.base.JObject;
import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.base.type.ToastType;
import org.beuwi.msgbots.manager.WebManager;
import org.beuwi.msgbots.setting.GlobalSettings;
import org.beuwi.msgbots.shared.SharedValues;
import org.beuwi.msgbots.view.gui.control.ToastItem;

public class CheckAppUpdateAction implements Executor {
	private static CheckAppUpdateAction instance = null;

	public void execute() {
		try {
			final JObject data = new JObject(WebManager.getText(SharedValues.getString("link.releasedInfo")));
			int releasedVersionCode = data.getInt("version_code");
			String releasedVersionName = data.getString("version_name");
			int currentVersionCode = GlobalSettings.getInt("program.versionCode");
			String currentVersionName = GlobalSettings.getString("program.versionName");
			// 새로 출시된 버전이 현재 버전보다 높으면
			if (releasedVersionCode > currentVersionCode) {
				// 토스트 메시지를 띄움
				ShowToastMessageAction.getInstance().execute(new ToastItem(
					ToastType.INFO,
					"Update Available",
					"Released Version : " + releasedVersionName + "\n" +
							"Current Version : " + currentVersionName + "\n"
				));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static CheckAppUpdateAction getInstance() {
		if (instance == null) {
			instance = new CheckAppUpdateAction();
		}
		return instance;
	}
}