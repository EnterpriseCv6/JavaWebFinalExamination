package finaltest_chatsys.chatsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unprocessedMessage")
public class UnprocessedMessage
{
    @Id
    @Column(name = "sendid")
    private String sendid;

    @Column(name = "count(*)")
    private int n;

    public String getSendid()
    {
        return sendid;
    }

    public void setSendid(String sendid)
    {
        this.sendid = sendid;
    }

    public int getN()
    {
        return n;
    }

    public void setN(int n)
    {
        this.n = n;
    }
}
