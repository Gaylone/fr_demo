package top.sonjong.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sonjong.system.API.ITechnicianService;
import top.sonjong.system.dao.ITechnicianDAO;
import top.sonjong.system.domain.POJO.TechnicianPOJO;
import top.sonjong.system.domain.criteria.TechnicianCriteria;

import java.util.List;
@Service
public class TechnicianServiceImpl implements ITechnicianService {
    @Autowired
    ITechnicianDAO technicianDAO;
    @Override
    public int addTechnician(TechnicianPOJO technicianPOJO) {

        return technicianDAO.addTechnician(technicianPOJO);
    }

    @Override
    public int ChangeTechnicianStatus(Long tid, Integer flag) {
        if (flag==0){
            return technicianDAO.activeTechnician(tid);
        }else {
            if (flag==1){
                return technicianDAO.freezeTechnician(tid);
            }else {
                return -1;//flag值不对
            }
        }

    }

    @Override
    public List<TechnicianPOJO> findTechniciansByConditions(TechnicianCriteria technicianCriteria) {
        return technicianDAO.findTechnicianByConditions(technicianCriteria);
    }

    @Override
    public List<TechnicianPOJO> getAllTechnicians() {
        return technicianDAO.getAllTechnician();
    }

    @Override
    public int countTechnicianNum() {
        return technicianDAO.countTechnicianNum();
    }

    @Override
    public int updateTechnicianInfo(TechnicianPOJO technicianPOJO) {
        return technicianDAO.updateTechnicianInfo(technicianPOJO);
    }

    @Override
    public TechnicianPOJO userLogin(String account) {

        return technicianDAO.userLogin(account);
    }

    @Override
    public int freezeTechnician(Long tid) {
        return technicianDAO.freezeTechnician(tid);
    }

    @Override
    public int activeTechnician(Long tid) {
        return technicianDAO.activeTechnician(tid);
    }


}
