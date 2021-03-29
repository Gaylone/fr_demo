package top.sonjong.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sonjong.system.API.ICaseService;
import top.sonjong.system.dao.ICaseDAO;
import top.sonjong.system.dao.IDiseaseDAO;
import top.sonjong.system.dao.IPondsDAO;
import top.sonjong.system.dao.ITechnicianDAO;
import top.sonjong.system.domain.POJO.CasePOJO;
import top.sonjong.system.domain.criteria.*;

import java.util.List;

@Service
public class CaseServiceImpl implements ICaseService {
    @Autowired
    private ICaseDAO caseDAO;
    @Autowired
    private IDiseaseDAO diseaseDAO;
    @Autowired
    private ITechnicianDAO technicianDAO;
    @Autowired
    private IPondsDAO pondsDAO;

    @Override
    public int saveCase(CasePOJO casePOJO) {
        if (casePOJO.getC_did()==null||casePOJO.getC_pid()==null||casePOJO.getC_tid()==null){
            System.out.println("疾病id/池塘id/技术人员id不能为空！");
            return -1;
        }
        TechnicianCriteria technicianCriteria=new TechnicianCriteria();
        technicianCriteria.setTid(casePOJO.getC_tid());
        PondsCriteria pondsCriteria=new PondsCriteria();
        pondsCriteria.setPid(casePOJO.getC_pid());
        DiseaseCriteria diseaseCriteria=new DiseaseCriteria();
        diseaseCriteria.setDid(casePOJO.getC_did());
        if (diseaseDAO.findDiseaseByCondition(diseaseCriteria).size()<1||
            pondsDAO.findPondByConditions(pondsCriteria).size()<1||
                technicianDAO.findTechnicianByConditions(technicianCriteria).size()<1
        ){
           if (diseaseDAO.findDiseaseByCondition(diseaseCriteria).size()<1){
               System.out.println("该疾病不存在");
           }
           if (pondsDAO.findPondByConditions(pondsCriteria).size()<1){
               System.out.println("该池塘不存在");
           }
           if (technicianDAO.findTechnicianByConditions(technicianCriteria).size()<1){
               System.out.println("该技术员不存在");
           }
            return -2;
        }
        if (casePOJO.getContent()==null){
            System.out.println("内容不能为空");
            return -3;
        }
        if (casePOJO.getDate()==null){
            System.out.println("时间不能为空");
            return -3;
        }
        if (casePOJO.getSolution()==null){
            System.out.println("解决方案不能为空");
            return -3;
        }
        if (casePOJO.getTitle()==null){
            System.out.println("标题不能为空");
            return -3;
        }


        return caseDAO.saveCase(casePOJO);
    }

    @Override
    public List<CasePOJO> getCaseByCondition(CaseCriteria caseCriteria) {
        return caseDAO.getCaseByCondition(caseCriteria);
    }


    @Override
    public List<CasePOJO> getAllCase() {
        return caseDAO.getAllCase();
    }

    @Override
    public int countCase() {
        return caseDAO.countCase();
    }

}
