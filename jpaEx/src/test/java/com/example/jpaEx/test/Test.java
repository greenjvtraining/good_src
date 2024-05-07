package com.example.jpaEx.test;

public class Test {

	public static void main(String[] args) {
		int pageNum = 7;
		//start 번호 지정
		//end번호 = start번호 + 4;
		int result = (int) (Math.floor((pageNum-1)/5)*5 + 1);
		//int result = (int)(Math.ceil(pageNum/(10*(1.0))));
		
		
		System.out.println("result : " + result);
	}

}
