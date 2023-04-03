package com.example.nettyController.controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Controller;

import com.example.nettyController.netty.NettySocketServer;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NettyController {

//	private final Gateway gateway;
	private final NettySocketServer nettySocketServer;
	
	@PostConstruct
	private void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
//					new NettySocketServer(8888, gateway).run();
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
