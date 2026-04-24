import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        // 1. progress와 speeds는 인덱스로 매핑됨
        // 2. 반복을 통해 count를 1씩 올리면서 지금 타겟하고 있는 인덱스가 100 이상이 되었는지 확인
        // 3. 되었다면 다음 인덱스의 speeds * 지금까지 지난 시간 + progresses가 100 이상이 되었는지 확인
        // 4. 위와 같은 과정을 거쳐 최종 반환 answer를 만듦
        
        int idx = 0;
        int count = 0;
        
        List<Integer> answerList = new ArrayList<>();
        
        while(true) {
            if (idx >= speeds.length) break;
            
            // 1. 현재 카운트 * speed에 프로그래스를 더하면 100 이상이 되는지 확인
            if (speeds[idx] * count + progresses[idx] >= 100) {
                idx += 1;
                // 몇개가 서비스에 반영될건지?
                int service = 1;
                // 3. 되었다면 다음 인덱스의 프로그레스도 완료되었는지 확인함
                while(true) {
                    if (speeds.length > idx && speeds[idx] * count + progresses[idx] >= 100) {
                        service += 1;
                        idx += 1;
                        continue;
                    }
                    // 아니라면 리스트에 add 하고 종료
                    answerList.add(service);
                    break;
                }
            }
            // 2. 안되면 count를 1 올림
            count += 1;
        }
        
        // 정답 포멧 준비
        int[] answer = new int[answerList.size()];
        
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}