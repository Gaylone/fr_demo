package top.sonjong.system.domain.criteria;

import java.io.Serializable;

public class TechnicianCriteria implements Serializable {

    private static final long serialVersionUID = -8787384760863113971L;
    private Long tid;
    private String tName;
    private String account;
    private String pswd;
    private String tidNum;
    private String tPhone;
    private String tSex;
    private String tMajor;
    private Integer role;
    private Integer status;

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return role;
    }

    public void setStatus(Integer role) {
        this.role = role;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getTidNum() {
        return tidNum;
    }

    public void setTidNum(String tidNum) {
        this.tidNum = tidNum;
    }

    public String gettPhone() {
        return tPhone;
    }

    public void settPhone(String tPhone) {
        this.tPhone = tPhone;
    }

    public String gettSex() {
        return tSex;
    }

    public void settSex(String tSex) {
        this.tSex = tSex;
    }

    public String gettMajor() {
        return tMajor;
    }

    public void settMajor(String tMajor) {
        this.tMajor = tMajor;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


}
