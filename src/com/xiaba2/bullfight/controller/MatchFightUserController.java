package com.xiaba2.bullfight.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.bullfight.domain.MatchFightUser;
import com.xiaba2.bullfight.service.MatchFightUserService;
@Controller
@RequestMapping("/matchfightuser")
public class MatchFightUserController {
@Resource
private MatchFightUserService matchFightUserService;
}