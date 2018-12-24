package finaltest_chatsys.chatsys.service;


import finaltest_chatsys.chatsys.dao.UserDao;
import finaltest_chatsys.chatsys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;

// 实现用户添加、信息查看、修改密码、修改信息功能
@Service
@Transactional
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserDao userDao;

    // 注册用户
    @Override
    public void addUser(String userid, String upassword, String username, String usersign, Date ctime, Date birth, String address, int userstatus)
    {
        userDao.addUser(userid, upassword, username, usersign, ctime, birth, address, userstatus);
    }

    // 登录login
    @Override
    public void login(String userid)
    {
        // 修改status=1
        userDao.loginStatus(userid);
    }

    // 退出
    @Override
    public void logoff(String userid)
    {
        // 修改status=0
        userDao.logoffSattus(userid);
    }

    // 修改密码
    @Override
    public void updatePassword(String newPassword, String userid)
    {
        userDao.updatePassword(newPassword, userid);
    }

    // 获取用户信息
    @Override
    public User queryUserInformation(String userid)
    {
        return userDao.queryUserInformation(userid);
    }

    // 修改信息
    @Override
    public void updateInformation(String newName, String newSign, Date newBirth, String newAddress, String userid)
    {
        userDao.updateInformation(newName, newSign, newBirth, newAddress, userid);
    }
}
