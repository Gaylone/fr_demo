package top.sonjong.system;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.sonjong.system.API.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private IFarmerService farmerService;
    @Autowired
    private IUserService userService;
    @Autowired
    ITechnicianService technicianService;
    @Autowired
    IPondsService pondsService;
    @Autowired
    IDiseaseService diseaseService;
    @Autowired
    ICaseService caseService;
    @Autowired
    IAquaticProductsService aquaticProductsService;
    @Autowired
    IIndustryService industryService;

    @Test
    void test(){
        System.out.println(diseaseService.checkName("烂鳃病"));
    }
}
