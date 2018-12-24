package finaltest_chatsys.chatsys.dao;

import finaltest_chatsys.chatsys.entity.UnprocessedRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnpRequestDao extends JpaRepository<UnprocessedRequest, String>
{
    @Modifying
    @Query(value = "select count(*) from unprocessedRequest where tarid = ?", nativeQuery = true)
    public List<UnprocessedRequest> countUnpRequest(String tarid);
}
