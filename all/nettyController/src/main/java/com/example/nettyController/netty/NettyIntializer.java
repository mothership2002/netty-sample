package com.example.nettyController.netty;

import org.springframework.stereotype.Component;

import com.example.nettyController.gateway.Gateway;
import com.example.nettyController.handler.NettySocketServerHandler;
import com.example.nettyController.handler.decode.NettyEncodingInterceptor;
import com.example.nettyController.handler.encode.NettyDecodingInterceptor;
import com.example.nettyController.handler.interceptor.NettyInterceptor;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NettyIntializer extends ChannelInitializer<SocketChannel> {
	
	private final Gateway gateway;
	
	@Override
	public void initChannel(SocketChannel ch) throws Exception {

//		SslContext sslContext = SslContextBuilder.forServer("serverCert", "privateKey").build();
		
		ChannelPipeline pipeline = ch.pipeline();
		
//		pipeline.addLast(sslContext.newHandler(ch.alloc()));
		
		// handler setting
		pipeline.addLast(new NettyInterceptor());
		pipeline.addLast(new NettyDecodingInterceptor()); // ssl 사용 시 의미X
		pipeline.addLast(new NettySocketServerHandler(gateway));
		pipeline.addLast(new NettyEncodingInterceptor()); // ssl 사용 시 의미X
		
	}

}
