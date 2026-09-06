class Solution {
    public String solution(String s, int n) {
        
        System.out.println(('A' + 0));
        System.out.println(('a' + 0));
        
        int UC = 65;
        int DC = 97;
        
        
        
        String answer = "";
        
        for (int i = 0; i < s.length(); i++) {
            
            char factor = s.charAt(i);
            
            if (factor == ' ') {
                answer += " ";
                continue;
            }
            
            
            
            if(Character.isUpperCase(factor)) {
                
                int next = (factor + n - UC) % 26;
                
                answer += (char) (UC + next);
            } else {
                
                int next = (factor + n - DC) % 26;
                
                answer += (char) (DC + next);
            }
        }
        
        
        return answer;
    }
}