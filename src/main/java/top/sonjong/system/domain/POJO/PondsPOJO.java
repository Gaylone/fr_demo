package top.sonjong.system.domain.POJO;

import java.io.Serializable;
import java.math.BigDecimal;

public class PondsPOJO implements Serializable {
    private static final long serialVersionUID = 1771435410818673700L;
    private Long pid;
    private String pAddress;
    private BigDecimal pSize;
    private String pType;
    private Long p_fid;
    private String pName;
    private Integer pStatus;
    private String fName;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
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

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getpStatus() {
        return pStatus;
    }

    public void setpStatus(Integer pStatus) {
        this.pStatus = pStatus;
    }
}
