package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.mysqlRepos.UserRepository;
import com.example.demo.redisRepos.UserRedis;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private UserRedis userRedis;
	
	@Cacheable(value="mysql:findById:user",keyGenerator="simpleKey")
	public Optional<User> find(Long id) {
		return userRepository.findById(id); 
	}
	
	@CachePut(value="mysql:findById:user",keyGenerator="objectId")
	public User create(User user) {
		return userRepository.save(user);
	}
	
	@CachePut(value="mysql:findById:user",keyGenerator="objectId")
	public User update(User user) {
		return userRepository.save(user);
	}
	
	@CacheEvict(value="mysql:findById:user",keyGenerator="simpleKey")
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
