// 알파벳을 key로, 가장 최근에 나온 index를 value로 갖는 map 생성
// 주어진 문자열을 순회하며 해당 key가 map에 존재하는지 확인
// 존재한다면 현재 index와 value의 index 차이를 확인. 후 배열에 삽입 및 map의 value값 갱신

import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        
        Map<Character, Integer> indexMap = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            
            char nowChar = s.charAt(i);
            
            // indexMap에 key가 존재하면 그 값을 가져오고 아니면 -1로 세팅
            indexMap.put(nowChar, indexMap.getOrDefault(nowChar, i));
            int index = indexMap.get(nowChar);
            
            // 만약 index와 i가 같다면 지금까지 한번도 세팅된 적 없는 char 가 들어옴
            if (index == i) {
                answer[i] = -1;
                continue;
            }
            
            answer[i] = i - index;
            indexMap.put(nowChar, i);
        }
        
        
        
        return answer;
    }
}