package c04_filter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FilterChainDemo implements MyfilterChain{

    //private final List<Myfilter> filters = new ArrayList<>();
    private final LinkedList<Myfilter> filters = new LinkedList<>();
    private int currentPosition = 0; // 현재 실행할 필터의 인덱스

    public void addFilter(Myfilter filter){
        this.filters.add(filter);
    }

    // [핵심] 특정 필터 앞에 필터 삽입하기
    public void addFilterBefore(Myfilter newFilter, Class<? extends Myfilter> targetFilterClass){
        for(int i = 0; i < filters.size(); i++){
            if(targetFilterClass.isInstance(filters.get(i))){
                filters.add(i, newFilter);
                return;
            }
        }
    }

    @Override
    public void doFilter(String request) {

        if(currentPosition == filters.size()){
            executeTarget(request);
            return;
        }

        Myfilter nextFilter = filters.get(currentPosition);
        currentPosition++;

        nextFilter.doFilter(request, this);
    }

    // 최종 목적지 (실제 비즈니스 로직이 수행되는 곳)
    private void executeTarget(String request) {
        System.out.println("\n[Target] ★ 실제 비즈니스 로직 수행: " + request + " 처리 완료 ★\n");
    }

    // --- 테스트 실행 ---
    public static void main(String[] args) {
        FilterChainDemo chain = new FilterChainDemo();
        
        // 필터를 순서대로 체인에 등록
        chain.addFilter(new FirstFilter());
        chain.addFilter(new SecondFilter());
        chain.addFilter(new ThirdFilter());

        // 2. [삽입] SecondFilter 앞에 AuthFilter 끼워넣기
        chain.addFilterBefore(new AuthFilter(), ThirdFilter.class);
        /*
        System.out.println("=== 필터 체인 시작 ===");
        chain.doFilter("Hello World Request");
        System.out.println("=== 필터 체인 종료 ===");
         */
        System.out.println("=== 변경된 필터 체인 시작 ===");
        chain.doFilter("JWT Token: ABC12345");
    }

}
