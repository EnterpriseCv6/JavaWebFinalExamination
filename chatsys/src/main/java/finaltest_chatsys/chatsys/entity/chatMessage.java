package finaltest_chatsys.chatsys.entity;


public class chatMessage {
    //发送人Id
    private String sendId;
    //聊天时间
    private String time;
    //聊天信息
    private String message;
    //get与set方法
    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
