package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.managers.ActivityBarManager;
import org.beuwi.simulator.platform.application.managers.SideBarManager;

// Change : 다른 버튼을 선택하면 Change
// Hide   : 이미 선택된 버튼을 다시 클릭 시
// Show   : 숨김 상태에서 버튼 클릭 시

public class SelectActivityButtonAction
{
	private static AnchorPane anpActivityBar = null;
	private static AnchorPane anpSideBar = null;

	public static void initAction()
	{
		anpActivityBar = ActivityBarManager.getComponent();
		anpSideBar = SideBarManager.getComponent();
	}

	public static void update(int index)
	{

	}
}