package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.IArenaDao;
import com.xiaba2.bullfight.domain.Arena;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class ArenaService extends BaseService<Arena, UUID> {
@Resource
private IArenaDao arenaDao;
@Override
protected IBaseDao<Arena, UUID> getEntityDao() {
return arenaDao;
}
}