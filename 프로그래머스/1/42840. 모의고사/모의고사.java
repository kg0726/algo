import java.util.*;
import java.lang.Math;

class Solution {
    
    static int operator(int humanNum, int answer) {
        if (humanNum == answer) {
            return 1;
        }
        return 0;
    }
    
    static int renew(int maxMum, int num) {
        return Math.max(maxMum, num);
    }
    
    public int[] solution(int[] answers) {
        
        int[] one = {1, 2, 3, 4, 5};
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int fh = 0;
        int sh = 0;
        int th = 0;
        
        
        int maxNum = 0;
        
        for(int i = 0; i < answers.length; i++) {
            
            int curr = answers[i];
            
            fh += operator(one[i % (one.length)], curr);
            maxNum = renew(maxNum, fh);
            sh += operator(two[i % (two.length)], curr);
            maxNum = renew(maxNum, sh);
            th += operator(three[i % (three.length)], curr);
            maxNum = renew(maxNum, th);
        }
        
        int[] arr = {fh, sh, th};
        List<Integer> tmp = new ArrayList<>();
        int cnt = 0;
        
        for (int i = 0; i < 3; i++) {
            
            if (arr[i] == maxNum) {
                tmp.add(i + 1);
                ++cnt;
            }
        }
        
        Collections.sort(tmp);
        
        int[] answer = new int[cnt];
        
        for (int i = 0; i < cnt; i++) {
            answer[i] = tmp.get(i);
        }
        
        return answer;
    }
}