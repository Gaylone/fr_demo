package top.sonjong.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sonjong.system.API.IFarmerService;
import top.sonjong.system.dao.IFarmerDAO;
import top.sonjong.system.domain.POJO.FarmerPOJO;
import top.sonjong.system.domain.criteria.FarmerCriteria;

import java.util.List;

@Service
public class FarmerServiceImpl implements IFarmerService {
    @Autowired
    private IFarmerDAO farmerDAO;
    @Override
    public int addFarmer(FarmerPOJO farmerPOJO) {
        if (farmerPOJO.getfAddress()==null||farmerPOJO.getfAddress()==""
                ||farmerPOJO.getfName()==null||farmerPOJO.getfName()==""
                ||farmerPOJO.getfIdNum()==null||farmerPOJO.getfIdNum()==""
                ||farmerPOJO.getfSex()==null||farmerPOJO.getfSex()==""
        ){
         return -1;
        }else if (farmerPOJO.getfPhone()==null||farmerPOJO.getfPhone()==""){
            farmerPOJO.setfPhone("0000000000");
        }
       int flag= farmerDAO.addFarmer(farmerPOJO);
        return flag;
    }

    @Override
    public int ChangeFarmerStatus(Long fid, Integer flag) {
        int result =0;
        if (flag==0){
            result=farmerDAO.activeFarmer(fid);
        }else if (flag==1){
            result=farmerDAO.freezeFarmer(fid);
        }
        return result;
    }

    @Override
    public List<FarmerPOJO> findFarmersByConditions(FarmerCriteria farmerCriteria) {
        return farmerDAO.findFarmersByConditions(farmerCriteria);
    }

    @Override
    public List<FarmerPOJO> getAllFarmers() {
        return farmerDAO.getAllFarmers();
    }

    @Override
    public int countFarmerNum() {
        return farmerDAO.countFarmerNum();
    }

    @Override
    public int updateFarmerInfo(FarmerPOJO farmerPOJO) {

        return farmerDAO.updateFarmerInfo(farmerPOJO);
    }
}
