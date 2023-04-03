package com.bb.relay.netty.sample.netty.controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Controller;

import com.bb.relay.netty.sample.netty.config.SampleNettySocketServer;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SampleNettyController {

	private final SampleNettySocketServer nettySocketServer;
	
	@PostConstruct
	private void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					nettySocketServer.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}).start();
	}

	@PreDestroy
	private void destory() {

	}

}