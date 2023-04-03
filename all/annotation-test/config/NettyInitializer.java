package com.bb.relay.netty.sample.netty.config;

import org.springframework.stereotype.Component;

import com.bb.relay.netty.sample.netty.controller.NettyMainController;
import com.bb.relay.netty.sample.netty.handler.SocketServerHandler;
import com.bb.relay.netty.sample.netty.handler.interceptor.SampleInterceptor;
import com.bb.relay.netty.sample.netty.nettyAnnotation.NettyAnnotationScanner;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NettyInitializer extends ChannelInitializer<SocketChannel> {

	private final NettyMainController nettyMainController;
	private final NettyAnnotationScanner nettyAnnotationScanner;

	@Override
	public void initChannel(SocketChannel ch) throws Exception {

//		SslContext sslContext = SslContextBuilder.forServer("serverCert", "privateKey").build();

		ChannelPipeline pipeline = ch.pipeline();

//		pipeline.addLast(sslContext.newHandler(ch.alloc()));

		// handler setting
		pipeline.addLast(new SampleInterceptor());
		pipeline.addLast(new SocketServerHandler(nettyMainController, nettyAnnotationScanner));
	}

}