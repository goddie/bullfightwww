package com.xiaba2.bullfight.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.MatchDataTeam;
import com.xiaba2.bullfight.service.MatchDataTeamService;

@RestController
@RequestMapping("/matchdatateam")
public class MatchDataTeamController {
	@Resource
	private MatchDataTeamService matchDataTeamService;
}