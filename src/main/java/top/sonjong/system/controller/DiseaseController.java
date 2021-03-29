package top.sonjong.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.sonjong.system.API.IDiseaseService;
import top.sonjong.system.domain.POJO.DiseasePOJO;

@Controller
@RequestMapping("/diseases")
public class DiseaseController {
    @Autowired
    IDiseaseService diseaseService;

    @RequestMapping(value = "/getDiseaseByName",method = RequestMethod.POST)
    @ResponseBody
    public String getDiseaseByName(@RequestBody JSONObject param){
       String dName= param.getString("dName");
       return diseaseService.checkName(dName)+"";

    }
    @RequestMapping(value = "/saveDisease",method = RequestMethod.POST)
    @ResponseBody
    public String saveDisease(@RequestBody JSONObject param){
        DiseasePOJO diseasePOJO = param.toJavaObject(DiseasePOJO.class);
        int flag=diseaseService.addDisease(diseasePOJO);
        if (flag==1){
            return "success";
        }else {
            return "error";
        }
    }
    @RequestMapping(value = "/getAllDisease",method = RequestMethod.POST)
    @ResponseBody
    public String getAllDisease(){
      return JSON.toJSONString(diseaseService.getAllDisease());
    }
    @RequestMapping(value = "/countDiseases",method = RequestMethod.POST)
    @ResponseBody
    public String countDiseases(){
        return diseaseService.countAllDisease()+"";
    }
}
