package top.sonjong.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.sonjong.system.API.IAquaticProductsService;
import top.sonjong.system.API.IFarmerService;
import top.sonjong.system.API.IIndustryService;
import top.sonjong.system.API.IPondsService;
import top.sonjong.system.domain.POJO.FarmerPOJO;
import top.sonjong.system.domain.POJO.IndustryDetailPOJO;
import top.sonjong.system.domain.POJO.IndustryPOJO;
import top.sonjong.system.domain.POJO.PondsPOJO;
import top.sonjong.system.domain.criteria.FarmerCriteria;
import top.sonjong.system.domain.criteria.IndustryCriteria;
import top.sonjong.system.domain.criteria.PondsCriteria;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/industry")
public class IndustryController {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    IAquaticProductsService aquaticProductsService;
    @Autowired
    IPondsService pondsService;
    @Autowired
    IIndustryService industryService;
    @Autowired
    IFarmerService farmerService;

    @RequestMapping(value = "/getAllProduct",method = RequestMethod.POST)
    @ResponseBody
    private String getAllProduct(){

       return  JSON.toJSONString(aquaticProductsService.getAquaticProducts());
    }
    @RequestMapping(value = "/addIndustry",method = RequestMethod.POST)
    @ResponseBody
    private String addIndustry(@RequestBody String param){
        JSONArray array = JSON.parseArray(param);
        if (array.size()>0){
            System.out.println(array);
            IndustryPOJO industryPOJO = new IndustryPOJO();
            industryPOJO.setI_pid(array.getJSONObject(0).getLong("value"));
            industryPOJO.setTotal_cost(new BigDecimal(array.getJSONObject(array.size()-1).get("value").toString()));
            List<IndustryDetailPOJO> industryDetailPOJOList = new ArrayList<>();
            industryService.addIndustry(industryPOJO);
            IndustryCriteria industryCriteria = new IndustryCriteria();
            industryCriteria.setI_pid(industryPOJO.getI_pid());
            industryCriteria.setStatus(0);
            List<IndustryPOJO> industryPOJOList=industryService.findIndustryByCondition(industryCriteria);
            Long iid = industryPOJOList.get(0).getIid();
            IndustryDetailPOJO industryDetailPOJO=null;
            int check=0;
            for (int i=0;i<array.size()-2;i++){
                if (array.getJSONObject(i+1).getString("value")!=null&&!array.getJSONObject(i+1).getString("value").equals("0")&&
                        !array.getJSONObject(i+1).getString("value").equals("")){
                    industryDetailPOJO = new IndustryDetailPOJO();
                    industryDetailPOJO.setaType(array.getJSONObject(i+1).getString("name"));
                    industryDetailPOJO.setIid(iid);
                    industryDetailPOJO.setPre_num(Integer.parseInt(array.getJSONObject(i+1).getString("value")));
                    industryDetailPOJOList.add(industryDetailPOJO);

                    industryDetailPOJO =null;
                }

            }
                int flag = industryService.addIndustryDetail(industryDetailPOJOList);
                int index = pondsService.changePondStatus(industryPOJO.getI_pid(),1);
            System.out.println(flag+"/"+(array.size()-2)+"/"+index);
            if (flag==industryDetailPOJOList.size()&&index==1){
                return "success";
            }else {
                return "error";
            }

        }else {
            return "error";
        }

    }
    @RequestMapping(value = "/getAllFreePonds",method = RequestMethod.POST )
    @ResponseBody
    public String getAllFreePonds(){
        PondsCriteria criteria=new PondsCriteria();
        criteria.setpStatus("0");
        return JSON.toJSONString(pondsService.findPondByConditions(criteria));
    }

    @RequestMapping(value = "/updateIndustry/{iid}",method = RequestMethod.POST)
    @ResponseBody
    public String updateIndustry(@PathVariable Long iid ,@RequestBody JSONArray param){

        if (param.size()>0){
            int flag=0;


            for (int i=0;i<param.size()/2;i++){
                IndustryDetailPOJO industryDetailPOJO =new IndustryDetailPOJO();
                industryDetailPOJO.setIid(iid);
                industryDetailPOJO.setWeight(new BigDecimal(param.getJSONObject(i*2).get("value").toString()));
                industryDetailPOJO.setUnit_price(new BigDecimal(param.getJSONObject(i*2+1).get("value").toString()));
                industryDetailPOJO.setaType(param.getJSONObject(i*2).get("name").toString());
                flag=flag+industryService.updateIndustryDetail(industryDetailPOJO);
            }

            if (flag==0){
                return "error";
            }else {
               if (flag==param.size()/2){
                   return "success";
               }else {
                   return "issues";
               }

            }
        }else {
            return "error";
        }


    }


    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ResponseBody
    public String detail(@RequestBody JSONObject param){

        Long iid= param.getLong("iid");
        List<IndustryDetailPOJO> industryDetailPOJOList=industryService.getIndustryDetailByID(iid);
        return JSON.toJSONString(industryDetailPOJOList);
    }
    @RequestMapping(value = "/getIndustryByID",method = RequestMethod.POST)
    @ResponseBody
    public String getIndustryByID(@RequestBody JSONObject param){
        IndustryCriteria industryCriteria=JSONObject.toJavaObject(param,IndustryCriteria.class);
        return  JSON.toJSONString(industryService.findIndustryByCondition(industryCriteria));
    }
    @RequestMapping(value = "/updateIndustry",method = RequestMethod.POST)
    @ResponseBody
    public String updateIndustry(@RequestBody JSONObject param){
        //从前端Json装配Industry对象
        IndustryPOJO industryPOJO=JSONObject.toJavaObject(param,IndustryPOJO.class);
        industryPOJO.setHarvestDate(dateFormat.format(param.getDate("harvestDate")));
        BigDecimal totalHarvest=new BigDecimal(0);
        //获取industrydetail集合并迭代，取各个条目的收成重量与单价相乘，获取总收成，装配到industry
        List<IndustryDetailPOJO> industryDetailPOJOList = industryService.getIndustryDetailByID(param.getLong("iid"));
        for (int i = 0; i < industryDetailPOJOList.size(); i++) {
            totalHarvest = totalHarvest.add(industryDetailPOJOList.get(i).getUnit_price().multiply(industryDetailPOJOList.get(i).getWeight()));
        }
        industryPOJO.setTotal_harvest(totalHarvest);
        //查询该industry对应的池塘pond，通过iid查ipid
        IndustryCriteria industryCriteria = new IndustryCriteria();
        industryCriteria.setIid(param.getLong("iid"));
        List<IndustryPOJO> industryPOJOList=  industryService.findIndustryByCondition(industryCriteria);
        if (industryPOJOList.get(0).getI_pid()!=null){
            //池塘存在则完成产业
            int flag= industryService.updateIndustry(industryPOJO);
            //然后将池塘状态释放为自由闲置态，方便下次投产
            int index =pondsService.changePondStatus(industryPOJOList.get(0).getI_pid(),0);
            if (flag==1&&index==1){
                return "success";
            }else {
                return "error";
            }
        }else {
            return "error";
        }

    }
    @RequestMapping(value = "/detail/{iid}",method = RequestMethod.POST)
    @ResponseBody
    public String getIndustryDetailByID(@PathVariable("iid")Long iid){
        //通过iid获取industry，industryDetail，ponds
        //通过ponds获取farmer
        JSONObject jsonObject = new JSONObject();
        IndustryCriteria industryCriteria = new IndustryCriteria();
        industryCriteria.setIid(iid);
        List<IndustryPOJO> industryPOJOList = industryService.findIndustryByCondition(industryCriteria);
        if (industryPOJOList.size()==0){
            return null;
        }else {
            //Json封装Industry
            jsonObject.put("totalHarvest",industryPOJOList.get(0).getTotal_harvest());
            jsonObject.put("totalCost",industryPOJOList.get(0).getTotal_cost());
            jsonObject.put("harvestDatetime",industryPOJOList.get(0).getHarvestDate());
            //Json封装industryDetail
            List<IndustryDetailPOJO> industryDetailPOJOList = industryService.getIndustryDetailByID(iid);
            jsonObject.put("industryDetailPOJOList",industryDetailPOJOList);
            //json封装Pond
            Long pid =   industryPOJOList.get(0).getI_pid();
            PondsCriteria pondsCriteria = new PondsCriteria();
            pondsCriteria.setPid(pid);
            List<PondsPOJO> pondsPOJOList = pondsService.findPondByConditions(pondsCriteria);
            if (pondsPOJOList.size()==0){
                jsonObject.put("pName","");
                jsonObject.put("fName","");
            }else {
                jsonObject.put("pName",pondsPOJOList.get(0).getpName());
                //json封装Farmer
                Long fid = pondsPOJOList.get(0).getP_fid();
                FarmerCriteria farmerCriteria = new FarmerCriteria();
                farmerCriteria.setFid(fid);
                List<FarmerPOJO> farmerPOJOList = farmerService.findFarmersByConditions(farmerCriteria);
                if (farmerPOJOList.size()==0){
                    jsonObject.put("fName","");
                }else {
                    jsonObject.put("fName",farmerPOJOList.get(0).getfName());
                }
            }

        }
        return JSON.toJSONString(jsonObject);
    }

}
