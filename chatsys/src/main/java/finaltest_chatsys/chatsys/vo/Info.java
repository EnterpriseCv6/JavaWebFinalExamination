package finaltest_chatsys.chatsys.vo;
//消息类
public class Info {
    //消息时间
    private String time;
    //消息内容
    private String infoContent;
    //对象用户id
    private String id;
    //好友分组
    private String group=new String("");
    //各种set方法

    public void setGroup(String group) {
        this.group = group;
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

    public String getTime() {
        return time;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public String getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }
}
