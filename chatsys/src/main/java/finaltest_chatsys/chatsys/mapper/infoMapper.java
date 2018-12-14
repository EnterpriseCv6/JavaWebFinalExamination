package finaltest_chatsys.chatsys.mapper;

import finaltest_chatsys.chatsys.vo.Info;
import org.apache.ibatis.annotations.Mapper;

//mybatis(消息Mapper)
@Mapper
public interface infoMapper {
    public void select(Info info);
    public void delete(Info info);

}
