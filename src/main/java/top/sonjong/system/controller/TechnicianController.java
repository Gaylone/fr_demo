package top.sonjong.system.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.sonjong.system.API.ITechnicianService;
import top.sonjong.system.domain.POJO.TechnicianPOJO;
import top.sonjong.system.utils.MD5Util;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/techMan")
public class TechnicianController {
    private static final Logger logger = LoggerFactory.getLogger(TechnicianController.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    @Autowired
    private ITechnicianService technicianService;

    @RequestMapping(value = "/active",method = RequestMethod.POST)
    @ResponseBody
    public String active(@RequestBody JSONObject param){

        Long tid = param.getLong("tid");
        if (tid==null){
            return 0+"";
        }else {
            logger.info(dateFormat.format(new Date())+"启用账号,数据{}",param);
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
            logger.info(dateFormat.format(new Date())+"冻结账户,数据{}",param);
            return technicianService.freezeTechnician(tid)+"";
        }
    }
    @RequestMapping(value = "/changePass",method = RequestMethod.POST)
    @ResponseBody
    public String changePass(@RequestBody JSONObject param, HttpSession session){

        TechnicianPOJO technicianPOJO = (TechnicianPOJO) session.getAttribute("currentUser");
        technicianPOJO.setPswd(MD5Util.generate(param.getString("pswd")));
        logger.info(dateFormat.format(new Date())+"修改密码");
        return technicianService.updateTechnicianInfo(technicianPOJO)+"";
    }
}
