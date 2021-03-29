package top.sonjong.system.domain.POJO;

import java.io.Serializable;

public class FarmerPOJO implements Serializable {
    private static final long serialVersionUID = 2920739286139156517L;
    private Long fid;
    private String fName;
    private String fIdNum;
    private String fSex;
    private String fBirthday;
    private String fAddress;
    private String fPhone;
    private Integer status;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfIdNum() {
        return fIdNum;
    }

    public void setfIdNum(String fIdNum) {
        this.fIdNum = fIdNum;
    }

    public String getfSex() {
        return fSex;
    }

    public void setfSex(String fSex) {
        this.fSex = fSex;
    }

    public String getfBirthday() {
        return fBirthday;
    }

    public void setfBirthday(String fBirthday) {
        this.fBirthday = fBirthday;
    }

    public String getfAddress() {
        return fAddress;
    }

    public void setfAddress(String fAddress) {
        this.fAddress = fAddress;
    }

    public String getfPhone() {
        return fPhone;
    }

    public void setfPhone(String fPhone) {
        this.fPhone = fPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
