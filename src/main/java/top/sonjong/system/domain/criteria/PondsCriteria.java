package top.sonjong.system.domain.criteria;

import java.io.Serializable;
import java.math.BigDecimal;

public class PondsCriteria implements Serializable {
    private static final long serialVersionUID = -1826832630154764868L;
    private Long pid;
    private String pAddress;

    private BigDecimal minSize;
    private BigDecimal maxSize;
    private String pType;
    private Long p_fid;
    private String pName;
    private String pStatus;


    public BigDecimal getMinSize() {
        return minSize;
    }

    public void setMinSize(BigDecimal minSize) {
        this.minSize = minSize;
    }

    public BigDecimal getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(BigDecimal maxSize) {
        this.maxSize = maxSize;
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

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }
}
