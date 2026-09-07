import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        Map<String, Integer> participantMap = new HashMap<>();
        Map<String, Integer> completionMap = new HashMap<>();
        
        participantMap.put(participant[participant.length - 1], 1);
        
        for (int i = 0; i < completion.length; i++) {
            mapBuilder(participantMap, participant[i]);
            mapBuilder(completionMap, completion[i]);
        }
        
        String answer = "";
        
        for (String name : participant) {
            
            // 같은 이름이 있는 경우 동명이인이 다 들어왔는지 확인
            if(completionMap.containsKey(name)) {
                if(completionMap.get(name).equals(participantMap.get(name))) continue;
                answer = name;
                break;
            }
            
            // 이름이 존재하지 않으면 바로 종료
            answer = name;
            break;
        }
        
        
        return answer;
    }
    
    static void mapBuilder(Map<String, Integer> map, String name) {
        
        // 인자로 받은 map에 name이 있는지 확인
        if (map.containsKey(name)) {
            map.put(name, map.get(name) + 1);
            return;
        }
        
        map.put(name, 1);
    }
}