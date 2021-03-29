package top.sonjong.system.domain.POJO;

import java.io.Serializable;
import java.math.BigDecimal;

public class CasePOJO implements Serializable {

    private static final long serialVersionUID = -3468450752669390871L;
    private Long cid;
    private Long c_did;
    private Long c_tid;
    private Long c_pid;
    private String solution;
    private String date;
    private String content;
    private String title;
    private BigDecimal loss;

    //查询拓展：疾病
    private Long did;
    private String dName;
    private String memo;
    //查询拓展：技术员
    private Long tid;
    private String tName;
    private String account;
    private String pswd;
    private String tidNum;
    private String tPhone;
    private String tSex;
    private String tMajor;
    private Integer role;
    //查询拓展：池塘
    private Long pid;
    private String pAddress;
    private BigDecimal pSize;
    private String pType;
    private Long p_fid;
    private String pName;
    private Integer pStatus;
    //查询拓展：养殖户
    private Long fid;
    private String fName;
    private String fIdNum;
    private String fSex;
    private String fBirthday;
    private String fAddress;
    private String fPhone;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public BigDecimal getpSize() {
        return pSize;
    }

    public void setpSize(BigDecimal pSize) {
        this.pSize = pSize;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public Long getP_fid() {
        return p_fid;
    }

    public void setP_fid(Long p_fid) {
        this.p_fid = p_fid;
    }

    public Integer getpStatus() {
        return pStatus;
    }

    public void setpStatus(Integer pStatus) {
        this.pStatus = pStatus;
    }

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
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public BigDecimal getLoss() {
        return loss;
    }

    public void setLoss(BigDecimal loss) {
        this.loss = loss;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getC_tid() {
        return c_tid;
    }

    public void setC_tid(Long c_tid) {
        this.c_tid = c_tid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getC_did() {
        return c_did;
    }

    public void setC_did(Long c_did) {
        this.c_did = c_did;
    }

    public Long getC_pid() {
        return c_pid;
    }

    public void setC_pid(Long c_pid) {
        this.c_pid = c_pid;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
