package org.beuwi.simulator.compiler.engine;

import org.beuwi.simulator.compiler.api.ImageDB;

public class ResponseParameters
{

    public String room;
    public String msg;
    public String sender;
    public boolean isGroupChat;
    public ScriptManager.Replier replier;
    public ImageDB ImageDB;
    public String packageName;
    
    public ResponseParameters(String room, String msg, String sender, boolean isGroupChat, ScriptManager.Replier replier, org.beuwi.simulator.compiler.api.ImageDB imageDB, String packName)
    {
        this.room = room;
        this.msg = msg;
        this.sender = sender;
        this.isGroupChat = isGroupChat;
        this.replier = replier;
        this.ImageDB = imageDB;
        this.packageName = packName;
    }
}