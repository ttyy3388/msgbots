package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.managers.ActiveAreaManager;
import org.beuwi.simulator.platform.application.managers.ActivityBarManager;
import org.beuwi.simulator.platform.application.managers.SideBarManager;

public class SelectActivityButtonAction
{
    private static HBox hoxActiveArea = null;
	private static AnchorPane anpActivityBar = null;
	private static AnchorPane anpSideBar = null;

	private static ToggleButton tgnExplorerPart = null;
	private static ToggleButton tgnSimulationPart = null;

	private static boolean isHided = false;

	public static void initAction()
	{
        hoxActiveArea = ActiveAreaManager.getComponent();
		anpActivityBar = ActivityBarManager.getComponent();
		anpSideBar = SideBarManager.getComponent();

		tgnExplorerPart = (ToggleButton) anpActivityBar.getChildren().get(0);
		tgnSimulationPart = (ToggleButton) anpActivityBar.getChildren().get(1);
	}

	// Selected Button Index
	public static void update(int index)
	{
		switch (index)
		{
			case 0 :

				// 이미 선택한 탭 다시 선택하면
				if (!tgnExplorerPart.isSelected())
				{
                    hoxActiveArea.getChildren().remove(anpSideBar);
                    isHided = true;
				}
				else
				{
				    if (isHided)
                    {
                        hoxActiveArea.getChildren().add(anpSideBar);
                    }

					tgnSimulationPart.setSelected(false);
				}

				break;

			case 1 :

				if (!tgnSimulationPart.isSelected())
				{
                    hoxActiveArea.getChildren().remove(anpSideBar);
                    isHided = true;
				}
				else
				{
                    if (isHided)
                    {
                        hoxActiveArea.getChildren().add(anpSideBar);
                    }

					tgnExplorerPart.setSelected(false);
				}

				break;
		}

		// anpSideBar.getChildren().get(index).setDisable(false);
		// anpSideBar.getChildren().get(index).setVisible(true);
	}
}