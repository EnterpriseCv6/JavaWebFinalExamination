package finaltest_chatsys.chatsys.service;


import java.sql.Date;

// 实现用户添加、修改密码接口
public interface UserService
{
    // 添加用户
    public void addUser(String userid, String upassword, String username, String usersign, Date ctime, Date birth, String address, int userstatus);

    //修改密码
    public void updatePassword(String newPassword, String userid);
}
