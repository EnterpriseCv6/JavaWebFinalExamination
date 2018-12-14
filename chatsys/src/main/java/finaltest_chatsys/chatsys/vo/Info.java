package finaltest_chatsys.chatsys.vo;
//消息类
public class Info {
    //消息种类
    private String infoType;
    //消息时间
    private String time;
    //消息内容
    private String infoContent;
    //对象用户id
    private String id;
    //各种set方法
    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
