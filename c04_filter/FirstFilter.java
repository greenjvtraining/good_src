package c04_filter;

public class FirstFilter implements Myfilter{

    @Override
    public void doFilter(String request, MyfilterChain chain) {
        System.out.println("[FirstFilter] --->  전처리(요청 입구)");

        chain.doFilter(request);

        System.out.println("[FirstFilter] <--- 후처리(응답 출구)");
    }

}
