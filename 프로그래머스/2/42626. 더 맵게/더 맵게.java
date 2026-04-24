import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        // 1. 힙에 스코빌 배열을 순회하며 삽입
        // 2. 힙에서 첫번째 값을 확인하여 K 이상인지 확인
        // 2-1. 만약 K 이상이면 종료
        // 2-2. K 이상이 아니라면 섞음(첫번째 두번째)
        
        // 힙 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i : scoville) {
            pq.add(i);
        }
        
        int answer = 0;
        
        while(true) {
            
            // 힙에서 첫번째 값을 뽑아 확인함
            int firstFood = pq.poll();
            // 첫번째 값을 확인하여 K 이상인지 확인
            if (firstFood >= K) {
                break;
            }
            // 종료되지 않았다면 두번째 값도 뽑음
            if(pq.isEmpty()) {
                answer = -1;
                break;
            }
            int secondFood = pq.poll();
            
            // 두 값을 문제의 요구에 따라 섞음
            pq.add(firstFood + secondFood * 2);
            answer += 1;
        }
        
        
        return answer;
    }
}