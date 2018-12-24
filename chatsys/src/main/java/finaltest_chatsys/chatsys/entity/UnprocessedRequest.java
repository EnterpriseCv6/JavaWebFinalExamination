package finaltest_chatsys.chatsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unprocessedRequest")
public class UnprocessedRequest
{
    @Id
    @Column(name = "count(*)")
    private int n;

    public int getN()
    {
        return n;
    }

    public void setN(int n)
    {
        this.n = n;
    }
}
