package com.example.jpa_simpleBbs_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpa_simpleBbs_jpa.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	User findByUsernameAndPassword(String username, String password);
	
	@Query(value = "select * from user where name like :name", nativeQuery = true)
	List<User> getUserList(@Param("name") String str);

	@Query(value = "select u from User u where u.name like :name")
	List<User> getUserList2(@Param("name") String str);
	
	@Query(value = "select tel, count(*) from user group by tel having tel is not null", nativeQuery = true)
	List<Object[]> getTelUser();
	
	@Query(value = "select count(u) from User u")
	int getTotal();
	
}
