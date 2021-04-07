package top.sonjong.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.sonjong.system.API.IFarmerService;
import top.sonjong.system.API.IPondsService;
import top.sonjong.system.domain.POJO.FarmerPOJO;
import top.sonjong.system.domain.POJO.PondsPOJO;
import top.sonjong.system.domain.criteria.FarmerCriteria;
import top.sonjong.system.domain.criteria.PondsCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/ponds")
public class PondsController {

    private static final Logger logger = LoggerFactory.getLogger(PondsController.class);
    @Autowired
    private IPondsService pondsService;
    @Autowired
    private IFarmerService farmerService;

    @RequestMapping(value = "/getFarmerByIDNum",method = RequestMethod.POST)
    @ResponseBody
    public String getFarmerByIDNum(@RequestBody JSONObject param){
        FarmerCriteria farmerCriteria = new FarmerCriteria();
        farmerCriteria.setfIdNum(param.getString("IDNum"));
        List<FarmerPOJO> farmerPOJOList=farmerService.findFarmersByConditions(farmerCriteria);
        logger.info("通过身份证获取养殖户信息，参数{}",param.toJSONString());
        return JSON.toJSONString(farmerPOJOList);
    }
    @RequestMapping(value = "/addPond",method = RequestMethod.POST)
    @ResponseBody
    public String addPond(@RequestBody JSONObject param){
        PondsPOJO pondsPOJO = JSONObject.toJavaObject(param,PondsPOJO.class);
        logger.info("新增池塘，参数{}",param.toJSONString());
        return pondsService.addPond(pondsPOJO)+"";
    }

    @RequestMapping(value = "/getPondsNum",method = RequestMethod.GET)
    @ResponseBody
    public String getPondsNum(){
        logger.info("获取池塘数量");
        return pondsService.countAllPond()+"";
    }

    @RequestMapping(value = "/getPondsByCondition",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView getPondsByCondition(PondsCriteria pondsCriteria, HttpServletRequest request, HttpServletResponse response){
        ModelAndView model = new ModelAndView();
        logger.info("池塘高级查询，参数{}",JSON.toJSONString(pondsCriteria));
        List<PondsPOJO> pondsPOJOList = pondsService.findPondByConditions(pondsCriteria);
        model.addObject("pondsPOJOList",pondsPOJOList);
        model.setViewName("ponds_Manage");
        return model;
    }
    @RequestMapping(value = "/getPondsByFID",method = RequestMethod.POST)
    @ResponseBody
    public String getPondsByFID(@RequestBody JSONObject param){
        PondsCriteria pondsCriteria = param.toJavaObject(PondsCriteria.class);
        logger.info("根据ID获取池塘信息：{}",param.toJSONString());
        return JSON.toJSONString(pondsService.findPondByConditions(pondsCriteria));
    }
    @RequestMapping(value = "/filled/{pid}",method = RequestMethod.POST)
    @ResponseBody
    public String filledPondByID(@PathVariable Long pid){
        logger.info("根据ID填埋池塘：{}",pid);
       return pondsService.changePondStatus(pid,2)+"";
    }
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    @ResponseBody
    public String filledPondByID(@RequestBody JSONObject param){
      PondsPOJO pondsPOJO = param.toJavaObject(PondsPOJO.class);
        logger.info("更新池塘信息：{}",param.toJSONString());
        return pondsService.updatePondInfo(pondsPOJO)+"";
    }
}
