package top.sonjong.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.sonjong.system.domain.POJO.FarmerPOJO;
import top.sonjong.system.domain.criteria.FarmerCriteria;

import java.util.List;

@Mapper
@Repository
public interface IFarmerDAO {
    int addFarmer(FarmerPOJO farmerPOJO);

    int freezeFarmer(Long fid);
    int activeFarmer(Long fid);

    List<FarmerPOJO> findFarmersByConditions(FarmerCriteria farmerCriteria);

    List<FarmerPOJO> getAllFarmers();

    int countFarmerNum();

    int updateFarmerInfo(FarmerPOJO farmerPOJO);
}
