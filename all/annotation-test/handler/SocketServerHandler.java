package com.bb.relay.netty.sample.netty.handler;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import com.bb.relay.netty.sample.netty.common.CommonMethod;
import com.bb.relay.netty.sample.netty.common.UrlObject;
import com.bb.relay.netty.sample.netty.controller.NettyMainController;
import com.bb.relay.netty.sample.netty.nettyAnnotation.NettyAnnotationScanner;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SocketServerHandler extends ChannelInboundHandlerAdapter {

	private StringBuilder builder = new StringBuilder();
	private final NettyMainController nettyMainController;
	private final NettyAnnotationScanner nettyAnnotationScanner;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		CommonMethod.releaseByteBuf(msg, builder);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		String[] urlAndObject = builder.toString().split("\\?");
		Method method = (Method) nettyAnnotationScanner.getHandlerMap().get(urlAndObject[0]);
		
		String result = null;
		if (method == null) {
			result = "unvalidate url";
		} else {
			result = (String) method.invoke(nettyMainController,
					new UrlObject(urlAndObject[0], urlAndObject[1]));
		}
		
		ctx.writeAndFlush(Unpooled.copiedBuffer(result.getBytes(StandardCharsets.UTF_8)));
		builder.delete(0, builder.length());
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		CommonMethod.nettyExceptionHandler(ctx, this.getClass());
		cause.printStackTrace();
	}

}
