package top.sonjong.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sonjong.system.API.IPondsService;
import top.sonjong.system.dao.IPondsDAO;
import top.sonjong.system.domain.POJO.PondsPOJO;
import top.sonjong.system.domain.criteria.PondsCriteria;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PondsServiceImpl implements IPondsService {
    @Autowired
    private IPondsDAO pondsDAO;
    @Override
    public int addPond(PondsPOJO pondsPOJO) {
        if (pondsPOJO.getP_fid() == null) {
            return -1;
        }
        if (pondsPOJO.getpSize() == null) {
            pondsPOJO.setpSize(new BigDecimal(0));
        }
        return pondsDAO.addPond(pondsPOJO);
    }
    @Override
    public int updatePondInfo(PondsPOJO pondsPOJO) {
        if (pondsPOJO.getPid() == null) {
            return -1;
        } else {
            PondsCriteria criteria = new PondsCriteria();
            criteria.setPid(pondsPOJO.getPid());
            int size = pondsDAO.findPondByConditions(criteria).size();
            if (size == 0) {
                return -2;
            }
            if (size == 1) {
                return pondsDAO.updatePondInfo(pondsPOJO);
            }
        }
        return 0;
    }

    @Override
    public int changePondStatus(Long pid, Integer status) {
        //status 0闲置 1投产  2填埋
        if (status == 0 || status == 1 || status == 2) {
            if (status == 0) {
                return pondsDAO.activePond(pid);
            }
            if (status == 1) {
                return pondsDAO.freezePond(pid);
            }
            if (status == 2) {
                return pondsDAO.fillPond(pid);
            }
        }
        return -1;
    }

    @Override
    public int countAllPond() {
        return pondsDAO.countAllPond();
    }

    @Override
    public List<PondsPOJO> getAllPond() {
        return pondsDAO.getAllPond();
    }

    @Override
    public List<PondsPOJO> findPondByConditions(PondsCriteria pondsCriteria) {
        if (pondsCriteria.getMaxSize() == null) {
            pondsCriteria.setMaxSize(new BigDecimal(999999999.9));
        }
        if (pondsCriteria.getMinSize() == null) {
            pondsCriteria.setMinSize(new BigDecimal(0));
        }
        return pondsDAO.findPondByConditions(pondsCriteria);
    }
}
