package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.IMatchDataUserDao;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class MatchDataUserService extends BaseService<MatchDataUser, UUID> {
@Resource
private IMatchDataUserDao matchDataUserDao;
@Override
protected IBaseDao<MatchDataUser, UUID> getEntityDao() {
return matchDataUserDao;
}
}