package com.example.demo.redisRepos;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.netty.util.internal.StringUtil;


@Repository
public class UserRedis {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	private Gson gson = new Gson();
	
	public void add(String key,Long time,User user) {
		redisTemplate.opsForValue().set(key, gson.toJson(user), time, TimeUnit.MINUTES);
	}
	
	public void add(String key,Long time,List<User> users) {
		redisTemplate.opsForValue().set(key, gson.toJson(users),time,TimeUnit.MINUTES);
	}
	
	public User get(String key) {
		String userGson = redisTemplate.opsForValue().get(key);
		if(StringUtil.isNullOrEmpty(userGson)) {
			return null;
		}
		User user = gson.fromJson(userGson, User.class);
		return user;
	}
	
	public List<User> getList(String key){
		String userListGson = redisTemplate.opsForValue().get(key);
		if(StringUtil.isNullOrEmpty(userListGson)) {
			return null;
		}
		List<User> users = gson.fromJson(userListGson, new TypeToken<List<User>>(){}.getType());
		return users;
	}
	
	public void delete(String key) {
		redisTemplate.opsForValue().getOperations().delete(key);
	}
}

