package finaltest_chatsys.chatsys.entity;

import javax.persistence.*;
import java.sql.Date;

// 用户vo
@Entity
@Table(name = "userTable")  // 与userTable表结构对应
public class User
{
    @Id
    @Column(name = "userid")
    private String userid;

    @Column(name = "upassword")
    private String upassword;

    @Column(name = "username")
    private String username;

    @Column(name = "usersign")
    private String usersign;

    @Column(name = "ctime")
    private Date ctime;

    @Column(name = "birth")
    private Date birth;

    @Column(name = "address")
    private String address;

    @Column(name = "userstatus")
    private int userstatus;

    public String getUserid()
    {
        return userid;
    }

    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    public String getUpassword()
    {
        return upassword;
    }

    public void setUpassword(String upassword)
    {
        this.upassword = upassword;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsersign()
    {
        return usersign;
    }

    public void setUsersign(String usersign)
    {
        this.usersign = usersign;
    }

    public Date getCtime()
    {
        return ctime;
    }

    public void setCtime(Date ctime)
    {
        this.ctime = ctime;
    }

    public Date getBirth()
    {
        return birth;
    }

    public void setBirth(Date birth)
    {
        this.birth = birth;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getUserstatus()
    {
        return userstatus;
    }

    public void setUserstatus(int userstatus)
    {
        this.userstatus = userstatus;
    }

}
