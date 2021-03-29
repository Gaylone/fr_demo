package top.sonjong.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.sonjong.system.domain.POJO.PondsPOJO;
import top.sonjong.system.domain.criteria.PondsCriteria;

import java.util.List;

@Repository
@Mapper
public interface IPondsDAO {
    int addPond(PondsPOJO pondsPOJO);
    int updatePondInfo(PondsPOJO pondsPOJO);
    int freezePond(Long pid);
    int activePond(Long pid);
    int fillPond(Long pid);
    int countAllPond();
    List<PondsPOJO> getAllPond();
    List<PondsPOJO> findPondByConditions(PondsCriteria pondsCriteria);

}
