package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;

/**
 * WebSocket message model
 * WebScoket聊天消息类
 */

public class Message {
    // TODO: add message model.
    public static final String ENTER = "ENTER";
    public static final String CHAT = "CHAT";
    public static final String LEAVE = "LEAVE";
    private String type; //消息类型
    private String username; //发送人
    private String msg; //发送的消息
    private int onlineCount; //在线用户数

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getMsg() {
        return msg;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public Message(String type, String username, String msg, int onlineCount) {
        this.type = type;
        this.username = username;
        this.msg = msg;
        this.onlineCount = onlineCount;
    }

    public static String jsonStr(String type, String username, String msg, int onlineCount) {
        return JSON.toJSONString(new Message(type, username, msg, onlineCount));
    }

}
