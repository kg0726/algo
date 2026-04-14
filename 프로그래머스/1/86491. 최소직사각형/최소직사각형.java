import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        int maxBig = 0;
        int maxSmall = 0;
        
        // width = false, hight = true;
        boolean flag = false;
        // 주어진 배열 중 가장 큰 수를 찾음
        for(int[] size : sizes) {
            
            int width = size[0];
            int hight = size[1];
            
            // 둘중 더 큰 수와 더 작은 수 탐색
            int moreBig = 0;
            int moreSmall = 0;
            
            if (width >= hight) {
                moreBig = width;
                moreSmall = hight;
            } else {
                moreBig = hight;
                moreSmall = width;
            }
            
            // 둘 중 더 큰 수, 둘 중 더 작은 수들끼리 최댓값 비교
            if (maxBig < moreBig) {
                maxBig = moreBig;
            }
            if (maxSmall < moreSmall) {
                maxSmall = moreSmall;
            }
            
        }
        
        
        return maxBig * maxSmall;
    }
}