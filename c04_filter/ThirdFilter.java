package c04_filter;

public class ThirdFilter implements Myfilter{

    @Override
    public void doFilter(String request, MyfilterChain chain) {
        System.out.println("[ThirdFilter] ---> 전처리 (보안 및 인증 검사)");
        
        chain.doFilter(request); // 다음 필터 호출
        
        System.out.println("[ThirdFilter] <--- 후처리");
    }

}
