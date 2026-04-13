import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        Map<Integer, Integer> pocketMap = new HashMap<>();
        
        int allow = nums.length / 2;
        
        for(int i = 0; i < nums.length; i++) {
            
            int num = nums[i];
            
            // 해당 포켓몬의 번호를 가지고 있는지 확인
            if(pocketMap.containsKey(num)) {
                // 있으면 다음으로
                continue;
            } else {
                // 없으면 맵에 추가하고 결과를 1 더함
                pocketMap.put(num, 1);
                answer += 1;
            }
        }
        
        // 만약 최대로 가져갈 수 있는 수보다 크다면
        if(answer > allow) answer = allow;
        
        return answer;
    }
}