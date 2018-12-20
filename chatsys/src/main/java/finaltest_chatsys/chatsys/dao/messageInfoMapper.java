package finaltest_chatsys.chatsys.dao;

import finaltest_chatsys.chatsys.entity.Info;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface messageInfoMapper {
    @Insert("insert into unprocessedMessage(sendid,receiveid,stime,message) values(#{userId},#{info.id},#{info.time},#{info.infoContent});")
    void insertToUnprocessed(@Param("info") Info info,@Param("userId") String userId);
    @Insert("insert into chatInfo(sendid,receiveid,chattime,message) values(#{userId},#{info.id},#{info.time},#{info.infoContent});")
    void insertToLog(@Param("info") Info info,@Param("userId") String userId);
    @Select("select receiveid,stime,message from unprocessedMessage where receiveid=#{userId} and sendid=#{tarId};")
    @Results({
            @Result(property="infoContent",column="message"),
            @Result(property="time",column="stime"),
            @Result(property="id",column="receiveid")
    })
    List<Info> select(@Param("userId") String userId,@Param("tarId") String tarId);
    @Delete("delete from unprocessedMessage where receiveid=#{userId} and sendid=#{tarId};")
    void delete(@Param("userId") String userId,@Param("tarId") String tarId);
}
