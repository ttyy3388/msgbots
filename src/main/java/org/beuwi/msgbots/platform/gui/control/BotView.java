package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.stream.Collectors;

public class BotView extends ListView<GridPane>
{
	public BotView()
	{

	}

	public Bot getBot(int index)
	{
		return (Bot) getItem(index);
	}

	public Bot getBot(String id)
    {
        return (Bot) getItem(id);
    }

    public List<Bot> getBots()
    {
        return getItems().stream().map(bot -> (Bot) bot).collect(Collectors.toList());
    }
}