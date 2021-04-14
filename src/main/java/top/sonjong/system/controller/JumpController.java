package top.sonjong.system.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.sonjong.system.API.*;
import top.sonjong.system.domain.POJO.*;
import top.sonjong.system.utils.MD5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class JumpController {
    private static final Logger logger = LoggerFactory.getLogger(JumpController.class);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

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
    @Autowired
    private ITechnicianService technicianService;

    @RequestMapping("/index_home")
    public String toIndexHome(){
        return "index_home";
    }

    @RequestMapping("/login")
    public String toLogin(HttpSession session){
        session.invalidate();
        return "login";
    }
    @RequestMapping("/portal")
    public String userLogin(String account,String pswd, HttpSession session, HttpServletResponse response) throws IOException {
        TechnicianPOJO currentUser = technicianService.userLogin(account);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(currentUser==null){
            out.write("<script>alert('用户不存在" +
                    "')</script>");
            return "login";
        }
        if(MD5Util.verify(pswd, currentUser.getPswd())){
            session.setAttribute("currentUser",currentUser);
            logger.info(dateFormat.format(new Date())+"用户登录，{}", JSON.toJSONString(currentUser));
            return "index";
        }else{
            out.write("<script>alert('用户名或密码错误')</script>");
            return "redirect:login";
        }
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
    @RequestMapping("/private")
    public String toAdmin(HttpSession session, HttpServletResponse response, HttpServletRequest request)throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        TechnicianPOJO technicianPOJO = (TechnicianPOJO) session.getAttribute("currentUser");
        if (technicianPOJO.getRole()==0){
            out.write("<script>alert('抱歉，您无权访问此页面')</script>");

            return "redirect:login";
        }else {
            return "redirect:../admin";
        }

    }
    @RequestMapping("/admin")
    public ModelAndView toAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        List<TechnicianPOJO>  technicianPOJOList=technicianService.getAllTechnicians();
        modelAndView.addObject("technicianPOJOList",technicianPOJOList);
        modelAndView.setViewName("administrator");
        return modelAndView;
    }
}
