package com.xiaba2.bullfight.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.bullfight.service.MatchDataUserService;

@RestController
@RequestMapping("/matchdatauser")
public class MatchDataUserController {
	@Resource
	private MatchDataUserService matchDataUserService;
}