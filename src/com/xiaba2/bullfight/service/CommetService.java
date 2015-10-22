package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.ICommetDao;
import com.xiaba2.bullfight.domain.Commet;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class CommetService extends BaseService<Commet, UUID> {
@Resource
private ICommetDao commetDao;
@Override
protected IBaseDao<Commet, UUID> getEntityDao() {
return commetDao;
}
}