package com.xiaba2.cms.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaba2.cms.domain.Album;
import com.xiaba2.cms.service.AlbumService;
import com.xiaba2.util.ImageUtil;

@RestController
@RequestMapping("/album")
public class AlbumController {

	@Resource
	private AlbumService albumService;

	/**
	 * 上传文件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/page/upfile")
	public ModelAndView upfile() {
		ModelAndView mv = new ModelAndView("upfile");
		return mv;
	}

	/**
	 * 上传图片
	 * 
	 * @return
	 */
	@RequestMapping(value = "/page/upload")
	public ModelAndView upimage() {
		ModelAndView mv = new ModelAndView("upimage");
		return mv;
	}

	@RequestMapping(value = "/uploadFile")
	public ModelAndView uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {

		String path = request.getSession().getServletContext().getRealPath("/") + "upfile";

		// String path = this.getClass().getClassLoader().getResource("/")
		// .getPath();
		//
		// path = path.replace("WEB-INF" + File.separator + "classes"
		// + File.separator, "upload");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		String pdate = sdf.format(new Date());

		path = path + File.separator + pdate;

		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		String uuidName = UUID.randomUUID().toString().replace("-", "");
		String fileName = uuidName + ext;

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

		Logger.getLogger(AlbumController.class.toString()).log(Level.INFO, path);

		String extPath = request.getContextPath() + "/upload/" + pdate + "/";

		Upfile uf = new Upfile();

		uf.setName(fileName);
		uf.setPath(extPath);
		// model.addAttribute("fileUrl", request.getContextPath() + "/upload/"+
		// fileName);

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, Boolean.TRUE);

		String json = "";
		try {

			json = mapper.writeValueAsString(uf);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		ModelAndView mv = new ModelAndView("forward:/album/page/upfile");
		// attr.addFlashAttribute("js", "<script>alert('a')</script>");
		mv.addObject("js", "<script>parent.upfile2('" + json + "')</script>");
		// mv.addObject("js", json);
		// return "<script>parent.upfile('" + json + "');</script>";
		return mv;
	}

	/**
	 * 上传图片处理
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadImage")
	public ModelAndView uploadImage(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {

		String path = request.getSession().getServletContext().getRealPath("/") + "upload";

		// String path = this.getClass().getClassLoader().getResource("/")
		// .getPath();
		//
		// path = path.replace("WEB-INF" + File.separator + "classes"
		// + File.separator, "upload");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		String pdate = sdf.format(new Date());

		path = path + File.separator + pdate;

		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		String uuidName = UUID.randomUUID().toString().replace("-", "");
		String fileName = uuidName + ext;

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

		Logger.getLogger(AlbumController.class.toString()).log(Level.INFO, path);

		// 200 宽
		ImageUtil.scaleThumb(targetFile.getPath(), false, new int[] { 200, 200 });
		ImageUtil.scaleThumb(targetFile.getPath(), true, new int[] { 200, 200 });
		ImageUtil.scaleThumb(targetFile.getPath(), true, new int[] { 240, 180 });
		// 1000 宽
		ImageUtil.scaleThumb(targetFile.getPath(), false, new int[] { 1000, 1000 });

		// String fileName = new Date().getTime()+".jpg";

		String extPath = request.getContextPath() + "/upload/" + pdate + "/";

		Upfile uf = new Upfile();

		uf.setName(fileName);
		uf.setPath(extPath);

		// 覆盖封面
		if (request.getParameter("cover") != null) {
			uf.setCover(1);
		}

		// model.addAttribute("fileUrl", request.getContextPath() + "/upload/"+
		// fileName);

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, Boolean.TRUE);

		String json = "";
		try {

			json = mapper.writeValueAsString(uf);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ModelAndView mv = new ModelAndView("forward:/album/page/upload");
		// attr.addFlashAttribute("js", "<script>alert('a')</script>");
		mv.addObject("js", "<script>parent.upfile('" + json + "')</script>");
		// mv.addObject("js", json);
		// return "<script>parent.upfile('" + json + "');</script>";
		return mv;
	}

	class Upfile {
		private String name;
		private String path;
		private int cover;

		public String getName() {
			return name;
		}

		public String getPath() {
			return path;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public int getCover() {
			return cover;
		}

		public void setCover(int cover) {
			this.cover = cover;
		}

	}

}