class Solution {
    public int solution(int[] numbers) {
        
        int[] arr = new int[10];
        
        for(int i = 0; i < numbers.length; i++) {
            arr[numbers[i]] = -1;
        }
        
        int answer = 0;
        
        for(int i = 0; i < 10; i++) {
            if (arr[i] == -1) continue;
            answer += i;    
        }
        
        
        return answer;
    }
}