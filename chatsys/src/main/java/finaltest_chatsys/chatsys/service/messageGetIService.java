package finaltest_chatsys.chatsys.service;

import finaltest_chatsys.chatsys.entity.Info;
import finaltest_chatsys.chatsys.entity.chatMessage;

import java.util.List;

public interface messageGetIService {
    public void insertToUnprocessed(Info info, String userId);
    public void insertToLog(Info info,String userId);
    public List<Info> select(String userId,String tarId);
    public void delete(String userId,String tarId);
    public List<chatMessage> selectChatMessage(String userId,String tarId);
}
