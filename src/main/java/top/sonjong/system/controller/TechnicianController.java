package top.sonjong.system.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.sonjong.system.API.ITechnicianService;
import top.sonjong.system.domain.POJO.TechnicianPOJO;
import top.sonjong.system.utils.MD5Util;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/techMan")
public class TechnicianController {
    @Autowired
    private ITechnicianService technicianService;

    @RequestMapping(value = "/active",method = RequestMethod.POST)
    @ResponseBody
    public String active(@RequestBody JSONObject param){

        Long tid = param.getLong("tid");
        if (tid==null){
            return 0+"";
        }else {
            return technicianService.activeTechnician(tid)+"";
        }

    }
    @RequestMapping(value = "/freeze",method = RequestMethod.POST)
    @ResponseBody
    public String freeze(@RequestBody JSONObject param){
        Long tid = param.getLong("tid");
        if (tid==null){
            return 0+"";
        }else {
            return technicianService.freezeTechnician(tid)+"";
        }
    }
    @RequestMapping(value = "/changePass",method = RequestMethod.POST)
    @ResponseBody
    public String changePass(@RequestBody JSONObject param, HttpSession session){

        TechnicianPOJO technicianPOJO = (TechnicianPOJO) session.getAttribute("currentUser");
        technicianPOJO.setPswd(MD5Util.generate(param.getString("pswd")));
        return technicianService.updateTechnicianInfo(technicianPOJO)+"";
    }
}
