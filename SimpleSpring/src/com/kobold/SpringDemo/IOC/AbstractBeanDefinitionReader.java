package com.kobold.SpringDemo.IOC;

import com.kobold.SpringDemo.IOC.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanDefinitionReader implements  BeanDefinitionReader {
	private Map<String,BeanDefinition> registry;
	private ResourceLoader resourceLoader;

	public AbstractBeanDefinitionReader( ResourceLoader resourceLoader) {
		this.registry = new HashMap<>();
		this.resourceLoader = resourceLoader;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public Map<String, BeanDefinition> getRegistry() {
		return registry;
	}
}
