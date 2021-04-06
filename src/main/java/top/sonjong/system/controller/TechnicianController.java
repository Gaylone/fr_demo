package top.sonjong.system.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.sonjong.system.API.ITechnicianService;

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
}
