package com.bb.relay.netty.sample.netty.controller;

import org.springframework.stereotype.Controller;

import com.bb.relay.netty.sample.netty.common.UrlObject;
import com.bb.relay.netty.sample.netty.nettyAnnotation.NettyAnnotation;

import api.gateway.constant.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NettyMainController {
	
	private final ApiService service;
	
	@NettyAnnotation(url = "/asdfasdf")
	public String testMethod1(UrlObject obj) {
		log.info("obj {} {}", obj.getUrl(), obj.getObject());
		
		return "test@@";
	}
	
	@NettyAnnotation(url = "/qwerqwer")
	public String testMethod2(UrlObject obj) {
		log.info("obj {} {}", obj.getUrl(), obj.getObject());
		
		return "test@@";
	}
	
}
