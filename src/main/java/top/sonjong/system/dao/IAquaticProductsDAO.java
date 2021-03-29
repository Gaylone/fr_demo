package top.sonjong.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.sonjong.system.domain.POJO.AquaticProductsPOJO;

import java.util.List;

@Repository
@Mapper
public interface IAquaticProductsDAO {
    int addAquaticProducts(AquaticProductsPOJO aquaticProductsPOJO);
    List<AquaticProductsPOJO> getAquaticProducts();
    int countProducts();
    List<AquaticProductsPOJO> getAquaticProductsByCondition(String aType);
}
