package c04_filter;

import java.util.ArrayList;
import java.util.List;

import c04_filter.sec.MyDelegatingFilterProxy;

public class ProxyDemo implements MyfilterChain{

    private final List<Myfilter> filters = new ArrayList<>();
    private int currentPosition = 0;

    public void addFilter(Myfilter filter) {
        this.filters.add(filter);
    }

    @Override
    public void doFilter(String request) {
        if (currentPosition == filters.size()) {
            System.out.println("\n[Target] ★ 실제 비즈니스 로직 수행: " + request + " ★\n");
            return;
        }
        filters.get(currentPosition++).doFilter(request, this);
    }

    public static void main(String[] args) {
        ProxyDemo mainChain = new ProxyDemo();
        
        // 메인 서블릿 필터 체인 구성
        mainChain.addFilter(new FirstFilter());             // 일반 전처리 필터
        mainChain.addFilter(new MyDelegatingFilterProxy()); // ⭐️ 시큐리티 위임 프록시 필터 삽입!
        mainChain.addFilter(new ThirdFilter());             // 일반 후처리 필터

        System.out.println("=== 전체 시스템 가동 ===");
        mainChain.doFilter("Secure Request Data");
        System.out.println("=== 전체 시스템 종료 ===");
    }
}
