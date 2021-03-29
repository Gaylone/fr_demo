package top.sonjong.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.sonjong.system.domain.POJO.CasePOJO;
import top.sonjong.system.domain.criteria.CaseCriteria;

import java.util.List;

@Mapper
@Repository
public interface ICaseDAO {
    int saveCase(CasePOJO casePOJO);
    List<CasePOJO> getCaseByCondition(CaseCriteria caseCriteria);
    List<CasePOJO> getAllCase();
    int countCase();
}
