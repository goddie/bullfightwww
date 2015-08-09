package com.xiaba2.cms.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaba2.cms.domain.Album;
import com.xiaba2.cms.service.AlbumService;

@RestController
@RequestMapping("/album")
public class AlbumController {

	@Resource
	private AlbumService albumService;

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/upload")
	public ModelAndView upload(
			@RequestParam(value = "file", required = false) MultipartFile file) {

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

		String extPath = "upload/" + pdate + "/";

		Upfile uf = new Upfile();

		uf.setName(fileName);
		uf.setPath(extPath);

		// model.addAttribute("fileUrl", request.getContextPath() + "/upload/"+
		// fileName);

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
				Boolean.TRUE);
		
		String json = "";
		try {
			json = mapper.writeValueAsString(uf);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mv=new ModelAndView();

		//return "<script>parent.upfile('" + json + "');</script>";
		return mv;
	}

	class Upfile {
		private String name;
		private String path;

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
	}

}