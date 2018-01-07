package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@SpringBootApplication
@Controller
public class DemoApplication {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String home() {
		User user = new User();
		user.setName("测试");
		user.setCreatedate(new Date());
		userService.create(user);
		return "index";
	}

	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);
	}
}
