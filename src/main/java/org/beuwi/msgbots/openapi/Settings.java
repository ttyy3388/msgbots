package org.beuwi.msgbots.openapi;

import java.util.Map;

public abstract class Settings extends JsonObject {

    public void putString(String type, String data)
    {
        super.put(type, data); apply();
    }

    public void putBoolean(String type, Boolean data)
    {
        super.put(type, data); apply();
    }

    public void putInteger(String type, Integer data)
    {
        super.put(type, data); apply();
    }

    public void putMap(String type, Map data)
    {
        super.put(type, data); apply();
    }

    public abstract void apply();
}