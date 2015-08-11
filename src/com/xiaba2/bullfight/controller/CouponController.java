package com.xiaba2.bullfight.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.Coupon;
import com.xiaba2.bullfight.service.CouponService;

@RestController
@RequestMapping("/coupon")
public class CouponController {
	@Resource
	private CouponService couponService;
}