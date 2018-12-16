package finaltest_chatsys.chatsys.controller;

import org.springframework.http.server.ServletServerHttpRequest;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/websocket/{userId}")
public class WebSocketEndPoint {
    private static int onlineCount=0;    //在线人数
    private Session session;
    private static Map<String,Session> webSocketMap=new HashMap<String,Session>();
    //建立连接调用方法
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId)
    {
        this.session=session;
        webSocketMap.put(userId,session);
        addOnlineCount();
    }
    //关闭连接调用方法
    @OnClose
    public void onClose(@PathParam("userId")String userId){
        webSocketMap.remove(userId);
        subOnlineCount();
    }
    //接收消息并转发调用方法
    @OnMessage
    public void onMessage(String message,Session session){

    }
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void addOnlineCount(){
        onlineCount++;
    }
    public void subOnlineCount(){
        onlineCount--;
    }
}
