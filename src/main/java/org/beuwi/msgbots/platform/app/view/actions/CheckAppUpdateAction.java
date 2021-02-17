package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.dialogs.UpdateAppDialog;
import org.beuwi.msgbots.platform.util.ProgramInfo;
import org.beuwi.msgbots.platform.util.ReleasedInfo;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.setting.GlobalSettings;

public class CheckAppUpdateAction implements Action {
	@Override
	public void init() {
	}

	public static void execute() {
		int releasedVersionCode = ReleasedInfo.getVersionCode();
		int currentVersionCode = GlobalSettings.getInt("program:version_code");
		// 새로 출시된 버전이 현재 버전보다 높으면
		if (releasedVersionCode > currentVersionCode) {
			// 업데이트 다이얼로그를 띄움
			OpenDialogBoxAction.execute(new UpdateAppDialog());
		}
	}

	@Override
	public String getName() {
		return "check.app.update.action";
	}
}