package finaltest_chatsys.chatsys.dao;


import finaltest_chatsys.chatsys.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.math.BigInteger;
import java.util.List;

//用户Dao
@Repository
public interface UserDao extends JpaRepository<User, Long>
{
    //添加用户
    @Modifying
    @Query(value = "insert into userTable values(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    public void addUser(String userid, String upassword, String username, String usersign, Date ctime, Date birth, String address, int userstatus);

    // 登入修改login
    @Modifying
    @Query(value = "update userTable set userstatus = 1 where userid = ?", nativeQuery = true)
    public void loginStatus(String userid);

    // 退出修改status
    @Modifying
    @Query(value = "update userTable set userstatus = 0 where userid = ?", nativeQuery = true)
    public void logoffSattus(String userid);

    // 修改密码
    @Modifying
    @Query(value = "update userTable set upassword = ?1 where userid = ?2", nativeQuery = true)
    public void updatePassword(String newPassword, String userid);

    // 返回个人信息
    @Query(value = "select * from userTable where userid = ?", nativeQuery = true)
    public User queryUserInformation(String userid);

    // 修改个人信息
    @Modifying
    @Query(value = "update userTable set username = ?1, usersign = ?2, birth = ?3, address = ?4 where userid = ?5", nativeQuery = true)
    public void updateInformation(String newName, String newSign, Date newBirth, String newAddress, String userid);
}
