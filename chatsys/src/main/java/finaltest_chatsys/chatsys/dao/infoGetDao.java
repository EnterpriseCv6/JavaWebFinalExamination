package finaltest_chatsys.chatsys.dao;

import finaltest_chatsys.chatsys.vo.Info;
import java.util.List;
//消息获取Dao
public interface infoGetDao {
    //通过目标id获取聊天消息
    public List<Info> getChatInfoById(String id);
    //通过目标id获取好友申请信息
    public List<Info> getFriendReqById(String id);

}
