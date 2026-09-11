// 1. players 배열 순회하며 순위 map 만들기 Key를 등수로 한다면?
// 2. callings를 순회하며 등수로 원래 key를 가진 선수를 찾아서 그 key에 순회중인 calling
// 3. 근데? 등수를 찾아도? 추월 처리를 어떻게 할 것인가? 원래 해당 등수의 선수를 어떻게 확인 할 수 있는가?
// 4. map 두개를 쓴다?

import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        
        Map<String, Integer> playerMap = new HashMap<>();
        Map<Integer, String> numberMap = new HashMap<>();
        
        for (int i = 0; i < players.length; i++) {
            playerMap.put(players[i], i + 1);
            numberMap.put(i + 1, players[i]);
        }
        
        for (int i = 0; i < callings.length; i++) {
            // 불린 선수의 등수 확보
            int callingNum = playerMap.get(callings[i]);
            // 불린 선수의 원래 앞에 있던 선수의 이름 확보
            String frontPlayer = numberMap.get(callingNum - 1);
            
            // 자리 및 등수 바꾸기
            playerMap.put(callings[i], callingNum - 1);
            numberMap.put(callingNum - 1, callings[i]);
            playerMap.put(frontPlayer, callingNum);
            numberMap.put(callingNum, frontPlayer);
        }
        
        String[] answer = new String[players.length];
        
        for (int i = 0; i < players.length; i++) {
            answer[i] = numberMap.get(i + 1);
        }
        return answer;
    }
}