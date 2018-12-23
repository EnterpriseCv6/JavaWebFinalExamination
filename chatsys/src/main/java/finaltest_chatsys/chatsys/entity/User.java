package finaltest_chatsys.chatsys.entity;
//用户类
public class User {
    //用户id
    private String userId;
    //用户名
    private String userName;
    //用户签名
    private String sign;
    //好友评论
    private String comment;
    //账号注册时间
    private String cTime;
    //用户生日
    private String bitrh;
    //用户地址
    private String adderss;
    //set()与get()方法
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getBitrh() {
        return bitrh;
    }

    public void setBitrh(String bitrh) {
        this.bitrh = bitrh;
    }

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss;
    }
}
