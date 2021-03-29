package top.sonjong.system.domain.POJO;

import java.io.Serializable;

public class AquaticProductsPOJO implements Serializable {
    private static final long serialVersionUID = 8802791193629313365L;
    private Long aid;
    private String aType;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }
}
