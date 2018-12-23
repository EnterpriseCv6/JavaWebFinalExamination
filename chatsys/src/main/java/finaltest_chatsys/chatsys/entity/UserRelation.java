package finaltest_chatsys.chatsys.entity;


import java.sql.Timestamp;

public class UserRelation {
    private String friendMessageId;
    private String tarid;
    private String reqid;
    private Timestamp rtime;
    private String msg;
    private String reqgroup;
    private String reqstatus;
    private String reqname;

    public String getFriendMessageId() {
        return friendMessageId;
    }

    public void setFriendMessageId(String friendMessageId) {
        this.friendMessageId = friendMessageId;
    }

    public String getTarid() {
        return tarid;
    }

    public void setTarid(String tarid) {
        this.tarid = tarid;
    }

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public Timestamp getRtime() {
        return rtime;
    }

    public void setRtime(Timestamp rtime) {
        this.rtime = rtime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReqgroup() {
        return reqgroup;
    }

    public void setReqgroup(String reqgroup) {
        this.reqgroup = reqgroup;
    }

    public String getReqstatus() {
        return reqstatus;
    }

    public void setReqstatus(String reqstatus) {
        this.reqstatus = reqstatus;
    }

    public String getReqname() {
        return reqname;
    }

    public void setReqname(String reqname) {
        this.reqname = reqname;
    }
}
