package top.sonjong.system.domain.criteria;

import java.io.Serializable;

public class UserCriteria implements Serializable {

    private static final long serialVersionUID = 4705731056701259120L;
    private String uname;
    private String status;
    private Long uid;
    private Long tid;

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
