package com.kobold.SpringDemo.IOC.io;

import java.net.URL;

/**
 * 资源加载
 */
public class ResourceLoader {
	public Resource getResource(String location) {
		URL resource = this.getClass().getClassLoader().getResource(location);
		return new UrlResource(resource);
	}
}
