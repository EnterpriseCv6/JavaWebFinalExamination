package finaltest_chatsys.chatsys.service;

import finaltest_chatsys.chatsys.entity.Inquire;
import finaltest_chatsys.chatsys.mapper.InquireMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class InquireService {
    @Autowired
    InquireMapper inquireMapper;

    public Inquire searchUser(int InquireId){
        return inquireMapper.searchUser(InquireId);
    }
}
