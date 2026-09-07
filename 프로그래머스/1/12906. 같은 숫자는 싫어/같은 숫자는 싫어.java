import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        int num = -1;
        
        List<Integer> numList = new ArrayList<>();
        
        
        for (int factor : arr) {
            
            if (num != factor) {
                
                numList.add(factor);
                num = factor;
                continue;
            }
            
        }
        
        int[] answer = new int[numList.size()];
        for(int i = 0; i < numList.size(); i++) {
            answer[i] = numList.get(i);
        }
        
        
        return answer;
    }
}