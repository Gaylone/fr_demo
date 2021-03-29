package top.sonjong.system.domain.criteria;

import java.io.Serializable;
import java.math.BigDecimal;

public class CaseCriteria implements Serializable {

    private static final long serialVersionUID = 9176662491485842798L;

    private Long cid;
    private Long c_did;
    private Long c_pid;
    private String solution;
    private String date;
    private String content;
    private Long c_tid;
    private String title;
    private Integer status;
    private BigDecimal loss;
    private String pName;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
