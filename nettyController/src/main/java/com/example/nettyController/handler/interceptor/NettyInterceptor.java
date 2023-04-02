package com.example.nettyController.handler.interceptor;

import java.nio.charset.StandardCharsets;

import com.example.nettyController.common.CommonMethod;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyInterceptor extends ChannelInboundHandlerAdapter {

	private final static String message = "적절치 않은 메시지 연결 종료\n";
	private StringBuilder builder = new StringBuilder();

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		CommonMethod.releaseByteBuf(msg, builder);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		
		String requestValidation = builder.toString().trim();
		builder.delete(0, builder.length());
		log.info("inter builder : {} " , requestValidation);
		
		if (requestValidation.length() < 4) {
			ByteBuf disconnectMessage = Unpooled.copiedBuffer(message.getBytes(StandardCharsets.UTF_8));
			ctx.writeAndFlush(disconnectMessage);
			ctx.close();
			disconnectMessage.release();
			return;
		} 
		else {
			CommonMethod.processRequest(ctx, requestValidation);
		}
		
	}

}