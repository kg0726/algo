import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int answer;
    
    public static void dfs(int idx, int length, int target, int[] numbers) {
        
        // 종료조건
        if (idx == length) {
            // target 값과 같은지 확인
            
            int sum = 0;
            for (int i : numbers) {
                sum += i;
            }
            if (sum == target) {
                answer += 1;
            }
            return;
        }
        
        if (visited[idx]) {
            dfs(idx + 1, length, target, numbers);
        }
        
        for(int i = 0; i < 2; i++) {
            
            visited[idx] = true;
            // 더하기
            if (i == 0) {
                dfs(idx + 1, length, target, numbers);
                // 백트래킹
                visited[idx] = false;
                // 빼기
            } else {
                numbers[idx] = numbers[idx] * -1;
                dfs(idx + 1, length, target, numbers);
                // 백트래킹
                visited[idx] = false;
                numbers[idx] = numbers[idx] * -1;
            }
        }
        
    }
    public int solution(int[] numbers, int target) {
        answer = 0;
        
        int length = numbers.length;
        visited = new boolean[length];
        
        dfs(0, length, target, numbers);
        
        return answer;
    }
}