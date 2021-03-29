package top.sonjong.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.sonjong.system.API.*;
import top.sonjong.system.domain.POJO.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class JumpController {
    @Autowired
    private IFarmerService farmerService;
    @Autowired
    private IPondsService pondsService;
    @Autowired
    private IIndustryService industryService;
    @Autowired
    private IAquaticProductsService productsService;
    @Autowired
    private IDiseaseService diseaseService;
    @Autowired
    private ICaseService caseService;

    @RequestMapping("/index_home")
    public String toIndexHome(){
        return "index_home";
    }
    @RequestMapping("/login")
    public String toLogin(HttpSession session){
        session.invalidate();
        return "login";
    }
    @RequestMapping("/farmerManage")
    public ModelAndView toFarmerManage(HttpSession session){
        ModelAndView model = new ModelAndView();
       List<FarmerPOJO> farmerPOJOList = farmerService.getAllFarmers();
       model.addObject("farmerPOJOList",farmerPOJOList);
        model.setViewName("farmer_Manage");
        return model;
    }
    @RequestMapping("/pondsManage")
    public ModelAndView toPondsManage(){
        ModelAndView model = new ModelAndView();
        List<PondsPOJO> pondsPOJOList = pondsService.getAllPond();
        model.addObject("pondsPOJOList",pondsPOJOList);
        model.setViewName("ponds_Manage");
        return model;
    }
    @RequestMapping("/productList")
    public ModelAndView toProductList(){
        ModelAndView model = new ModelAndView();
        List<AquaticProductsPOJO> aquaticProductsPOJOList = productsService.getAquaticProducts();
        model.addObject("aquaticProductsPOJOList",aquaticProductsPOJOList);
        model.setViewName("product_List");
        return model;
    }
    @RequestMapping("/industryList")
    public ModelAndView toIndustryList(){
        ModelAndView model = new ModelAndView();
        List<IndustryPOJO> industryPOJOSList = industryService.getAllIndustry();
        model.addObject("industryPOJOSList",industryPOJOSList);
        model.setViewName("industry_list");
        return model;
    }

    @RequestMapping("/diseaseList")
    public ModelAndView toDiseaseList(){
        ModelAndView modelAndView = new ModelAndView();
        List<DiseasePOJO> diseasePOJOList =diseaseService.getAllDisease();
        modelAndView.addObject("diseasePOJOList",diseasePOJOList);
        modelAndView.setViewName("disease_list");
        return modelAndView;
    }

    @RequestMapping("/caseList")
    public ModelAndView toCaseList(){
        ModelAndView modelAndView = new ModelAndView();
        List<CasePOJO> casePOJOList = caseService.getAllCase();
        modelAndView.addObject("casePOJOList",casePOJOList);
        modelAndView.setViewName("case_list");
        return modelAndView;
    }
}
