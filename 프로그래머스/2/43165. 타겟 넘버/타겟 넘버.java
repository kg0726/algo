// 음이 아닌 정수들
// 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만든다.
// 해당 정수들을 순서를 바꾸지 않고 더하거나 빼서 target을 만든다.
//(+를 붙일 수도, -를 붙일 수도 있다.)

class Solution {
    
    static int targetNum;
    static int[] arr;
    static int maxDepth;
    static int answer = 0;
    
    // dfs로 푸는 경우
    // 1. 배열의 길이 == depth 가 기저조건, depth를 배열의 인덱스로 사용
    // 2. 각 시행마다 +/- 후 재귀 호출
    
    static void dfs(int depth, int num) {
        
        if (maxDepth == depth) {
            // 여기서 target과 현재 num 비교
            if (targetNum == num) {
                answer += 1;
            }
            return;
        }
        
        // +인 경우
        dfs(depth + 1, num + arr[depth]);
        // -인 경우
        dfs(depth + 1, num - arr[depth]);
        
    }
    
    public int solution(int[] numbers, int target) {
        
        targetNum = target;
        arr = numbers;
        maxDepth = numbers.length;
        
        dfs(0, 0);
        
        return answer;
    }
}