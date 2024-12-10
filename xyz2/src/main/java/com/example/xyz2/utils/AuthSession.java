package com.example.xyz2.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AuthSession {
	
	private Map<String, MemberDetails> session = new HashMap<>();
	
	public void setAttribute(String username, MemberDetails memberDetails) {
		session.put(username, memberDetails);
	}
	
	public MemberDetails getAttribute(String username) {
		return session.get(username);
	}
	
	public void removeAttribute(String username) {
		session.remove(username);
	}
	
	public void invalidate() {
		session.clear();
	}
}
