package com.kobold.SpringDemo.IOC.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获取资源的输入流从而获取其中的内容
 */
public interface Resource {
	InputStream getInputStream() throws IOException;
}
