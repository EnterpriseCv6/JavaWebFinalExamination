package finaltest_chatsys.chatsys.dao;

import finaltest_chatsys.chatsys.entity.Info;
import finaltest_chatsys.chatsys.entity.chatMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface messageInfoMapper {
    //将消息插入到未处理信息表中
    @Insert("insert into unprocessedMessage(messageId,sendid,receiveid,stime,message) values(0,#{userId},#{info.id},#{info.time},#{info.infoContent});")
    void insertToUnprocessed(@Param("info") Info info,@Param("userId") String userId);
    //将消息插入到聊天记录表中
    @Insert("insert into chatInfo(msgId,sendid,receiveid,chattime,message) values(0,#{userId},#{info.id},#{info.time},#{info.infoContent});")
    void insertToLog(@Param("info") Info info,@Param("userId") String userId);
    //获取未处理的消息
    @Select("select receiveid,stime,message from unprocessedMessage where receiveid=#{userId} and sendid=#{tarId};")
    @Results({
            @Result(property="infoContent",column="message"),
            @Result(property="time",column="stime"),
            @Result(property="id",column="receiveid")
    })
    List<Info> select(@Param("userId") String userId,@Param("tarId") String tarId);
    //将消息从未处理消息表中删除
    @Delete("delete from unprocessedMessage where receiveid=#{userId} and sendid=#{tarId};")
    void delete(@Param("userId") String userId,@Param("tarId") String tarId);
    //获取聊天记录
    @Select("select sendid,chattime,message from chatInfo where( (sendid=#{userId} and receiveid=#{tarId}) or (sendid=#{tarId} and receiveid=#{userId}));")
    @Results(value = {
            @Result(property = "sendId",column = "sendid"),
            @Result(property = "time",column = "chattime"),
            @Result(property = "message",column = "message")
    })
    List<chatMessage> selectChatMessage(@Param("userId") String userId,@Param("tarId") String tarId);
}
