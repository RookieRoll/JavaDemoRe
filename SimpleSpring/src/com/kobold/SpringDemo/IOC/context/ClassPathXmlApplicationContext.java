package com.kobold.SpringDemo.IOC.context;

import com.kobold.SpringDemo.IOC.BeanDefinition;
import com.kobold.SpringDemo.IOC.factory.AbstractBeanFactory;
import com.kobold.SpringDemo.IOC.factory.AutowireCapableBeanFactory;
import com.kobold.SpringDemo.IOC.io.ResourceLoader;
import com.kobold.SpringDemo.IOC.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
	private String configLocation;

	public ClassPathXmlApplicationContext(String configLocatioon) throws Exception {
		this(configLocatioon, new AutowireCapableBeanFactory());

	}

	public ClassPathXmlApplicationContext(String configLocatioon, AbstractBeanFactory beanFactory) throws Exception {
		super(beanFactory);
		this.configLocation = configLocatioon;
		refresh();
	}

	@Override
	protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		// 从类路径加载xml文件中bean的定义并注册到AbstractBeanDefinitionReader的registry中去
		xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
		// 将加载出的bean定义从registry中注册到beanFactory中
		for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}
	}
}
