package top.sonjong.system.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.sonjong.system.API.IAquaticProductsService;
import top.sonjong.system.domain.POJO.AquaticProductsPOJO;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IAquaticProductsService aquaticProductsService;
    @RequestMapping(value = "/queryProductByConditions",method = RequestMethod.POST)
    @ResponseBody
    public String queryProductByConditions(@RequestBody JSONObject param){
        List<AquaticProductsPOJO> list=aquaticProductsService.getAquaticProductsByCondition(param.getString("aType"));
        if (list.size()==0){
            return "0";
        }else {
            return list.size()+"";
        }
    }
    @RequestMapping(value = "/saveProduct",method = RequestMethod.POST)
    @ResponseBody
    public String saveProduct(@RequestBody JSONObject param){
        AquaticProductsPOJO aquaticProductsPOJO=param.toJavaObject(AquaticProductsPOJO.class);
        int i= aquaticProductsService.addAquaticProducts(aquaticProductsPOJO);
        return i+"";
    }
    @RequestMapping(value = "/countProducts",method = RequestMethod.POST)
    @ResponseBody
    public String countProducts(){
        return aquaticProductsService.countProducts()+"";
    }
}
