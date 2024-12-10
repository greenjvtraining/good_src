package test;

import java.util.HashMap;
import java.util.Map;

public class CipherTest {

	public static void main(String[] args) {
		//회원가입
		Map<String, String> user = new HashMap<>();
		String username = "aaa";
		
		int key = (int)(Math.random()*5 + 1);
		System.out.println("key : " + key);
		String encodedPw = passwordEncoder("abcd", key);
		System.out.println("암호화 패스워드 : " + encodedPw);
		
		user.put(username, encodedPw);
		
		// 로그인
		String inputUsername = "aaa";
		String inputPw = "abcd";
		
		//1. 아이디 확인
		String pw = user.get(inputUsername);
		System.out.println("저장된 pw : " + pw);
		System.out.println("-------------------------");
		char temp = pw.charAt(pw.length()-1);
		System.out.println("패스워드에서 추출한 key : " + pw.charAt(pw.length() - 1));
		String verifiedKey = "" + temp;
		System.out.println("패스워드에서 추출한 key(int) : " + verifiedKey);
		String verifiedPw = passwordEncoder(inputPw, Integer.parseInt(verifiedKey));
		System.out.println("검증할 pw : " + verifiedPw);
		if(verifiedPw.equals(pw)) {
			System.out.println("login 완료!!");
		}else {
			System.out.println("fail......");
		}
		
	}

	private static String passwordEncoder(String password, int key) {
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
				System.out.println(i);
				sb.append(pw[i]);
			}else {
				sb.append('x');
			}
		}
		sb.append(key);
		
		return sb.toString();
	}
}
