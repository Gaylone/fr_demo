package top.sonjong.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.sonjong.system.domain.POJO.UserPOJO;
import top.sonjong.system.domain.criteria.UserCriteria;

import java.util.List;

@Mapper
@Repository
public interface IUserDAO {
    List<UserPOJO> findPageObjects(
            @Param("startIndex")Integer startIndex,
            @Param("pageSize")Integer pageSize
    );
    int getRowCount();
    int createAccount(UserPOJO userPOJO);
    int frozeAccount(@Param("uid") Long uid);
    int unfreezeAccount(@Param("uid") Long uid);
    List<UserPOJO>  getAllAccount();
    List<UserPOJO>  getAccountByCondition(UserCriteria userCriteria);
}
