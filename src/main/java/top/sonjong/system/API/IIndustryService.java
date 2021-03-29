package top.sonjong.system.API;

import top.sonjong.system.domain.POJO.IndustryDetailPOJO;
import top.sonjong.system.domain.POJO.IndustryPOJO;
import top.sonjong.system.domain.criteria.IndustryCriteria;

import java.util.List;

public interface IIndustryService {

    int addIndustry(IndustryPOJO industryPOJO);
    List<IndustryPOJO> getAllIndustry();
    int updateIndustry(IndustryPOJO industryPOJO);
    List<IndustryPOJO> findIndustryByCondition(IndustryCriteria industryCriteria);
    int addIndustryDetail(List<IndustryDetailPOJO> industryDetailPOJO);
    int updateIndustryDetail(IndustryDetailPOJO industryDetailPOJO);
    int countIndustry();
    List<IndustryDetailPOJO> getIndustryDetailByID(Long iid);
}
