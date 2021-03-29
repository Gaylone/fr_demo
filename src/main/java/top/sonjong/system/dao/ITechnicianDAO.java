package top.sonjong.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.sonjong.system.domain.POJO.TechnicianPOJO;
import top.sonjong.system.domain.criteria.TechnicianCriteria;

import java.util.List;

@Repository
@Mapper
public interface ITechnicianDAO {

    int addTechnician(TechnicianPOJO technicianPOJO);

    int freezeTechnician(Long tid);

    int activeTechnician(Long tid);

    List<TechnicianPOJO> findTechnicianByConditions(TechnicianCriteria technicianCriteria);

    List<TechnicianPOJO> getAllTechnician();

    int countTechnicianNum();

    int updateTechnicianInfo(TechnicianPOJO technicianPOJO);

    TechnicianPOJO userLogin(String account,String pswd);
}
