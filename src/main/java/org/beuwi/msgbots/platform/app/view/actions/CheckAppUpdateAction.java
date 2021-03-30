package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.gui.control.ToastItem;
import org.beuwi.msgbots.platform.gui.enums.ToastType;
import org.beuwi.msgbots.platform.util.ReleasedInfo;
import org.beuwi.msgbots.setting.GlobalSettings;

public class CheckAppUpdateAction implements Action {
	@Override
	public void init() {
	}

	public static void execute() {
		try {
			int releasedVersionCode = ReleasedInfo.getVersionCode();
			int currentVersionCode = GlobalSettings.getInt("program:version_code");
			String releasedVersionName = ReleasedInfo.getVersionName();
			String currentVersionName = GlobalSettings.getString("program:version_name");
			// 새로 출시된 버전이 현재 버전보다 높으면
			if (releasedVersionCode > currentVersionCode) {
				// 토스트 메시지 다이얼로그를 띄움
				ShowToastMessageAction.execute(new ToastItem(
					ToastType.EVENT,
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

	@Override
	public String getName() {
		return "check.app.update.action";
	}
}