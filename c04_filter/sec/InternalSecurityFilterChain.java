package c04_filter.sec;

import java.util.ArrayList;
import java.util.List;

import c04_filter.Myfilter;
import c04_filter.MyfilterChain;

public class InternalSecurityFilterChain implements MyfilterChain{

    private final List<Myfilter> filters = new ArrayList<>();
    private final MyfilterChain originalChain;
    private int currentPosition = 0;

    public InternalSecurityFilterChain(MyfilterChain originalChain){
        this.originalChain = originalChain;
        // 시큐리티 내부 필터 등록
        this.filters.add(new SecAuthenticationFilter());
        this.filters.add(new SecAuthorizationFilter());
    }

    @Override
    public void doFilter(String request) {
        // 내부 시큐리티 필터를 모두 통과했다면, 다시 원래 메인 필터 체인으로 돌아갑니다!
        if(currentPosition == filters.size()){
            System.out.println("[InternalChain] 시큐리티 필터 전원 통과! 메인 체인으로 복귀.");
            originalChain.doFilter(request);
            return;
        }

        Myfilter nextFilter = filters.get(currentPosition++);
        nextFilter.doFilter(request, this);
    }

}
