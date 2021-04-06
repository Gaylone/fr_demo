package top.sonjong.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.sonjong.system.API.ICaseService;
import top.sonjong.system.domain.POJO.CasePOJO;
import top.sonjong.system.domain.POJO.TechnicianPOJO;
import top.sonjong.system.domain.base.WangEditor;
import top.sonjong.system.domain.criteria.CaseCriteria;
import top.sonjong.system.utils.ImgUploadUtil.ImgUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/case")
public class CaseController {
    private static final Logger logger = LoggerFactory.getLogger(CaseController.class);
    @Autowired
    private ICaseService caseService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/uploadImg",method= RequestMethod.POST)
    @ResponseBody
    public WangEditor upload(@RequestParam("myFile") MultipartFile multipartFile, HttpServletRequest request) {

        String url = ImgUpload.upload(multipartFile,request);

        WangEditor we = new WangEditor(url);
        return we;

    }
    @RequestMapping(value = "/saveCase",method = RequestMethod.POST)
    @ResponseBody
    public String saveCase(@RequestBody JSONObject param, HttpSession session){
        CasePOJO casePOJO = param.toJavaObject(CasePOJO.class);
        TechnicianPOJO technicianPOJO = (TechnicianPOJO) session.getAttribute("currentUser");

        casePOJO.setDate(dateFormat.format(new Date()));
        casePOJO.setC_tid(technicianPOJO.getTid());
        int flag = caseService.saveCase(casePOJO);
        if (flag==1){

            return "success";
        }else {
            return "error";
        }
    }
    @RequestMapping(value = "/getCaseByCID/{cid}",method = RequestMethod.POST)
    @ResponseBody
    public String getCaseByCID(@PathVariable Long cid){
        CaseCriteria caseCriteria = new CaseCriteria();
        caseCriteria.setCid(cid);
        CasePOJO casePOJO = caseService.getCaseByCondition(caseCriteria).get(0);
        logger.info("记录日志：通过ID获取病历{}",cid);
        return JSON.toJSONString(casePOJO);
    }
    @RequestMapping(value = "/countCase",method = RequestMethod.POST)
    @ResponseBody
    public String countCase(){
        return caseService.countCase()+"";
    }
}
