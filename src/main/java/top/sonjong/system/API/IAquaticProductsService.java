package top.sonjong.system.API;

import top.sonjong.system.domain.POJO.AquaticProductsPOJO;

import java.util.List;

public interface IAquaticProductsService {
    int addAquaticProducts(AquaticProductsPOJO aquaticProductsPOJO);
    List<AquaticProductsPOJO> getAquaticProducts();
    List<AquaticProductsPOJO> getAquaticProductsByCondition(String aType);
    int countProducts();
}
