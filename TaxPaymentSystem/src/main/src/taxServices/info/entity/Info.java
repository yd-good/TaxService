/*
信息:
*/
package taxServices.info.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Info implements Serializable {
     private String infoId;
     private String type;
     private String source;
     private String title;
     private String content;
     private String memo;
     private String creator;
    private Timestamp createTime;
     private String state;
     private static String INFO_STATE_PUBLIC="1";//发布
     private static String INFO_STATE_STOP="0";//停用
//     通知公告、政策速递、纳税指导
     private static String INFO_TYPE_ANNOUNCEMENT="announcement";
     private static String INFO_TYPE_PolicyExpress="policyExpress";
     private static String INFO_TYPE_TAXGUIDANCE="taxGuidance";
     public static Map<String,String> INFO_TYPE_MAP;
     static {
         INFO_TYPE_MAP=new HashMap<String,String>();
         INFO_TYPE_MAP.put(INFO_TYPE_ANNOUNCEMENT,"通知公告");
         INFO_TYPE_MAP.put(INFO_TYPE_PolicyExpress,"政策速递");
         INFO_TYPE_MAP.put(INFO_TYPE_TAXGUIDANCE,"纳税指导");
     }
     public Info() {
    }
     public Info(String infoId, String type, String source, String title, String content, String memo, String creator, Timestamp createTime, String state) {
        this.infoId = infoId;
        this.type = type;
        this.source = source;
        this.title = title;
        this.content = content;
        this.memo = memo;
        this.creator = creator;
        this.createTime = createTime;
        this.state = state;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Override
    public String toString() {
        return "Info{" +
                "infoId='" + infoId + '\'' +
                ", type='" + type + '\'' +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", memo='" + memo + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", state='" + state + '\'' +
                '}';
    }
}
