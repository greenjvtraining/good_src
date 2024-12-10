package com.example.xyz2.utils;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

	public String passwordEncoder(String password, int key) {
		char[] pw = new char[20];
		int pwCnt = 0;
		for(int i = 0; i < 20; i=i+key) {
			pw[i] = password.charAt(pwCnt++);
			if(pwCnt >= password.length()) {
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < pw.length-1; i++) {
			if(pw[i] != '\u0000') {
				//System.out.println(i);
				sb.append(pw[i]);
			}else {
				sb.append('x');
			}
		}
		sb.append(key);
		
		return sb.toString();
	}
}
