package org.beuwi.simulator.platform.ui.editor;

import javafx.collections.ListChangeListener;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import org.beuwi.simulator.platform.application.views.actions.CloseEditorTabAction;
import org.beuwi.simulator.platform.application.views.actions.SelectEditorTabAction;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class IEditorPane extends ITabPane
{
	public IEditorPane()
	{
		this.setTabMinHeight(24);
		this.setTabMaxHeight(30);

		EditorAreaPart.setSelectedPane(this);

		this.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
		{
			EditorAreaPart.setSelectedPane(this);
		});

		this.setOnKeyPressed(event ->
		{
			if (event.isControlDown())
			{
				switch (event.getCode())
				{
					case W  :
					case F4 : CloseEditorTabAction.update(this); break;
				}
			}

			if (event.isAltDown())
			{
				switch (event.getCode())
				{
					case LEFT : SelectEditorTabAction.update(this, Side.LEFT); break;
					case RIGHT : SelectEditorTabAction.update(this, Side.RIGHT); break;
				}
			}
		});

		getTabs().addListener((ListChangeListener.Change<? extends Tab> change) ->
		{
			while (change.next())
			{
				// 탭이 모두 닫히면 에디터 닫음
				if (this.getTabs().isEmpty())
				{
					EditorAreaPart.getComponent().getItems().remove(this);
				}
			}
		});
	}
}