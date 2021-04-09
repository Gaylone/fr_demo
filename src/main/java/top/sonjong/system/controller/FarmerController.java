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

    @ResponseBody
    @RequestMapping(value = "/freeze/{fid}",method = RequestMethod.POST)
    public String freezeFarmer(@PathVariable Long fid){
        return farmerService.changeFarmerStatus(fid,1)+"";
    }
    @ResponseBody
    @RequestMapping(value = "/active/{fid}",method = RequestMethod.POST)
    public String activeFarmer(@PathVariable Long fid){
        return farmerService.changeFarmerStatus(fid,0)+"";
    }
    @ResponseBody
    @RequestMapping(value = "/getFarmer/{fid}",method = RequestMethod.POST)
    public String getFarmerByID(@PathVariable Long fid){
        FarmerCriteria farmerCriteria =new FarmerCriteria();
        farmerCriteria.setFid(fid);
        return JSON.toJSONString(farmerService.findFarmersByConditions(farmerCriteria));
    }
    @ResponseBody
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    public String updateInfo(@RequestBody JSONObject param){
        FarmerPOJO farmerPOJO = param.toJavaObject(FarmerPOJO.class);

        return farmerService.updateFarmerInfo(farmerPOJO)+"";
    }
}
