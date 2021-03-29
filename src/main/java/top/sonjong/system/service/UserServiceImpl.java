package top.sonjong.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sonjong.system.API.IUserService;
import top.sonjong.system.dao.IUserDAO;
import top.sonjong.system.domain.POJO.UserPOJO;
import top.sonjong.system.domain.base.PageEntity;
import top.sonjong.system.domain.criteria.UserCriteria;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDAO dao;

    @Override
    public PageEntity<UserPOJO> findPageObject(Integer pageCurrent) {
        // 1.验证参数的合法性
        // 1.1验证pageCurrent的合法性
        // 不合法抛出IllegalArgumentException异常
        if (pageCurrent == null || pageCurrent < 1)
            throw new IllegalArgumentException("当前也显示不正确");
        // 基于条件查询总记录数
        // 2.1执行查询
        int rowCount = dao.getRowCount();
        // 2.2验证查询结果，假如结果为0不在执行如下操作
        if (rowCount == 0)

            System.out.println("没查到");
        // 3.基于条件查询当前页记录(pageSize定义为2)
        // 3.1)定义pageSize
        int pageSize = 5;
        //3.2计算startIndex
        int startIndex=(pageCurrent-1)*pageSize;
        //3.3执行当前数据的查询操作
        List<UserPOJO> records = dao.findPageObjects(startIndex, pageSize);
        //4.对分页信息以及当前页记录进行封装
        //4.1 构建PageObject对象
        PageEntity<UserPOJO> pageObject = new PageEntity<>();
        //4.2封装数据
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(rowCount);
        pageObject.setRecords(records);
        pageObject.setPageCount((rowCount-1)/pageSize+1);
        //5.返回封装结果
        return pageObject;
    }

    @Override
    public int createAccount(UserPOJO userPOJO) {
        return dao.createAccount(userPOJO);
    }

    @Override
    public int frozeAccount(Long uid) {
        return dao.frozeAccount(uid);
    }

    @Override
    public int unfreezeAccount(Long uid) {
        return dao.unfreezeAccount(uid);
    }

    @Override
    public List<UserPOJO> getAllAccount() {

        return dao.getAllAccount();
    }

    @Override
    public List<UserPOJO> getAccountByCondition(UserCriteria userCriteria) {
        return dao.getAccountByCondition(userCriteria);
    }
}
