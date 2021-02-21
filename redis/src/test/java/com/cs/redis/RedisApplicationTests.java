package com.cs.redis;

import com.cs.redis.bean.bo.UserBO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest
class RedisApplicationTests {

	@Autowired
	private RedisTemplate<String,String> stringRedisTemplate;
	@Autowired
	private RedisTemplate<String, Serializable> serializableRedisTemplate;
	private static final String STR_KEY = "strKey";
	private static final String SERIA_KEY = "user";
	@Test
	void contextLoads() {
	}

	@Test
	public void testString(){
		stringRedisTemplate.opsForValue().set(STR_KEY,"cs");
		System.out.println(stringRedisTemplate.opsForValue().get(STR_KEY));
	}

	@Test
	public void testSerializable(){
		UserBO userBO = new UserBO();
		userBO.setId(1L);
		userBO.setUserName("陈帅");
		userBO.setUserSex("男");
		serializableRedisTemplate.opsForValue().set(SERIA_KEY,userBO);
		UserBO userBO1 = (UserBO) serializableRedisTemplate.opsForValue().get(SERIA_KEY );
		System.out.println("userBO1:" + userBO1.getId()+","+userBO1.getUserName()+","+userBO1.getUserSex());
	}
}
