package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Department;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.redisRepos.UserRedis;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTest {
	@Autowired
	private UserRedis userRedis; 

	@Test
	public void contextLoads() {
		User user = userRedis.get(this.getClass().getName() + ":userByname:user");
		System.out.println(new Gson().toJson(user));
	}
	
	@Before
	public void initData() {
		
		Department dept = new Department();
		dept.setName("开发部");
		
		Role role = new Role();
		role.setName("admin");
		
		User user = new User();
		user.setName("user");
		user.setCreatedate(new Date());
		user.setDepartment(dept);
		
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		
		userRedis.delete(this.getClass().getName() + ":userByname:" +user.getName());
		userRedis.add(this.getClass().getName() + ":userByname:" + user.getName(), 10L, user);
	}

}
