package top.sonjong.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.sonjong.system.domain.POJO.IndustryDetailPOJO;
import top.sonjong.system.domain.POJO.IndustryPOJO;
import top.sonjong.system.domain.criteria.IndustryCriteria;

import java.util.List;

@Mapper
@Repository
public interface IIndustryDAO {
    int addIndustry(IndustryPOJO industryPOJO);
    List<IndustryPOJO> getAllIndustry();
    int updateIndustry(IndustryPOJO industryPOJO);
    List<IndustryPOJO> findIndustryByCondition(IndustryCriteria industryCriteria);
    int addIndustryDetail(@Param("List")List<IndustryDetailPOJO> industryDetailPOJO);
    int updateIndustryDetail(IndustryDetailPOJO industryDetailPOJO);
    List<IndustryDetailPOJO> getIndustryDetailByID(Long iid);
    int countIndustry();

}
