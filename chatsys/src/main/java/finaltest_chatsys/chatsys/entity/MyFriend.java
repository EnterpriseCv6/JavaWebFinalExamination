package finaltest_chatsys.chatsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userTable, firendInfo")
public class MyFriend
{
    @Id
    @Column(name = "friendid")
    private String id;

    @Column(name = "username")
    private String name;

    @Column(name = "userstatus")
    private int status;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
}
