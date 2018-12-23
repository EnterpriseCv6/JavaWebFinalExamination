package finaltest_chatsys.chatsys.dao;


import finaltest_chatsys.chatsys.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.math.BigInteger;

//用户Dao
@Repository
public interface UserDao extends JpaRepository<User, Long>
{
    //添加用户
    @Modifying
    @Query(value = "insert into userTable values(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    public void addUser(String userid, String upassword, String username, String usersign, Date ctime, Date birth, String address, int userstatus);

    // 修改密码
    @Modifying
    @Query(value = "update userTable set upassword = ?1 where userid = ?2", nativeQuery = true)
    public void updatePassword(String newPassword, String userid);
}
