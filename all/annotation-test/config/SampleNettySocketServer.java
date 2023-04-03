package com.bb.relay.netty.sample.netty.config;

//import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class SampleNettySocketServer {
	
    @Value("${socket.server.host}")
    private String host;
    @Value("${socket.server.port}")
    private int port;
    @Value("${socket.server.netty.boss-count}")
    private int bossCount;
    @Value("${socket.server.netty.worker-count}")
    private int workerCount;
    @Value("${socket.server.netty.backlog}")
    private int backlog;
	
	private final NettyInitializer nettyIntializer;

	public void run() {
		log.info("host : {} ", host);

		EventLoopGroup bossGroup = new NioEventLoopGroup(bossCount);
		EventLoopGroup workerGroup = new NioEventLoopGroup(workerCount);

		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, backlog)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childHandler(nettyIntializer);

		try {
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
//    public InetSocketAddress inetSocketAddress() {
//        return new InetSocketAddress(host, port);
//    }
}