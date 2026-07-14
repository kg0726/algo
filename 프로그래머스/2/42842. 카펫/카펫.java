class Solution {
    
    static boolean verifier(int width, int hight, int brown, int yellow) {
        
        if (width == 0 || width < hight) return false;
        
        int arround = width * 2 + (hight - 2) * 2;
        
        int total = width * hight;
        
        // 테두리의 합이 borwn인가?
        if (arround == brown) {
            // 나머지의 합이 yellow 인가?
            if (total - arround == yellow) return true;
        }
        return false;
    }
    
    public int[] solution(int brown, int yellow) {
        
        // 총 칸 수 구하기
        int total = brown + yellow;
        
        // 가로 세로 배분
        for (int hight = 1; hight < total; hight++) {
            
            int width = 0; 
            
            // 가로 * 세로가 total이 나오는 수를 확인
            if (total % hight == 0) {
                width = total / hight;
            }
            
            if (verifier(width, hight, brown, yellow)) {
                return new int[] {width, hight};
            }
        }
        return null;
    }
}