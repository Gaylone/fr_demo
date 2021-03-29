package top.sonjong.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.sonjong.system.domain.POJO.DiseasePOJO;
import top.sonjong.system.domain.criteria.DiseaseCriteria;

import java.util.List;

@Repository
@Mapper
public interface IDiseaseDAO {
    int addDisease(DiseasePOJO diseasePOJO);
    int updateDisease(DiseasePOJO diseasePOJO);
    List<DiseasePOJO> getAllDisease();
    int countAllDisease();
    List<DiseasePOJO> findDiseaseByCondition(DiseaseCriteria diseaseCriteria);
    int checkName(String dName);
}
