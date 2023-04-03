package com.bb.relay.netty.sample.netty.handler.interceptor;

import java.nio.charset.StandardCharsets;

import com.bb.relay.netty.sample.netty.common.CommonMethod;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleInterceptor extends ChannelInboundHandlerAdapter{

	private final static String message = "유효하지 않은 URL\n";
	private StringBuilder builder = new StringBuilder();

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		CommonMethod.releaseByteBuf(msg, builder);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		
		String requestValidation = builder.toString().trim();
		builder.delete(0, builder.length());
		
		if (requestValidation.length() < 10) {
			ByteBuf disconnectMessage = Unpooled.copiedBuffer(message.getBytes(StandardCharsets.UTF_8));
			log.error("unconnected url validate : {}", requestValidation);
			ctx.writeAndFlush(disconnectMessage);
			ctx.close();
			disconnectMessage.release();
			return;
		} 
		else {
			CommonMethod.processRequest(ctx, requestValidation);
		}
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		CommonMethod.nettyExceptionHandler(ctx, this.getClass());
	}
	

}
