import java.util.*;

// 벽을 피하면서 최단경로를 찾아내는 전형적인 bfs, dfs 문제




class Solution {
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    // bfs로 푸는 경우
    // 방문 배열을 0으로 초기화하여 생성한 후, 0이면 아직 방문하지 않았다고 가정함.
    // 방문 배열은 해당 지점을 방문했는지 여부를 나타냄과 동시에 해당 지점에 도착하기 위해 몇칸을 거쳐서 와야 하는지를 표시
    // 현재 도착하려는 지점이 지금 자리 + 1보다 크다면 숫자를 갱신함
    static int bfs(int n, int m, int[][] visited, int[][] maps) {
        
        Queue<int[]> q = new ArrayDeque<>();
        
        // 큐에 시작 지점 삽입 및 방문 처리
        q.add(new int[]{0, 0});
        visited[0][0] = 1;
        
        // 큐가 빌 때 까지 반복
        while(!q.isEmpty()) {
            // 큐에서 r, c를 꺼냄
            int r = q.peek()[0];
            int c = q.poll()[1];
            
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (canGo(nr, nc, n, m)) {
                    if (maps[nr][nc] == 0) continue;
                    // 해당 위치에 방문한 적 없거나
                    // 해당 위치가 현재 + 1보다 작은지 확인
                    if (visited[nr][nc] > visited[r][c] + 1 || visited[nr][nc] == 0) {
                        // 방문처리 후 큐에 삽입
                        visited[nr][nc] = visited[r][c] + 1;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
        
        return visited[n - 1][m - 1];
    }
    
    // 델타 탐색 시 갈 수 있는 곳인지 확인
    static boolean canGo(int nr, int nc, int n, int m) {
        
        if (0 <= nr && nr < n && 0 <= nc && nc < m) return true;
        return false;
    }
    
    
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        int n = maps.length;
        int m = maps[0].length;
        
        int[][] bfsVisited = new int[n][m];
        
        System.out.println(Arrays.deepToString(bfsVisited));
        
        answer = bfs(n ,m, bfsVisited, maps);      
        
        return answer != 0 ? answer : -1;
    }
}