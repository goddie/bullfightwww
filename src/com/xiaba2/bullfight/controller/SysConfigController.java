package com.xiaba2.bullfight.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.bullfight.domain.SysConfig;
import com.xiaba2.bullfight.service.SysConfigService;
@Controller
@RequestMapping("/sysconfig")
public class SysConfigController {
@Resource
private SysConfigService sysConfigService;
}