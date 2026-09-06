class Solution {
    boolean solution(String s) {
        
        char[] cArr = s.toCharArray();
        
        int p = 0;
        int y = 0;
        
        for(int i = 0; i < s.length(); i++) {
            if(cArr[i] == 'p' || cArr[i] == 'P') {
                p++;
                continue;
            }
            
            if(cArr[i] == 'y' || cArr[i] == 'Y') {
                y++;
            }
        }

        return p == y ? true:false;
    }
}