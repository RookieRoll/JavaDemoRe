package com.kobold.SpringDemo.IOC.factory;

import com.kobold.SpringDemo.IOC.BeanDefinition;
import com.kobold.SpringDemo.IOC.BeanPostProcessor;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AbstractBeanFactory implements BeanFactory {

	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

	private final List<String> beanDefinitionNames = new ArrayList<>();

	private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

	@Override
	public Object getBean(String name) throws Exception {
		BeanDefinition beanDefinition = beanDefinitionMap.get(name);
		if (beanDefinition == null)
			throw new IllegalArgumentException("No bean named " + name + " is defined");
		Object bean = beanDefinition.getBean();
		if (bean == null) {
			bean = doCreateBean(beanDefinition);

			//初始化bean
			bean = initializeBean(bean, name);
			beanDefinition.setBean(bean);
		}
		return bean;
	}

	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
		Object bean = createBeanInstance(beanDefinition);
		beanDefinition.setBean(bean);
		// 注入属性的hook方法(参考模板方法设计模式中的hook方法)交给子类去实现
		applyPropertyValues(bean, beanDefinition);
		return bean;
	}

	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

	}

	protected Object createBeanInstance(BeanDefinition beanDefinition) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		return beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
	}

	//TODO 该部分的代码好像有点儿问题
	protected Object initializeBean(Object bean, String name) throws Exception {
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
		}
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
		}
		return bean;
	}

	/**
	 * 预处理bean的定义，将bean的名字提前存好，实现ioc容器中存储单例bean
	 */
	public void preInstantiateSingletons() throws Exception {
		Iterator it =this.beanDefinitionNames.iterator();
		while (it.hasNext()){
			String name= (String) it.next();
			getBean(name);
		}
	}

	/**
	 * 根据类型获取所有的bean实例
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List getBeansForType(Class type) throws Exception {
		List beans=new ArrayList<Object>();
		for(String beanDefinitionName:beanDefinitionNames){
			if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass()))
				beans.add(getBean(beanDefinitionName));
		}
		return beans;
	}

	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
		beanDefinitionMap.put(name, beanDefinition);
		beanDefinitionNames.add(name);
	}

	// 增加bean处理程序，例如AspectJAwareAdvisorAutoProxyCreator#postProcessAfterInitialization()
	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
		this.beanPostProcessors.add(beanPostProcessor);
	}
}
