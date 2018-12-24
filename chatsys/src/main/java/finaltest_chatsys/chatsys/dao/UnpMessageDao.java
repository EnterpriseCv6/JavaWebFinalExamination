package finaltest_chatsys.chatsys.dao;

import finaltest_chatsys.chatsys.entity.UnprocessedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnpMessageDao extends JpaRepository<UnprocessedMessage, String>
{
    @Modifying
    @Query(value = "select sendid, count(*) from unprocessedMessage where receiveid = ? group by sendid", nativeQuery = true)
    public List<UnprocessedMessage> findUnpMessage(String receiveid);
}
