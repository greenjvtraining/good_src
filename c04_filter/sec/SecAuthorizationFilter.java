package c04_filter.sec;

import c04_filter.Myfilter;
import c04_filter.MyfilterChain;

public class SecAuthorizationFilter implements Myfilter{

    @Override
    public void doFilter(String request, MyfilterChain chain) {
        System.out.println("   [Sec-인가필터] ---> 권한 검사 진행 (ROLE_USER)");
        chain.doFilter(request);
        System.out.println("   [Sec-인가필터] <--- 권한 검사 완료");
    }

}
