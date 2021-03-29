package top.sonjong.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

        return JSON.toJSONString(farmerPOJOList);
    }
    @RequestMapping(value = "/addPond",method = RequestMethod.POST)
    @ResponseBody
    public String addPond(@RequestBody JSONObject param){
        PondsPOJO pondsPOJO = JSONObject.toJavaObject(param,PondsPOJO.class);

        return pondsService.addPond(pondsPOJO)+"";
    }

    @RequestMapping(value = "/getPondsNum",method = RequestMethod.GET)
    @ResponseBody
    public String getPondsNum(){

        return pondsService.countAllPond()+"";
    }

    @RequestMapping(value = "/getPondsByCondition",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView getPondsByCondition(PondsCriteria pondsCriteria, HttpServletRequest request, HttpServletResponse response){
        ModelAndView model = new ModelAndView();

        //PondsCriteria pondsCriteria=JSONObject.toJavaObject(param,PondsCriteria.class);
        List<PondsPOJO> pondsPOJOList = pondsService.findPondByConditions(pondsCriteria);
        model.addObject("pondsPOJOList",pondsPOJOList);
        model.setViewName("ponds_Manage");
        return model;
    }
    @RequestMapping(value = "/getPondsByFID",method = RequestMethod.POST)
    @ResponseBody
    public String getPondsByFID(@RequestBody JSONObject param){
        PondsCriteria pondsCriteria = param.toJavaObject(PondsCriteria.class);
        return JSON.toJSONString(pondsService.findPondByConditions(pondsCriteria));
    }

}
