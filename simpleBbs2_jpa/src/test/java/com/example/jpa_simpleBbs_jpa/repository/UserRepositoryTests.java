package com.example.jpa_simpleBbs_jpa.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa_simpleBbs_jpa.entity.User;

@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	UserRepository userRepository;
	
	//@Test
	public void queryTest() {
		
		List<User> list = userRepository.getUserList("%an%");
		
		for(User u : list) {
			System.out.println(u);
		}
	}
	//@Test
	public void queryTest2() {
		List<User> list = userRepository.getUserList2("%Ja%");
		
		for(User u : list) {
			System.out.println(u);
		}
	}
	@Test
	public void queryTest3() {
		List<Object[]> list = userRepository.getTelUser();
		
		for(Object[] objs : list) {
			System.out.println(Arrays.toString(objs));
		}
	}
	//@Test
	public void queryTest4() {
		int cnt = userRepository.getTotal();
		System.out.println("cnt : " + cnt);
	}
	
	
	
	
}
