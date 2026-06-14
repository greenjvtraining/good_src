package c04_filter.sec;

import c04_filter.Myfilter;
import c04_filter.MyfilterChain;

public class SecAuthenticationFilter implements Myfilter{

    @Override
    public void doFilter(String request, MyfilterChain chain) {
        System.out.println("   [Sec-인증필터] ---> 토큰 및 인증 검사 진행");
        chain.doFilter(request);
        System.out.println("   [Sec-인증필터] <--- 인증 필터 완료");
    }

}
