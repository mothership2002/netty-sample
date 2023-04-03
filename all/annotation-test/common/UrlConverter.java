package com.bb.relay.netty.sample.netty.common;

import org.springframework.core.convert.converter.Converter;

public class UrlConverter implements Converter<String, UrlObject>{

	@Override
	public UrlObject convert(String source) {
		String[] resouce = source.split("?"); // 이 부분은 데이터 포멧에 따른 변경이 필요
		return new UrlObject(resouce[0], resouce[1]);
	}

}
