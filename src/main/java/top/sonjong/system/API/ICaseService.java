package top.sonjong.system.API;

import top.sonjong.system.domain.POJO.CasePOJO;
import top.sonjong.system.domain.criteria.CaseCriteria;

import java.util.List;

public interface ICaseService {
    int saveCase(CasePOJO casePOJO);
    List<CasePOJO> getCaseByCondition(CaseCriteria caseCriteria);
    List<CasePOJO> getAllCase();
    int countCase();
}
