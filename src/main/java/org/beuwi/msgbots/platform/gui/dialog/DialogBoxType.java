package org.beuwi.msgbots.platform.gui.dialog;

public enum DialogBoxType
{
    APPLICATION,  // 복잡한 형태

    DOCUMENT; // 간단한 형태

    public enum DOCUMENT
    {
        ERROR, WARNING, EVENT
    }
}