package com.example.nettyController.handler;

import com.example.nettyController.common.CommonMethod;
import com.example.nettyController.gateway.Gateway;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class NettySocketServerHandler extends ChannelInboundHandlerAdapter {
	
	private final Gateway gateway;

	private StringBuilder builder = new StringBuilder();

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		CommonMethod.releaseByteBuf(msg, builder);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		String result = gateway.branchUrl(builder.toString());
		builder.delete(0, builder.length());
		CommonMethod.processRequest(ctx, result);
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		log.error("Exception occurred: ", cause);
		ctx.close();
	}

}
