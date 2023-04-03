package com.bb.relay.netty.sample.netty.nettyAnnotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class NettyAnnotationScanner implements BeanPostProcessor {

	private final Map<String, Object> handlerMap = new HashMap<String, Object>();

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		Method[] methods = bean.getClass().getMethods();

		for (Method method : methods) {
			NettyAnnotation annotation = method.getAnnotation(NettyAnnotation.class);
			if (annotation != null) {
				handlerMap.put(annotation.url(), method);
			}
		}

		return bean;
	}

	public Map<String, Object> getHandlerMap() {
		return handlerMap;
	}

}
