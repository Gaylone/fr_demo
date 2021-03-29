package top.sonjong.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.sonjong.system.API.IFarmerService;
import top.sonjong.system.domain.POJO.FarmerPOJO;
import top.sonjong.system.domain.criteria.FarmerCriteria;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/farmer")
public class FarmerController {
    @Autowired
    private IFarmerService farmerService;

    @ResponseBody
    @RequestMapping("/getAllFarmer")
    public String getAllFarmer(){

        List<FarmerPOJO> farmerPOJOList=farmerService.getAllFarmers();

        return JSON.toJSONString(farmerPOJOList);
    }
    @ResponseBody
    @RequestMapping(value = "/queryFarmerIDNum",method = RequestMethod.POST)
    public String queryFarmerIDNum(@RequestBody JSONObject param){
        FarmerCriteria criteria=new FarmerCriteria();
        criteria.setfIdNum(param.get("IDNum").toString());
        List<FarmerPOJO> farmerPOJOList=farmerService.findFarmersByConditions(criteria);
        if (farmerPOJOList.size()>0){
            return "-1";
        }else {
            return "0";
        }

    }
    @ResponseBody
    @RequestMapping(value = "/saveFarmer",method = RequestMethod.POST)
    public String saveFarmer(@RequestBody JSONObject param){
        FarmerPOJO farmerPOJO = JSONObject.toJavaObject(param,FarmerPOJO.class);


      return farmerService.addFarmer(farmerPOJO)+"";

    }
    @ResponseBody
    @RequestMapping(value = "/countFarmer",method = RequestMethod.POST)
    public String countFarmer(){
        return farmerService.countFarmerNum()+"";
    }
}
