package top.sonjong.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sonjong.system.API.IDiseaseService;
import top.sonjong.system.dao.IDiseaseDAO;
import top.sonjong.system.domain.POJO.DiseasePOJO;
import top.sonjong.system.domain.criteria.DiseaseCriteria;

import java.util.List;

@Service
public class DiseaseServiceImpl implements IDiseaseService {
    @Autowired
    private IDiseaseDAO diseaseDAO;
    @Override
    public int addDisease(DiseasePOJO diseasePOJO) {
        return diseaseDAO.addDisease(diseasePOJO);
    }

    @Override
    public int updateDisease(DiseasePOJO diseasePOJO) {
        if (diseasePOJO.getDid()==null){
            return -1;
        }
        DiseaseCriteria criteria=new DiseaseCriteria();
        criteria.setDid(diseasePOJO.getDid());
        int flag=diseaseDAO.findDiseaseByCondition(criteria).size();
        if (flag==0){
            return -2;
        }
        if (flag==1){
            return diseaseDAO.updateDisease(diseasePOJO);
        }
        return 0;
    }

    @Override
    public List<DiseasePOJO> getAllDisease() {

        return diseaseDAO.getAllDisease();
    }

    @Override
    public int countAllDisease() {
        return diseaseDAO.countAllDisease();
    }

    @Override
    public List<DiseasePOJO> findDiseaseByCondition(DiseaseCriteria diseaseCriteria) {
        return diseaseDAO.findDiseaseByCondition(diseaseCriteria);
    }

    @Override
    public int checkName(String dName) {
        if (dName.substring(dName.length()-1).equals("病")){
            dName= dName.substring(0, dName.length() -1);
        }
        return diseaseDAO.checkName(dName);
    }
}
