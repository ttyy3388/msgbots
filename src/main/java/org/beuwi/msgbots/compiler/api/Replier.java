package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.platform.app.view.actions.AddChatMessageAction;
import org.mozilla.javascript.annotations.JSStaticFunction;

public class Replier
{
    @JSStaticFunction
    public static Boolean reply(String message)
    {
        AddChatMessageAction.execute(message, true);

        return true;
    }

    @JSStaticFunction
    public static Boolean reply(String room, String message, Boolean hideToast)
    {
        AddChatMessageAction.execute(message, true);

        return true;
    }
}