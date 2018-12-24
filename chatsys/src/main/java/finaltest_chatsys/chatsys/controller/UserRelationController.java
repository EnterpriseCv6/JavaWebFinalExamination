package finaltest_chatsys.chatsys.controller;

import com.alibaba.fastjson.JSONObject;
import finaltest_chatsys.chatsys.entity.Friend;
import finaltest_chatsys.chatsys.entity.User;
import finaltest_chatsys.chatsys.entity.UserRelation;
import finaltest_chatsys.chatsys.service.UserRelationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserRelationController {
    @Resource
    private UserRelationService userRelationService;
    @RequestMapping(value="/searchFriend",method= RequestMethod.POST)
    @ResponseBody
    List<User> searchFriend(@RequestBody JSONObject params){
        String searchid=params.getString("friendid");
        List<User> user=userRelationService.searchFriend(searchid);
        return user;
    }
    @RequestMapping(value="/sendRequest",method= RequestMethod.POST)
    @ResponseBody
    Map<String,Object> sendRequest(@RequestBody JSONObject params){
        UserRelation userRelation=new UserRelation();
        //将信息插入userRelation类（5个，消息序号在mapper中)
        String userIdA=params.getString("userid");
        String userIdB=params.getString("friendid");
        String message=params.getString("msg");
        System.out.println(userIdA+"   "+userIdB+"   "+message);
        userRelation.setTarid(userIdA);
        userRelation.setReqid(userIdB);
        userRelation.setMsg(message);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        userRelation.setRtime(timestamp);
        userRelation.setReqstatus("0");
        String userid=userIdA;
        UserRelation userRelation1=userRelationService.judgeRelation(userid);
        Map<String,Object> result = new HashMap<String,Object>();
        if(userRelation1!=null)
            result.put("result","申请失败");
        else{//查询彼此是否为好友
            userRelationService.insertToUnprocessed(userRelation);
            result.put("result","申请成功");
        }

        return result;
    }
    @RequestMapping(value="/getRequest",method= RequestMethod.POST)
    @ResponseBody
    Map<String,Object> getRequest(@RequestBody JSONObject params){
        String userIdA=params.getString("userid");
        String userIdB=params.getString("friendid");
        UserRelation userRelation=userRelationService.getRequest(userIdA,userIdB);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("tarid",userRelation.getTarid());
        result.put("reqid",userRelation.getReqid());
        result.put("rtime",userRelation.getRtime());
        result.put("msg",userRelation.getMsg());
        result.put("reqgroup",userRelation.getReqgroup());
        result.put("reqstatus",userRelation.getReqstatus());
        return result;
    }
    @RequestMapping(value="/agreeRequest",method= RequestMethod.POST)
    @ResponseBody
    Map<String,Object> agreeRequest(@RequestBody JSONObject params){
        String userid=params.getString("userid");
        String friendid=params.getString("friendid");
        int temp=userRelationService.agreeRequest(userid,friendid);
        Map<String,Object> result = new HashMap<String,Object>();
        if(temp!=0)
            result.put("result","success");
        else
            result.put("result","fail");
        return result;
    }
    @RequestMapping(value="/addFriend",method= RequestMethod.POST)
    @ResponseBody
    Map<String,Object> addFriend(@RequestBody JSONObject params){
        String friendid=params.getString("friendid");
        String friendgroup=params.getString("friendgroup");
        String friendName=params.getString("friendName");
        String userid=params.getString("userid");
        Friend friend=new Friend();
        friend.setFriendid(friendid);
        friend.setFriendGroup(friendgroup);
        friend.setFriendName(friendName);
        int temp;
        Map<String,Object> result = new HashMap<String,Object>();
        temp=userRelationService.addFriend(userid,friend);
        if(temp!=0){
            result.put("result","success");}
        else
            result.put("result","fail");
        return result;
    }
    @RequestMapping(value="/refuseRequest",method= RequestMethod.POST)
    @ResponseBody
    Map<String,Object> refuseRequest(@RequestBody JSONObject params){
        String userid=params.getString("userid");
        String friendid=params.getString("friendid");
        int temp=userRelationService.refuseRequest(userid,friendid);
        Map<String,Object> result = new HashMap<String,Object>();
        if(temp!=0){
            result.put("result","success");}
        else
            result.put("result","fail");
        return result;
    }
    @RequestMapping(value="/checkRequest",method= RequestMethod.POST)
    @ResponseBody
    List<UserRelation> checkRequest(@RequestBody JSONObject params){
        String userid=params.getString("userid");
        String friendid=params.getString("friendid");
        List<UserRelation>list=userRelationService.checkRequest(userid,friendid);
        return list;
    }
    @RequestMapping(value = "/getFriendList",method = RequestMethod.POST)
    @ResponseBody
    String getFriendList(@RequestBody JSONObject param){
        System.out.println("jinu");
        String userId= param.getString("userId");
        List<Friend> list=userRelationService.getFriendList(userId);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("msg",list);
        return jsonObject.toJSONString();
    }
}