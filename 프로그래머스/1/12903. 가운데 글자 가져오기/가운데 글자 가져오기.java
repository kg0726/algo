class Solution {
    public String solution(String s) {
        
        int len = s.length();
        int mid = len / 2;
        String answer = "";
        
        if(len % 2 != 0) {
            answer += s.charAt(mid);
        } else {
            answer = answer + s.charAt(mid - 1) + s.charAt(mid);
        }
        
        
        return answer;
    }
}