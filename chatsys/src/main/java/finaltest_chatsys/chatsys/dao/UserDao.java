package finaltest_chatsys.chatsys.dao;


import finaltest_chatsys.chatsys.entity.User;
//用户Dao
public interface UserDao {
    //添加用户
    public void addUser(User user);
    //通过id删除用户
    public void deleteUserById(String id);
}
