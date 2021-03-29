package top.sonjong.system.API;

import top.sonjong.system.domain.POJO.UserPOJO;
import top.sonjong.system.domain.base.PageEntity;
import top.sonjong.system.domain.criteria.UserCriteria;

import java.util.List;

public interface IUserService {
    PageEntity<UserPOJO> findPageObject(Integer pageCurrent);
    int createAccount(UserPOJO userPOJO);
    int frozeAccount(Long uid);
    int unfreezeAccount(Long uid);
    List<UserPOJO> getAllAccount();
    List<UserPOJO>  getAccountByCondition(UserCriteria userCriteria);
}
