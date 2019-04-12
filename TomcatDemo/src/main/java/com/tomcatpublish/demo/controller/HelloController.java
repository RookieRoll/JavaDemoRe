package com.tomcatpublish.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
	@GetMapping("/gethello")
	public String getHello() {
		return "侬好呢";
	}

	@GetMapping("/usay/{word}")
	public String getusay(@PathVariable String word) {
		return "u say " + word;
	}

	@GetMapping("/getname")
	public String getGetValue(@RequestParam("name") String name) {
		return "u name is " + name;
	}


}
