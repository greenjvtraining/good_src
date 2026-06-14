package c04_filter;

public class AuthFilter implements Myfilter{

    @Override
    public void doFilter(String request, MyfilterChain chain) {
      System.out.println("AuthFilter] ---> 인증 로직 수행 (토큰 검증)");

      // 간단한 인증 성공 로직
      boolean isAuthenticated = true;

      if(isAuthenticated){
        System.out.println("{AuthFilter] 인증 성공! 다음 필터로 진행.");
        chain.doFilter(request);
      }else{
        System.out.println("[AuthFilter] 인증 실패! 요청 중단.");
      }

      System.out.println("[AuthFilter] <--- 인증 필터 종료");
    }

}
