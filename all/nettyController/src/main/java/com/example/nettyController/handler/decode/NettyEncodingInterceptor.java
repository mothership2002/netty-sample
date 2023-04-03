package com.example.nettyController.handler.decode;

import java.nio.charset.StandardCharsets;

import com.example.nettyController.common.CommonMethod;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyEncodingInterceptor extends ChannelInboundHandlerAdapter {

	private StringBuilder builder = new StringBuilder();

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		CommonMethod.releaseByteBuf(msg, builder);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		log.info(builder.toString());
		ctx.writeAndFlush(Unpooled.copiedBuffer(builder.toString(), StandardCharsets.UTF_8));
		builder.setLength(0);
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		
	}

}
