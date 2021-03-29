package top.sonjong.system.API;

import top.sonjong.system.domain.POJO.DiseasePOJO;
import top.sonjong.system.domain.criteria.DiseaseCriteria;

import java.util.List;

public interface IDiseaseService {
    int addDisease(DiseasePOJO diseasePOJO);
    int updateDisease(DiseasePOJO diseasePOJO);
    List<DiseasePOJO> getAllDisease();
    int countAllDisease();
    List<DiseasePOJO> findDiseaseByCondition(DiseaseCriteria diseaseCriteria);
    int checkName(String dName);
}
