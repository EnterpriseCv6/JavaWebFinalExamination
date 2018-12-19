package finaltest_chatsys.chatsys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import finaltest_chatsys.chatsys.entity.Info;
import finaltest_chatsys.chatsys.service.messageGetService;
import org.springframework.stereotype.Component;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint(value = "/WebSocket/{userId}")
public class WebSocketEndPoint {
    private static int onlineCount=0;    //在线人数
    private static Map<String,Session> webSocketMap=new HashMap<String,Session>();
    private static Map<String,String> userIdMap=new HashMap<String,String>();
    //建立连接调用方法
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId)
    {
        System.out.println(userId+"已上线");
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
        //将前端传输的序列化json化为对象
        JSONObject json= JSON.parseObject(message);
        System.out.println(message);
        messageGetService messageGetService=new messageGetService();
        Info info =new Info();
        info.setId(json.getString("id"));
        info.setInfoContent(json.getString("content"));
        System.out.println(userId+"发送了"+info.getInfoContent());
        info.setTime(json.getString("time"));
        String type=json.getString("type");
        //用于消息传输到对象客户端的json
        JSONObject json1=new JSONObject();
        //如果为聊天消息
        if(type.equals("1")){
            //查看对象好友是否在线
            //如果在线
            if(webSocketMap.containsKey(info.getId())){
                //获取好友的session
                Session session1=webSocketMap.get(info.getId());
                //查看用户当前的聊天对象
                if(userIdMap.containsKey(info.getId()))
                {
                    //如果用户当前聊天对象为该好友
                    if(userIdMap.get(info.getId()).equals(userId)){
                        //将消息存储到聊天记录的数据库中
                        //将消息发送给好友的客户端
                        messageGetService.insertToLog(info,userId);
                        info.setId(userId);
                        json1.put("msg",info);
                        json1.put("type","1");
                        sendMsg(session1,json1);
                    }
                    //如果不为该好友
                    else{
                        //将消息存入未处理消息表
                        //给好友客户端发送提示
                        messageGetService.insertToUnprocessed(info,userId);
                        info.setId(userId);
                        info.setInfoContent("");
                        json1.put("msg",info);
                        json1.put("type","2");
                        sendMsg(session1,json1);
                    }
                }else{
                    //如果好友不在线则将消息存入未处理消息表
                    messageGetService.insertToLog(info,userId);
                }

            }
        }
        //如果为用户当前聊天对象的id
        else if(type.equals("5")){
            //将对象的id加入图中
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
