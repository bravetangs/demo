package com.example.demo;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.example.demo.domain.Department;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.mysqlRepos.DepartmentRepository;
import com.example.demo.mysqlRepos.RoleRepository;
import com.example.demo.mysqlRepos.UserRepository;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void contextLoads() {
//		Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC,"id"));
//		Page<User> page = userRepository.findAll(pageable);
//		Assert.notNull(page);
//		for(User user : page.getContent()) {
//			System.out.println(new Gson().toJson(user));
//		}
//		List<User> users = userRepository.findBy();
//		System.out.println(new Gson().toJson(users));
	}
	
//	@Before
	public void initData() {
//		userRepository.deleteAll();
//		departmentRepository.deleteAll();
//		roleRepository.deleteAll();
		
		Department dept = new Department();
		dept.setName("开发部");
		departmentRepository.save(dept);
		Assert.notNull(dept.getId());
		
		Role role = new Role();
		role.setName("admin");
		roleRepository.save(role);
		Assert.notNull(role.getId());
		
		User user = new User();
		user.setName("user");
		user.setCreatedate(new Date());
		user.setDepartment(dept);
		
		List<Role> roles = roleRepository.findAll();
		Assert.notNull(roles);
		user.setRoles(roles);
		
		userRepository.save(user);
		Assert.notNull(user.getId());
	}

}
