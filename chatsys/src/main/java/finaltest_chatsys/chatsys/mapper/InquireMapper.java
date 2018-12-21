package finaltest_chatsys.chatsys.mapper;

import finaltest_chatsys.chatsys.entity.Inquire;
import org.apache.ibatis.annotations.Select;

public interface InquireMapper {
    @Select("SELECT * FROM chatInfo WHERE userid = #{InquireId}")
    Inquire searchUser(int InquireId);
}
