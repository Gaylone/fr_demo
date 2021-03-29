package top.sonjong.system.domain.POJO;

import java.io.Serializable;

public class DiseasePOJO implements Serializable {
    private static final long serialVersionUID = 4549822334890595940L;
    private Long did;
    private String dName;
    private String memo;


    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
