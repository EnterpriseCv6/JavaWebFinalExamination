package finaltest_chatsys.chatsys.service;


import finaltest_chatsys.chatsys.dao.FriendDao;
import finaltest_chatsys.chatsys.dao.UnpMessageDao;
import finaltest_chatsys.chatsys.dao.UnpRequestDao;
import finaltest_chatsys.chatsys.dao.UserDao;
import finaltest_chatsys.chatsys.entity.UnprocessedMessage;
import finaltest_chatsys.chatsys.entity.UnprocessedRequest;
import finaltest_chatsys.chatsys.entity.User;
import finaltest_chatsys.chatsys.entity.MyFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

// 实现用户添加、信息查看、修改密码、修改信息功能
@Service
@Transactional
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserDao userDao;

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private UnpMessageDao unpMessageDao;

    @Autowired
    private UnpRequestDao unpRequestDao;

    // 注册用户
    public void addUser(String userid, String upassword, String username, String usersign, Date ctime, Date birth, String address, int userstatus)
    {
        userDao.addUser(userid, upassword, username, usersign, ctime, birth, address, userstatus);
    }

    // 登录成功
    public void login(String userid)
    {
        // 修改status=1
        userDao.loginStatus(userid);
    }

    // 退出
    public void logoff(String userid)
    {
        // 修改status=0
        userDao.logoffSattus(userid);
    }

    // 修改密码
    public void updatePassword(String newPassword, String userid)
    {
        userDao.updatePassword(newPassword, userid);
    }

    // 获取用户信息
    public User queryUserInformation(String userid)
    {
        return userDao.queryUserInformation(userid);
    }

    // 修改信息
    public void updateInformation(String newName, String newSign, Date newBirth, String newAddress, String userid)
    {
        userDao.updateInformation(newName, newSign, newBirth, newAddress, userid);
    }

    // 返回好友
    public List<MyFriend> findFriends(String userid)
    {
        return friendDao.findFriends(userid);
    }

    // 返回未读消息数
    public List<UnprocessedMessage> findUnpMesssage(String receiveid)
    {
        return unpMessageDao.findUnpMessage(receiveid);
    }

    // 返回未处理消息数量
    public List<UnprocessedRequest> countUnpRequest(String tarid)
    {
        return unpRequestDao.countUnpRequest(tarid);
    }
}
