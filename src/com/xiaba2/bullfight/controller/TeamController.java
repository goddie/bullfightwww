package com.xiaba2.bullfight.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.domain.TeamUser;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.bullfight.service.TeamUserService;
import com.xiaba2.cms.controller.AlbumController;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/team")
public class TeamController {
	@Resource
	private TeamService teamService;

	@Resource
	private UserService userService;
	
	@Resource
	private TeamUserService teamUserService;
	
	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_team_" + name);
	}
	
	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList(@RequestParam("p") int p, HttpServletRequest request) {
		
		
		ModelAndView mv=new ModelAndView("admin_team_list");
		
		Page<Team> page = new Page<Team>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		
		DetachedCriteria criteria = teamService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete",0));
		
		page = teamService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml",page.genPageHtml(request));
		return mv;
	}
	
	
	@RequestMapping(value = "/admin/recordlist")
	public ModelAndView pageRecordList(@RequestParam("p") int p, HttpServletRequest request) {
		
		
		ModelAndView mv=new ModelAndView("admin_team_recordlist");
		
		Page<Team> page = new Page<Team>();
		page.setPageSize(50);
		page.setPageNo(p);
		
		DetachedCriteria criteria = teamService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete",0));
		page = teamService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml",page.genPageHtml(request));
		
		return mv;
	}
	
	@RequestMapping(value = "/admin/sel")
	public ModelAndView pageSel() {
		
		
		ModelAndView mv=new ModelAndView("admin_team_sel");
		
		Page<Team> page = new Page<Team>();
		page.setPageSize(999);
		page.setPageNo(1);
		
		DetachedCriteria criteria = teamService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete",0));
		page = teamService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		
		return mv;
	}
	
	
	
	/** 
	 *  球队数据
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/admin/uprecord")
	public ModelAndView adminUprecord(@RequestParam("tid") UUID tid) {
		ModelAndView mv=new ModelAndView("admin_team_uprecord");
		
		Team entity = teamService.get(tid);
		
		mv.addObject("entity", entity);
		
		return mv;
	}
	
	
	
	
	
	@RequestMapping("/add")
	public ModelAndView add(Team entity, HttpServletRequest request) {
		ModelAndView mv=new ModelAndView("redirect:admin/add");

		String uid = request.getParameter("uid");
		User u = userService.get(UUID.fromString(uid));

		TeamUser teamUser=null;
		
		if (u != null) {
			entity.setAdmin(u);
			
			teamUser =new TeamUser();
			teamUser.setUser(u);
			teamUser.setTeam(entity);
			teamUser.setCreatedDate(new Date());
		}


		entity.setCreatedDate(new Date());
		entity.setStatus(1);
		teamService.save(entity);
		if(teamUser!=null)
		{
			teamUserService.save(teamUser);
		}
		

		return mv;
	}

	@RequestMapping("/json/add")
	public JsonResult jsonAdd(Team entity, HttpServletRequest request) {
		JsonResult js = new JsonResult();

		String uid = request.getParameter("uid");
		User u = userService.get(UUID.fromString(uid));

		if (u == null) {
			js.setMsg("创建失败：用户不存在");
			return js;
		}

		entity.setAdmin(u);
		entity.setCreatedDate(new Date());
		entity.setStatus(1);
		teamService.save(entity);

		if (entity.getId() != null) {
			js.setMsg("创建失败");
			return js;
		}
		
		TeamUser teamUser = new TeamUser();
		teamUser.setTeam(entity);
		teamUser.setUser(u);
		teamUser.setCreatedDate(new Date());
 
		teamUserService.save(teamUser);
		

		js.setCode(JsonResult.SUCCESS);
		js.setMsg("创建成功");

		return js;
	}

	/**
	 * 换人
	 * 
	 * @param tid
	 * @param newid
	 * @param oldid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/admin")
	public JsonResult jsonAdm̋in(@RequestParam("tid") String tid,
			@RequestParam("newid") String newid,
			@RequestParam("oldid") String oldid, HttpServletRequest request) {
		JsonResult js = new JsonResult();

		Team team = teamService.get(UUID.fromString(tid));

		User oldu = userService.get(UUID.fromString(oldid));
		User newu = userService.get(UUID.fromString(newid));

		if (team.getAdmin().getId().toString().equals(oldu.getId().toString())) {
			team.setAdmin(newu);
			
			teamService.saveOrUpdate(team);

		}

		js.setCode(JsonResult.SUCCESS);
		js.setMsg("操作成功");

		return js;
	}
	
	@RequestMapping("/json/getteam")
	public JsonResult jsonGetTeam(@RequestParam("tid") String tid) {
		JsonResult js = new JsonResult();

		if(StringUtils.isEmpty(tid))
		{
			return js;
		}
		
		Team team = teamService.get(UUID.fromString(tid));
		js.setData(team);
		js.setCode(JsonResult.SUCCESS);
		return js;
	}
	
	
	
	/**
	 * 上传队标
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/upavatar")
	public JsonResult jsonUpavatar(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {

		JsonResult rs = new JsonResult();



		// String path = System.getProperty("myapp.root") + "upload";
		String path = this.getClass().getClassLoader().getResource("/")
				.getPath();
		path = path.replace("WEB-INF" + File.separator + "classes"
				+ File.separator, "upload");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String pdate = sdf.format(new Date());
		path = path + File.separator + pdate;
		String ext = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf("."));
		String fileName = UUID.randomUUID().toString().replace("-", "") + ext;
		// String fileName = new Date().getTime()+".jpg";
		Logger.getLogger(AlbumController.class.toString())
				.log(Level.INFO, path);
		// System.out.println(path);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String extPath = request.getContextPath() + "/upload/" + pdate + "/";
		
		
		rs.setCode(JsonResult.SUCCESS);
		
		String tid = request.getParameter("tid");
		if (!StringUtils.isEmpty(tid)) {
			Team entity = teamService.get(UUID.fromString(tid));
			entity.setAvatar(extPath+fileName);
			teamService.saveOrUpdate(entity);
			
			rs.setData(entity);
			
			return rs;
		}
		
		
		
		Team t = new Team();
		t.setAvatar(extPath+fileName);
		
		rs.setData(t);
		
		return rs;
	}
	
	/**
	 * 更新队伍资料
	 * @param tid
	 * @return
	 */
	@RequestMapping("/json/update")
	public JsonResult jsonUpdate(@RequestParam("tid") UUID tid,HttpServletRequest request) {
		JsonResult js = new JsonResult();

		
		
		Team team = teamService.get(tid);
		
		
		team.setName(request.getParameter("name"));
		team.setCity(request.getParameter("city"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date birthday;
 
		try {

			birthday = sdf.parse(request.getParameter("found"));
			team.setCreatedDate(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		team.setInfo(request.getParameter("info"));
		
		
		teamService.saveOrUpdate(team);
		
		js.setMsg("修改成功!");
		js.setData(team);
		js.setCode(JsonResult.SUCCESS);
		return js;
	}
	
	
	/**
	 * 创建队伍
	 * @param tid
	 * @return
	 */
	@RequestMapping("/json/create")
	public JsonResult jsonCreate(@RequestParam("uid") UUID uid,HttpServletRequest request) {
		JsonResult js = new JsonResult();
		
		Team team=new Team();
		
		team.setName(request.getParameter("name"));
		team.setCity(request.getParameter("city"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date birthday;
 
		try {

			birthday = sdf.parse(request.getParameter("found"));
			team.setCreatedDate(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		team.setInfo(request.getParameter("info"));
		
		String avatar = request.getParameter("avatar");
		if(!StringUtils.isEmpty(avatar))
		{
			team.setAvatar(avatar);
		}
		
		User user = userService.get(uid);
		team.setAdmin(user);
		
		teamService.save(team);

		js.setMsg("创建成功，快去邀请成员加入队伍吧!");
		js.setData(team);
		js.setCode(JsonResult.SUCCESS);
		return js;
	}
	
	/**
	 * 用户是否队长
	 * @param uid
	 * @return
	 */
	@RequestMapping("/json/checkuser")
	public JsonResult jsonCheckUser(@RequestParam("uid") UUID uid)
	{
		JsonResult rs = new JsonResult();
		
		User user = userService.get(uid);
		
		DetachedCriteria criteria = teamService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete",0));
		criteria.add(Restrictions.eq("admin", user));
		
		
		long count = teamService.getRowCount(criteria);
		
//		List<Team> list = teamService.findByCriteria(criteria);
		
		if(count>0)
		{
			rs.setData(count);
			rs.setCode(JsonResult.SUCCESS);
			return rs;
		}
		
		
		return rs;
	}
	
	
}