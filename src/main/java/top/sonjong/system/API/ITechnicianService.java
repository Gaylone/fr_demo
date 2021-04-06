package top.sonjong.system.API;

import top.sonjong.system.domain.POJO.TechnicianPOJO;
import top.sonjong.system.domain.criteria.TechnicianCriteria;

import java.util.List;

public interface ITechnicianService {
    int addTechnician(TechnicianPOJO technicianPOJO);

    int ChangeTechnicianStatus(Long tid,Integer flag);

    List<TechnicianPOJO> findTechniciansByConditions(TechnicianCriteria technicianCriteria);

    List<TechnicianPOJO> getAllTechnicians();

    int countTechnicianNum();

    int updateTechnicianInfo(TechnicianPOJO technicianPOJO);

    TechnicianPOJO  userLogin(String account);
    int freezeTechnician(Long tid);

    int activeTechnician(Long tid);
}
