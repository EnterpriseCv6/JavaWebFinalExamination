package finaltest_chatsys.chatsys.mapper;

import finaltest_chatsys.chatsys.entity.Friend;
import finaltest_chatsys.chatsys.entity.User;
import finaltest_chatsys.chatsys.entity.UserRelation;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserRelationMapper {

    @Select("select * from userTable where userid=#{userid}")
    @Results(value = {
            @Result(property="userId",column="userid"),
            @Result(property="userName",column="username"),
            @Result(property="sign",column="usersign"),
            @Result(property="cTime",column="ctime"),
            @Result(property="bitrh",column="birth"),
            @Result(property="adderss",column="address")
    })
    List<User> searchfriend(@Param("userid")String userid);
    @Insert("insert into unprocessedRequest(tarid,reqid,rtime,msg,reqstatus) values(#{userRelation.tarid},#{userRelation.reqid},#{userRelation.rtime},#{userRelation.msg},0);")
    void insertToUnprocessed(@Param("userRelation") UserRelation userRelation);
    @Select("select friendid from friendInfo where userid=#{userid}")
    @Results({
            @Result(property="reqid",column="friendid")
    })
    UserRelation judgeRelation(@Param("userid") String userId);
    @Select("select * from unprocessedRequest where tarid=#{tarid} and reqid=#{reqid}")
    @Results(value = {
            @Result(property="friendMessageId",column="friendMessageId"),
            @Result(property="tarid",column="tarid"),
            @Result(property="reqid",column="reqid"),
            @Result(property="rtime",column="rtime"),
            @Result(property="msg",column="msg"),
            @Result(property="reqstatus",column="reqstatus"),
            @Result(property="reqname",column="reqname"),
            @Result(property="reqgroup",column="reqgroup")
    })
    UserRelation getRequest(@Param("tarid")String tarid,@Param("reqid")String reqid);
    @Select("select friendgroup from unprocessedRequest where userid=#{userid} and friendid=#{friendid}")
    @Results({
            @Result(property="friendgroup",column="friendgroup")
    })
    List<UserRelation> getFriendgroup(@Param("userid")String userid, @Param("friendid")String friendid);
    @Insert("insert into friendInfo(userid,friendid,friendName,friendgroup) values(#{userid},#{friend.friendid},#{friend.friendName},#{friend.friendGroup}) ")
    int addFriend(@Param("userid")String userid, @Param("friend")Friend friend);
    @Update("update unprocessedRequest set reqstatus='1' where tarid=#{tarid} and reqid=#{reqid}")
    int agreeRequest(@Param("tarid")String tarid,@Param("reqid")String reqid);
    @Update("update unprocessedRequest set reqstatus='2' where tarid=#{tarid} and reqid=#{reqid}")
    int refuseRequest(@Param("tarid")String tarid,@Param("reqid")String reqid);
    @Select("select reqid,rtime,reqname,msg from unprocessedRequest where tarid=#{tarid} and reqid=#{reqid}")
    @Results(value = {
            @Result(property="reqid",column="reqid"),
            @Result(property="rtime",column="rtime"),
            @Result(property="msg",column="msg"),
            @Result(property="reqname",column="reqname")
    })
    List<UserRelation> checkRequest(@Param("tarid")String tarid,@Param("reqid")String reqid);
    @Select("select friendid,friendName,friendGroup from friendInfo where userId=#{userId}")
    @Results(value = {
            @Result(property = "friendid",column = "friendid"),
            @Result(property = "friendName",column = "friendName"),
            @Result(property = "friendGroup",column = "friendGroup")
    }
    )
    List<Friend> getFriendList(String userId);
}