package top.sonjong.system.domain.POJO;

import java.io.Serializable;
import java.math.BigDecimal;

public class IndustryPOJO implements Serializable {
    private static final long serialVersionUID = -6456490763990069052L;
    private Long iid;
    private Long i_pid;
    private BigDecimal total_harvest;
    private Integer status;
    private String harvestDate;
    private BigDecimal total_cost;
    private String pName;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public Long getI_pid() {
        return i_pid;
    }

    public void setI_pid(Long i_pid) {
        this.i_pid = i_pid;
    }

    public BigDecimal getTotal_harvest() {
        return total_harvest;
    }

    public void setTotal_harvest(BigDecimal total_harvest) {
        this.total_harvest = total_harvest;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setHarvestDate(String harvestDate) {
        this.harvestDate = harvestDate;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public BigDecimal getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(BigDecimal total_cost) {
        this.total_cost = total_cost;
    }
}
