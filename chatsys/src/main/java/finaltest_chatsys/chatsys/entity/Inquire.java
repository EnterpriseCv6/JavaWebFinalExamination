package finaltest_chatsys.chatsys.entity;

public class Inquire {
    private String InquireId;
    private String InquireName;
    private String InquireOld;
    private String InquireAddress;

    public String getInquireId() {
        return InquireId;
    }

    public String getInquireOld() {
        return InquireOld;
    }

    public String getInquireAddress() {
        return InquireAddress;
    }

    public String getInquireName() {
        return InquireName;
    }

    public void setInquireId(String inquireId) {
        InquireId = inquireId;
    }

    public void setInquireName(String inquireName) {
        InquireName = inquireName;
    }

    public void setInquireOld(String inquireOld) {
        InquireOld = inquireOld;
    }

    public void setInquireAddress(String inquireAddress) {
        InquireAddress = inquireAddress;
    }

    @Override
    public String toString(){
        return "查询该账号结果为："+InquireName+","+InquireOld+","+InquireAddress;
    }
}
