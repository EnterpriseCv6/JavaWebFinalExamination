package finaltest_chatsys.chatsys.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest
{
    private final Logger logger =
            LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserServiceImpl service;

    @Test
    public void addUser()
    {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        service.addUser("mybatis", "mybatis", "mybatis", "mybatis", date, date, "Zhejiang", 1);
    }

    @Test
    public void updatePassword()
    {
        service.updatePassword("654321", "javaweb");
    }
}