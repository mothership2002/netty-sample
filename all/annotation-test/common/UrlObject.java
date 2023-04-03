package com.bb.relay.netty.sample.netty.common;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UrlObject {
	
	@NonNull
	private String url;
	@NonNull
	private String object;

}
