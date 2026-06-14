package c04_filter.sec;

import c04_filter.Myfilter;
import c04_filter.MyfilterChain;

public class MyDelegatingFilterProxy implements Myfilter{

    @Override
    public void doFilter(String request, MyfilterChain chain) {
        System.out.println("[Proxy] ---> 서블릿 컨테이너에서 요청 가로챔. 시큐리티 체인으로 위임합니다.");
        
        // 내부 시큐리티 체인을 생성하면서 메인 체인(chain)을 넘겨줍니다.
        InternalSecurityFilterChain securityChain = new InternalSecurityFilterChain(chain);

        // 시큐리티 체인 가동!
        securityChain.doFilter(request);
        
        System.out.println("[Proxy] <--- 시큐리티 처리 완료. 서블릿 컨테이너로 응답 반환.");
    }

}
