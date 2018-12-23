package finaltest_chatsys.chatsys.service;


import finaltest_chatsys.chatsys.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;

// 实现用户添加功能
@Service
@Transactional
public class AddUserService implements UserService
{

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(String userid, String upassword, String username, String usersign, Date ctime, Date birth, String address, int userstatus)
    {
        userDao.addUser(userid, upassword, username, usersign, ctime, birth, address, userstatus);
    }
}
