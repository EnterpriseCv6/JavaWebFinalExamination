package finaltest_chatsys.chatsys.service;


import java.sql.Date;

// 实现用户添加接口
public interface UserService
{
    public void addUser(String userid, String upassword, String username, String usersign, Date ctime, Date birth, String address, int userstatus);
}
