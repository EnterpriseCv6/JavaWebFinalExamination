package finaltest_chatsys.chatsys.controller;

import com.alibaba.fastjson.JSONObject;
import finaltest_chatsys.chatsys.entity.Friend;
import finaltest_chatsys.chatsys.entity.User;
import finaltest_chatsys.chatsys.entity.UserRelation;
import finaltest_chatsys.chatsys.service.UserRelationService;
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
    List<Friend> searchfriend(@RequestBody JSONObject params){
        String friendid=params.getString("friendid");
        String userid=params.getString("userid");
        List<Friend> friend=userRelationService.searchFriend(userid,friendid);
        return friend;
    }
    @RequestMapping(value="/searchUser",method= RequestMethod.POST)
    @ResponseBody
    List<User> searchUser(@RequestBody JSONObject params){
        String searchid=params.getString("friendid");
        List<User> user=userRelationService.searchUser(searchid);
        for(User str :user){
            System.out.println(str.getAddress());
            System.out.println(str.getBirth());
            System.out.println(str.getUsername());
        }
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
        userRelation.setTarid(userIdB);
        userRelation.setReqid(userIdA);
        userRelation.setMsg(message);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        userRelation.setRtime(timestamp);
        userRelation.setReqstatus("0");
        User user=userRelationService.getUserid(userIdA);
        userRelation.setReqname(user.getUsername());
        UserRelation userRelation1=userRelationService.judgeRelation(userIdA);
        Map<String,Object> result = new HashMap<String,Object>();
        if(userRelation1!=null)
            result.put("result","申请失败,该用户已是您的好友！");
        else{//查询彼此是否为好友
            userRelationService.insertToUnprocessed(userRelation);
            result.put("result","申请成功！");
        }

        return result;
    }
    @RequestMapping(value="/getRequest",method= RequestMethod.POST)
    @ResponseBody
    List<UserRelation> getRequest(@RequestBody JSONObject params){
        String userIdA=params.getString("userid");
        String userIdB=params.getString("friendid");
        List<UserRelation> userRelation=userRelationService.getRequest(userIdB,userIdA);
        return userRelation;
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
        String userid=params.getString("userid");
        userRelationService.agreeRequest(friendid,userid);
        User user=userRelationService.getUserid(friendid);
        Friend friend=new Friend();
        friend.setFriendid(friendid);
        friend.setFriendGroup(friendgroup);
        friend.setFriendName(user.getUsername());
        int temp;
        Map<String,Object> result = new HashMap<String,Object>();
        temp=userRelationService.addFriend(userid,friend);
        if(temp!=0){
            result.put("result","添加好友成功！");}
        else
            result.put("result","添加好友失败！");
        return result;
    }
    @RequestMapping(value="/deleteFriend",method= RequestMethod.POST)
    @ResponseBody
    Map<String,Object> deleteFriend(@RequestBody JSONObject params){
        String userid=params.getString("userid");
        String friendid=params.getString("friendid");
        int temp=userRelationService.deleteFriend(userid,friendid);
        Map<String,Object> result = new HashMap<String,Object>();
        if(temp!=0){
            result.put("result","删除成功");}
        else
            result.put("result","删除失败");
        return result;
    }
    @RequestMapping(value="/refuseRequest",method= RequestMethod.POST)
    @ResponseBody
    Map<String,Object> refuseRequest(@RequestBody JSONObject params){
        String userid=params.getString("userid");
        String friendid=params.getString("friendid");
        int temp=userRelationService.refuseRequest(friendid,userid);
        Map<String,Object> result = new HashMap<String,Object>();
        if(temp!=0){
            result.put("result","拒绝成功");}
        else
            result.put("result","拒绝失败");
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