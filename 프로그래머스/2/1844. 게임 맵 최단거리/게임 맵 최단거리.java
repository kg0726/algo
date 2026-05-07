import java.util.*;

class Solution {
    
    public static int N;
    public static int M;
    public static int[][] arr;
    public static int[][] visited;
    
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    
    public static void bfs() {
        
        // 빈 큐 생성
        Queue<int[]> q = new ArrayDeque<>();
        
        // 시작점 방문 처리 후 큐에 삽입
        visited[0][0] = 1;
        int[] start = {0, 0};
        q.add(start);
        
        // q가 비면 더 이상 갈 곳이 없음
        while(!q.isEmpty()) {
            
            int[] step = q.poll();
            
            int r = step[0];
            int c = step[1];
            
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(0 <= nr && nr < N && 0 <= nc && nc < M) {
                    
                    // 방문 확인 및 방문처리 + 큐 삽입
                    if (arr[nr][nc] == 1 && visited[nr][nc] == 0) {
                        visited[nr][nc] = visited[r][c] + 1;
                        q.add(new int[]{nr, nc});
                    }
                }
            }            
        }
    }
    
    
        
    public int solution(int[][] maps) {
        
        arr = maps;
        
        // 가로 세로 칸 수
        N = maps.length;
        M = maps[0].length;
        
        // 같은 크기의 visited 배열 생성
        visited = new int[N][M];
    
        bfs();
        
        int answer = visited[N - 1][M - 1];
        
        if (answer == 0) {
            answer = -1;
        }
        return answer;
    }
}