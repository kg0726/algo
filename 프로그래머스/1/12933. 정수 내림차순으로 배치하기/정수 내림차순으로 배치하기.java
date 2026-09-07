import java.util.*;

class Solution {
    public long solution(long n) {
        
        String numString = Long.toString(n);
        
        List<Integer> arr = new ArrayList<>();

        for(int i = 0; i < numString.length(); i++) {
            
            arr.add(numString.charAt(i) - 48);
        }
        
        Collections.sort(arr, Collections.reverseOrder());
        
        String sortedString = "";
        
        for(int i = 0; i < arr.size(); i++) {
            sortedString += arr.get(i);
        }
        
        
        long answer = Long.parseLong(sortedString);
        return answer;
    }
}