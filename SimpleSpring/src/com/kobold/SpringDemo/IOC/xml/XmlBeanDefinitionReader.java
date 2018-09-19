package com.kobold.SpringDemo.IOC.xml;

import com.kobold.SpringDemo.BeanReference;
import com.kobold.SpringDemo.IOC.AbstractBeanDefinitionReader;
import com.kobold.SpringDemo.IOC.BeanDefinition;
import com.kobold.SpringDemo.IOC.PropertyValue;
import com.kobold.SpringDemo.IOC.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
	public XmlBeanDefinitionReader( ResourceLoader resourceLoader) {
		super( resourceLoader);
	}

	@Override
	public void loadBeanDefinitions(String location) throws IOException, ParserConfigurationException, SAXException {
		InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
		doLoadBeanDefinitions(inputStream);
	}

	protected void doLoadBeanDefinitions(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(inputStream);
		registerBeanDefinitions(doc);
		inputStream.close();
	}

	protected void registerBeanDefinitions(Document document) {
		Element root = document.getDocumentElement();
		parseBeanDefinitions(root);
	}

	protected void parseBeanDefinitions(Element root) {
		NodeList nodeList = root.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node instanceof Element) {
				Element element = (Element) node;
				processBeanDefinitions(element);
			}
		}
	}

	protected void processBeanDefinitions(Element element) {
		String name = element.getAttribute("id");
		String className = element.getAttribute("class");
		BeanDefinition beanDefinition = new BeanDefinition();

		processProperty(element, beanDefinition);
		beanDefinition.setBeanClass(className);
		getRegistry().put(name, beanDefinition);
	}

	protected void processProperty(Element element, BeanDefinition beanDefinition) {
		NodeList nodeList = element.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node instanceof Element) {
				Element propertyElement = (Element) node;
				String name = propertyElement.getAttribute("name");
				String value = propertyElement.getAttribute("value");
				//value是属性值
				if (value != null && value.length() > 0) {
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
				} else {
					String ref = propertyElement.getAttribute("ref");
					if (ref == null || ref.length() == 0)
						throw new IllegalArgumentException("Configuration problem: <property> element for property '"
								+ name + "' must specify a ref or value");
					BeanReference beanReference = new BeanReference(ref);
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
				}
			}
		}
	}

}
