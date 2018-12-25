package finaltest_chatsys.chatsys.service;

import finaltest_chatsys.chatsys.dao.messageInfoMapper;
import finaltest_chatsys.chatsys.entity.Info;
import finaltest_chatsys.chatsys.entity.chatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@ComponentScan({"finaltest_chatsys.chatsys.dao.messageInfoMapper"})
@Service("messageGetService")
public class messageGetService implements messageGetIService {

    @Resource
    private messageInfoMapper messageInfoMapper;

    @Override
    public void insertToUnprocessed(Info info, String userId) {
        messageInfoMapper.insertToUnprocessed(info,userId);
    }

    @Override
    public void insertToLog(Info info,String userId) {
        System.out.println(info.getId()+" "+info.getInfoContent()+" "+info.getTime()+" "+userId);
        messageInfoMapper.insertToLog(info,userId);
    }

    @Override
    public List<Info> select(String userId,String tarId) {
        return messageInfoMapper.select(userId,tarId);
    }

    @Override
    public void delete(String userId,String tarId) {
        messageInfoMapper.delete(userId,tarId);
    }

    @Override
    public List<chatMessage> selectChatMessage(String userId, String tarId) {
        return messageInfoMapper.selectChatMessage(userId,tarId);
    }

    @Override
    public String login(String userId, String pwd) {
        return messageInfoMapper.login(userId,pwd);
    }
}
