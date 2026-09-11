import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        
        // 체육복 개수 현황을 관리할 indexArr
        // -1: 체육복 없음 0: 체육복 있음 1: 여유분 있음
        int[] indexArr = new int[n];

        for (int i : lost) {
            indexArr[i - 1]--;
        }
        
        for (int i : reserve) {
            indexArr[i - 1]++;
        }
        
        for (int i = 0; i < n; i++) {
            // 여벌 체육복이 없으면 다음으로
            if(indexArr[i] != 1) continue;
            
            // index == 0 처리
            if(i == 0) {
                if(indexArr[i] == 1 && indexArr[i + 1] == -1) {
                    indexArr[i]--;
                    indexArr[i + 1]++;
                }
                continue;
            }
            // index == n - 1 처리
            if(i == n - 1) {
                if(indexArr[i] == 1 && indexArr[i - 1] == -1) {
                    indexArr[i]--;
                    indexArr[i - 1]++;
                }
                continue;
            }
            
            // index 1 ~ n - 2 처리
            // 이전부터 보는게 최댓값을 구하는데 적합
            if (indexArr[i - 1] == -1) {
                indexArr[i]--;
                indexArr[i - 1]++;
                continue;
            }
            if (indexArr[i + 1] == -1) {
                indexArr[i]--;
                indexArr[i + 1]++;
            }
        }
        
        int answer = 0;
            for(int i : indexArr) {
                if(i >= 0) answer++;
            }
        
        return answer;
    }
}