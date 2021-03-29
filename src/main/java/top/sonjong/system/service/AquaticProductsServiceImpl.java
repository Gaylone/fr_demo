package top.sonjong.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sonjong.system.API.IAquaticProductsService;
import top.sonjong.system.dao.IAquaticProductsDAO;
import top.sonjong.system.domain.POJO.AquaticProductsPOJO;

import java.util.List;
@Service
public class AquaticProductsServiceImpl implements IAquaticProductsService {
    @Autowired
    IAquaticProductsDAO aquaticProductsDAO;
    @Override
    public int addAquaticProducts(AquaticProductsPOJO aquaticProductsPOJO) {
        if (aquaticProductsPOJO.getaType()==null||aquaticProductsPOJO.getaType().equals("")){
            System.out.println("水产名称不得为空");
            return -1;
        }
        return aquaticProductsDAO.addAquaticProducts(aquaticProductsPOJO);
    }

    @Override
    public List<AquaticProductsPOJO> getAquaticProducts() {
        return aquaticProductsDAO.getAquaticProducts();
    }

    @Override
    public List<AquaticProductsPOJO> getAquaticProductsByCondition(String aType) {
        return aquaticProductsDAO.getAquaticProductsByCondition(aType);
    }

    @Override
    public int countProducts() {
        return aquaticProductsDAO.countProducts();
    }
}
