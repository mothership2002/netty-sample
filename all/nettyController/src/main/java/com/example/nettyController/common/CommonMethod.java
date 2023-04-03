package com.example.nettyController.common;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

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
    		}
		finally {
			message.release();
    		}
	}
}
