package finaltest_chatsys.chatsys.service;

import finaltest_chatsys.chatsys.entity.Friend;
import finaltest_chatsys.chatsys.entity.User;
import finaltest_chatsys.chatsys.entity.UserRelation;
import finaltest_chatsys.chatsys.mapper.UserRelationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRelationService {
    @Resource
    private UserRelationMapper userRelationMapper;
    public List<User> searchFriend(String friendid){
        return userRelationMapper.searchfriend(friendid);
    }
    public  void insertToUnprocessed(UserRelation userRelation){
        userRelationMapper.insertToUnprocessed(userRelation);
    }
    public UserRelation judgeRelation(String userId){
        return userRelationMapper.judgeRelation(userId);
    }
    public UserRelation getRequest(String traid,String reqid){
        return userRelationMapper.getRequest(traid,reqid);
    }
   public List<UserRelation> getFriendgroup(String userid, String friendid){
        return userRelationMapper.getFriendgroup(userid,friendid);
    }
    public int addFriend(String userid, Friend friend){
        return userRelationMapper.addFriend(userid,friend);
    }
    public int agreeRequest(String tarid,String reqid){
         return userRelationMapper.agreeRequest(tarid,reqid);
    }
    public int refuseRequest(String tarid,String reqid){
        return userRelationMapper.refuseRequest(tarid,reqid);
    }
    public List<UserRelation> checkRequest(String userid, String friendid){
        return userRelationMapper.checkRequest(userid,friendid);
    }
}
