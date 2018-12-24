package finaltest_chatsys.chatsys.dao;

import finaltest_chatsys.chatsys.entity.MyFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendDao extends JpaRepository<MyFriend, String>
{
    // 返回好友userTable 与 friendInfo表做连接查询
    @Modifying
    @Query(value = "select friendid, username, userstatus from friendInfo, userTable where friendid = userTable.userid and friendInfo.userid = ?1", nativeQuery = true)
    public List<MyFriend> findFriends(String userid);
}
