package top.sonjong.system.API;

import top.sonjong.system.domain.POJO.PondsPOJO;
import top.sonjong.system.domain.criteria.PondsCriteria;

import java.util.List;

public interface IPondsService {
    int addPond(PondsPOJO pondsPOJO);
    int updatePondInfo(PondsPOJO pondsPOJO);
    int changePondStatus(Long pid,Integer status);
    int countAllPond();
    List<PondsPOJO> getAllPond();
    List<PondsPOJO> findPondByConditions(PondsCriteria pondsCriteria);
}
