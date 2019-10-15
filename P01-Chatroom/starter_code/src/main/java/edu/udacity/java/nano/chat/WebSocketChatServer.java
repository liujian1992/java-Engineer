package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat") //标记此类为服务端
public class WebSocketChatServer {

    /**
     * 全部在线会话 PS：基于场景考虑，这里使用线程安全的Map存储会话对象
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    /**
     * 公共方法，将消息发给所有接受者
     *
     * @param msg
     */
    private static void sendMessageToAll(String msg) {
        //TODO: add send message method.
        onlineSessions.forEach((id, session) -> {
            try {
                //发送消息
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Open connection, 1) add session, 2) add user.
     * 当客户端打开连接：1.添加会话对象
     */
    @OnOpen
    public void onOpen(Session session) {
        //TODO: add on open connection.
        onlineSessions.put(session.getId(), session);
        sendMessageToAll(Message.jsonStr(Message.ENTER, "", "", onlineSessions.size()));
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     * 当客户端发送消息: 1)获取它的用户名和消息 2）将消息发给所有人
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.
        Message message = JSON.parseObject(jsonStr, Message.class);
        sendMessageToAll(Message.jsonStr(Message.CHAT, message.getUsername(), message.getMsg(), message.getOnlineCount()));

    }

    /**
     * Close connection, 1) remove session, 2) update user.
     * 当关闭连接时: 1)移除会话对象 2.更新在线人数
     */
    @OnClose
    public void onClose(Session session) {
        //TODO: add close connection.
        onlineSessions.remove(session.getId());
        sendMessageToAll(Message.jsonStr(Message.LEAVE, "", "", onlineSessions.size()));
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
