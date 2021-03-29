package top.sonjong.system.domain.POJO;

import java.io.Serializable;
import java.math.BigDecimal;

public class IndustryDetailPOJO  implements Serializable {
    private static final long serialVersionUID = -5595238124739099662L;
    private Long iid;
    private String aType;
    private int pre_num;
    private BigDecimal weight;
    private BigDecimal unit_price;

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public int getPre_num() {
        return pre_num;
    }

    public void setPre_num(int pre_num) {
        this.pre_num = pre_num;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }
}
