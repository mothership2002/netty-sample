package com.bb.relay.netty.sample.netty.nettyAnnotation;

import java.lang.annotation.*;

import org.springframework.web.bind.annotation.Mapping;

import lombok.NonNull;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Mapping
public @interface NettyAnnotation {

	@NonNull
	String url();
	
}
