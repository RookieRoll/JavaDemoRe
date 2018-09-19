package com.kobold.SpringDemo.IOC;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface BeanDefinitionReader {
	void loadBeanDefinitions(String location) throws IOException, ParserConfigurationException, SAXException;
}
