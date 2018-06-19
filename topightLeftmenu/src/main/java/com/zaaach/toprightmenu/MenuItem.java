package com.zaaach.toprightmenu;

/**
 * Author：Bro0cL on 2016/12/26.
 */

public class MenuItem {
    private int icon;
    private String text;
    private String messageCount;//消息数量

    public MenuItem() {}

    public MenuItem(String text) {
        this.text = text;
    }

    public MenuItem(int iconId, String text) {
        this.icon = iconId;
        this.text = text;
    }


    public MenuItem(int icon, String text, String messageCount) {
        this.icon = icon;
        this.text = text;
        this.messageCount = messageCount;
    }

    public int getIcon() {
        return icon;

}
    public void setIcon(int iconId) {
        this.icon = iconId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }
}
