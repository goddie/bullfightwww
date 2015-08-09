package com.xiaba2.cms.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.xiaba2.cms.dao.IMemberDao;
import com.xiaba2.cms.domain.Member;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
import com.xiaba2.invest.dao.IUserDao;
import com.xiaba2.invest.domain.User;

@Service
public class MemberService extends BaseService<Member, UUID> {

	@Resource
	private IMemberDao memberDao;

	@Resource
	private IUserDao userDao;

	@Override
	protected IBaseDao<Member, UUID> getEntityDao() {
		return memberDao;
	}

	@Override
	@Transactional
	public Member save(Member entity) {

		if (StringUtils.isEmpty(entity.getUsername()) || StringUtils.isEmpty(entity.getPassword())) {
			throw new RuntimeException();
		}

		DetachedCriteria criteria = memberDao.createDetachedCriteria();
		criteria.add(Restrictions.or(Restrictions.eq("username", entity.getUsername().trim()),
				Restrictions.eq("email", entity.getUsername().trim())));

		List<Member> list = findByCriteria(criteria);

		if (!list.isEmpty()) {
			throw new RuntimeException("用户重复");
		}
		entity.setCreatedDate(new Date());

		entity = memberDao.save(entity);

		User user = new User();
		user.setMember(entity);

		userDao.save(user);

		return entity;
	}

	/**
	 * 根据用户名获取用户
	 * 
	 * @param username
	 * @return
	 */
	@Transactional
	public Member getByUsername(String username) {
		DetachedCriteria criteria = memberDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("username", username.trim()));

		List<Member> list = findByCriteria(criteria);

		if (list == null) {
			return null;
		}

		return list.get(0);

	}
}