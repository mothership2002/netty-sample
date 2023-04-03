package com.bb.relay.netty.sample.netty.common;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonMethod {

	public static void processRequest(ChannelHandlerContext ctx, String requestToString) {
		ByteBuf request = Unpooled.copiedBuffer(requestToString.trim(), StandardCharsets.UTF_8);
		ctx.fireChannelRead(request);
		ctx.fireChannelReadComplete();

	}

	public static void releaseByteBuf(Object msg, StringBuilder builder) {
		ByteBuf message = (ByteBuf) msg;
		try {
			builder.append(message.toString(StandardCharsets.UTF_8));
		} finally {
			message.release();
		}
	}

	public static void nettyExceptionHandler(ChannelHandlerContext ctx,
			Class<? extends ChannelInboundHandlerAdapter> clazz) {

		log.error("netty module error class : {} ", clazz);
		ByteBuf responseMessage = Unpooled.copiedBuffer("Exception ", StandardCharsets.UTF_8);
		ctx.writeAndFlush(responseMessage);
		ctx.close();
	}

}
