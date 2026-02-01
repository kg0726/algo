import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] dr = {-1, 1, 0, 0};
    static int [] dc = {0, 0, -1, 1};
    static int[][] arr;
    static int[][] visited;


    public static boolean canMove(int r, int c) {
        if (0 <= r && r < N && 0 <= c && c < M && arr[r][c] == 1 && visited[r][c] == 0) {
            return true;
        }
        return false;
    }

    static int bfs() {
        // 시작 처리
        visited[0][0] = 1;
        // 빈 큐 생성
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});

        // 큐가 빌 때 까지 실행
        while (!q.isEmpty()) {
            // 큐에서 값을 하나 꺼냄
            int[] node = q.poll();
            int r = node[0];
            int c = node[1];

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (canMove(nr, nc)) {
                    // 방문 처리 후 큐에 삽입
                    visited[nr][nc] = visited[r][c] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        return visited[N - 1][M - 1];
    }

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            char[] charArray = str.toCharArray();
            for (int j = 0; j < M; j++) {
                arr[i][j] = Character.getNumericValue(charArray[j]);
            }
        }
        visited = new int[N][M];
        System.out.println(bfs());
    }
}
