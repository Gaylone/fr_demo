package top.sonjong.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sonjong.system.API.IIndustryService;
import top.sonjong.system.dao.IIndustryDAO;
import top.sonjong.system.domain.POJO.IndustryDetailPOJO;
import top.sonjong.system.domain.POJO.IndustryPOJO;
import top.sonjong.system.domain.criteria.IndustryCriteria;

import java.util.List;

@Service
public class IndustryServiceImpl implements IIndustryService {
    @Autowired
    IIndustryDAO industryDAO;
    @Override
    public int addIndustry(IndustryPOJO industryPOJO) {

        return industryDAO.addIndustry(industryPOJO);
    }

    @Override
    public List<IndustryPOJO> getAllIndustry() {
        return industryDAO.getAllIndustry();
    }

    @Override
    public int updateIndustry(IndustryPOJO industryPOJO) {
        return industryDAO.updateIndustry(industryPOJO);
    }

    @Override
    public List<IndustryPOJO> findIndustryByCondition(IndustryCriteria industryCriteria) {

        return industryDAO.findIndustryByCondition(industryCriteria);
    }

    @Override
    public int addIndustryDetail(List<IndustryDetailPOJO> industryDetailPOJO) {
        return industryDAO.addIndustryDetail(industryDetailPOJO);
    }

    @Override
    public int updateIndustryDetail(IndustryDetailPOJO industryDetailPOJO) {
        return industryDAO.updateIndustryDetail(industryDetailPOJO);
    }

    @Override
    public int countIndustry() {
        return industryDAO.countIndustry();
    }

    @Override
    public List<IndustryDetailPOJO> getIndustryDetailByID(Long iid ) {
        return industryDAO.getIndustryDetailByID(iid);
    }
}
