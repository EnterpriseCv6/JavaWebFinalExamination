package finaltest_chatsys.chatsys.service;


import finaltest_chatsys.chatsys.entity.User;

import java.sql.Date;

// 实现用户添加、修改密码、查看信息、修改信息接口
public interface UserService
{
    // 添加用户
    public void addUser(String userid, String upassword, String username, String usersign, Date ctime, Date birth, String address, int userstatus);

    // 登录
    public void login(String userid);

    // 退出
    public void logoff(String userid);

    //修改密码
    public void updatePassword(String newPassword, String userid);

    // 查看信息
    User queryUserInformation(String userid);

    // 修改信息
    void updateInformation(String newName, String newSign, Date newBirth, String newAddress, String userid);
}
