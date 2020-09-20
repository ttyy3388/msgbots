package org.beuwi.msgbots.compiler.engine;

import org.beuwi.msgbots.compiler.api.ImageDB;
import org.beuwi.msgbots.compiler.api.Replier;

public class ResponseParameters
{
    public String room;
    public String msg;
    public String sender;
    public boolean isGroupChat;
    public Replier replier;
    public ImageDB ImageDB;
    public String packageName;
    
    public ResponseParameters(String room, String msg, String sender, boolean isGroupChat, Replier replier, ImageDB imageDB, String packName)
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