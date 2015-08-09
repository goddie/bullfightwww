package com.xiaba2.invest.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaba2.invest.dao.IUserDao;
import com.xiaba2.invest.domain.User;
import com.xiaba2.cms.domain.Member;
import com.xiaba2.cms.service.MemberService;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class UserService extends BaseService<User, UUID> {
	
	
	@Resource
	private IUserDao userDao;

	@Override
	protected IBaseDao<User, UUID> getEntityDao() {
		return userDao;
	}
	
	@Resource
	private MemberService memberService;

	/**
	 * 通过Member 获取
	 * @param member
	 * @return
	 */
	@Transactional
	public User getByMember(Member member) {
		DetachedCriteria criteria = userDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("member", member));

		List<User> list = findByCriteria(criteria);

		if (list.isEmpty()) {
			return null;
		}

		return list.get(0);
	}

	
	/**
	 * 通过uuid获取
	 * @param uuid
	 * @return
	 */
	@Transactional
	public User getByMemberUUID(String uuid) {
		UUID id = UUID.fromString(uuid);
		Member member = memberService.get(id);
		return getByMember(member);
	}

}