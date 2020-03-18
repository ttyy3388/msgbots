package org.beuwi.simulator.platform.application.actions;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.managers.ActivityBarManager;
import org.beuwi.simulator.platform.application.managers.SideBarManager;

// Change : 다른 버튼을 선택하면 Change
// Hide   : 이미 선택된 버튼을 다시 클릭 시
// Show   : 숨김 상태에서 버튼 클릭 시

public class SelectActivityButtonAction
{
	private static HBox       hoxLeftArea = null;
	private static AnchorPane anpActivityBar = null;
	private static AnchorPane anpSideBar = null;

	private static AnchorPane savedSideBar = null;

	public static void initAction()
	{
		anpActivityBar = ActivityBarManager.getComponent();
		anpSideBar = SideBarManager.getComponent();
		// hoxLeftArea = (HBox) anpActivityBar.getParent();
		System.out.println(anpActivityBar.getParent());
	}

	// getSelectedButtonIndex
	private static int getSelectedIndex()
	{
		ObservableList<Node> children = anpActivityBar.getChildren();

		for (int index = 0 ; index < children.size() ; index ++)
		{
			if (((ToggleButton) children.get(index)).isSelected())
			{
				return index;
			}
		}

		return -1;
	}

	public static void update(int index)
	{
		int selectedIndex = getSelectedIndex();

		AnchorPane targetTab = (AnchorPane) anpSideBar.getChildren().get(index);

		// 선택된 탭이 없다면
		if (selectedIndex == -1)
		{
			// 선택된 탭 보여줌
			hoxLeftArea.getChildren().add(savedSideBar);
			savedSideBar = null;
		}
		// 이미 선택된 탭이 있다면
		else
		{
			AnchorPane selectedTab = (AnchorPane) anpSideBar.getChildren().get(selectedIndex);

			// 이미 선택된 탭이랑 선택된 탭이랑 다르다면
			if (selectedTab != targetTab)
			{
				// 원래 선택된 탭을 숨기고, 새로 선택된 탭을 보여줌
				selectedTab.setDisable(false);
				selectedTab.setVisible(false);

				targetTab.setDisable(true);
				targetTab.setVisible(true);
			}
			// 같다면
			else
			{
				//  Side Bar 숨김
				savedSideBar = selectedTab;
				hoxLeftArea.getChildren().remove(selectedTab);
			}
		}
	}
}