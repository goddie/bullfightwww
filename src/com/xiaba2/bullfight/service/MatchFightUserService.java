package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.IMatchFightUserDao;
import com.xiaba2.bullfight.domain.MatchFightUser;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class MatchFightUserService extends BaseService<MatchFightUser, UUID> {
@Resource
private IMatchFightUserDao matchFightUserDao;
@Override
protected IBaseDao<MatchFightUser, UUID> getEntityDao() {
return matchFightUserDao;
}
}