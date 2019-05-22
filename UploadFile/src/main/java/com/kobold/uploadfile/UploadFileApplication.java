package com.kobold.uploadfile;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kobold
 */
@SpringBootApplication
public class UploadFileApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UploadFileApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
