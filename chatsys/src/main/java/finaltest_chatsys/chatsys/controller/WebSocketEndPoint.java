package finaltest_chatsys.chatsys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.deploy.net.BasicHttpRequest;
import com.sun.deploy.net.HttpRequest;
import finaltest_chatsys.chatsys.dao.MyConfigurator;
import finaltest_chatsys.chatsys.vo.Info;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/websocket/{userId}",configurator = MyConfigurator.class)
public class WebSocketEndPoint {
    private static int onlineCount=0;    //在线人数
    private Session session;
    private static Map<String,Session> webSocketMap=new HashMap<String,Session>();
    private static Map<String,String> userIdMap=new HashMap<String,String>();
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
    public void onMessage(String message,Session session,@PathParam("userId")String userId){
        JSONObject json= JSON.parseObject(message);
        Info info =new Info();
        info.setId(json.getString("id"));
        info.setInfoContent(json.getString("content"));
        info.setTime(json.getString("time"));
        String type=json.getString("type");
        JSONObject json1=new JSONObject();
        if(type.equals("1")){
            if(webSocketMap.containsKey(info.getId())){
                Session session1=webSocketMap.get(info.getId());
                if(userIdMap.containsKey(info.getId()))
                {
                    if(userIdMap.get(info.getId()).equals(userId)){
                        info.setId(userId);
                        json1.put("msg",info);
                        json1.put("type","1");
                        sendMsg(session1,json1);
                        //缺少插入数据库
                    }
                    else{
                        info.setId(userId);
                        info.setInfoContent("");
                        json1.put("msg",info);
                        json1.put("type","2");
                        sendMsg(session1,json1);
                        //缺少插入数据库
                    }
                }else{
                    //缺少插入数据库
                }

            }
        }
        else if(type.equals("4")){
            userIdMap.put(userId,info.getId());
        }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    //发送信息
    public void sendMsg(Session session,JSONObject jsonObject){
        session.getAsyncRemote().sendText(jsonObject.toJSONString());
    }

    public void addOnlineCount(){
        onlineCount++;
    }
    public void subOnlineCount(){
        onlineCount--;
    }
}
