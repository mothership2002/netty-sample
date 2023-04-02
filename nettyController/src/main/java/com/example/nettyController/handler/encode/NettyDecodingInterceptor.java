package com.example.nettyController.handler.encode;

import com.example.nettyController.common.CommonMethod;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyDecodingInterceptor extends ChannelInboundHandlerAdapter {

	private StringBuilder builder = new StringBuilder();
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		CommonMethod.releaseByteBuf(msg, builder);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		log.info("decode : {} " , builder.toString());
		
		CommonMethod.processRequest(ctx, builder.toString());
		builder.delete(0, builder.length());
 	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		
	}

}
