package top.sonjong.system.API;

import top.sonjong.system.domain.POJO.FarmerPOJO;
import top.sonjong.system.domain.criteria.FarmerCriteria;

import java.util.List;

public interface IFarmerService {

    int addFarmer(FarmerPOJO farmerPOJO);

    int ChangeFarmerStatus(Long fid,Integer flag);//fid自不必说，flag的取值有 从业/转业

    List<FarmerPOJO>  findFarmersByConditions(FarmerCriteria farmerCriteria);

    List<FarmerPOJO> getAllFarmers();

    int countFarmerNum();

    int updateFarmerInfo(FarmerPOJO farmerPOJO);
}