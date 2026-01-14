import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Snake {
        // 뱀의 몸이 닿아있는 좌표
        Queue<int[]> snakeQ = new ArrayDeque<>();
        int dir;
        int result;
        // 머리 위치
        int[] head = {0, 0};
        int[] tail = {0, 0};
        public Snake() {
            // 기본 위치
            snakeQ.add(new int[]{0, 0});
            dir = 3;
            result = 0;
        }

        // 방향전환 메서드
        public void switchDir(String direction) {
            if (direction.equals("D")) {
                this.dir = DMap.get(dir);
            } else if (direction.equals("L")) {
                this.dir = LMap.get(dir);
            }
        }

        // 이동 메서드
        public boolean move(boolean[][] visited, int[][] arr) {
            // 머리가 이동할 수 있는지, 이동할 수 있다면 해당 위치에 사과가 있는지 확인
            int r = head[0];
            int c = head[1];

            int nr = r + dr[dir];
            int nc =  c + dc[dir];
            result += 1;
            // 경계 확인
            if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                // 뱀의 몸이 닿는지 확인
                if (visited[nr][nc]) return false;
                snakeQ.add(new int[]{nr, nc});
                head[0] = nr;
                head[1] = nc;
                // 사과가 있는지 확인
                if (arr[nr][nc] == 1) {
                    arr[nr][nc] = 0;
                    visited[nr][nc] = true;
                    return true;
                }
                // 사과가 없다면 큐에서 값을 하나 삭제하고 그 좌표를 미방문 처리
                else {
                    int[] poll = snakeQ.poll();
                    visited[poll[0]][poll[1]] = false;
                    visited[nr][nc] = true;
                    return true;
                }
            }else
                return false;
        }

    }
    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N;
    static int K;
    static int L;
    static Map<Integer, String> dirMap = new HashMap<>();
    static Map<Integer, Integer> DMap = new HashMap<>();
    static Map<Integer, Integer> LMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 사과 표시
            arr[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st2.nextToken());
            String v = st2.nextToken();
            dirMap.put(k, v);
        }
        // 입력 끝
        DMap.put(0, 3);
        DMap.put(1, 2);
        DMap.put(2, 0);
        DMap.put(3, 1);
        LMap.put(0, 2);
        LMap.put(1, 3);
        LMap.put(2, 1);
        LMap.put(3, 0);

        // 뱀 방문 배열
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        // 뱀 객체 생성
        Snake snake = new Snake();
        // 뱀이 더 이상 움직일 수 없을 때 까지
        while (true) {
            // 방향 전환을 해야 하는지 확인
            if (dirMap.containsKey(snake.result)) {
                snake.switchDir(dirMap.get(snake.result));
            }
            if (! snake.move(visited, arr)) {
                break;
            }
        }
        System.out.println(snake.result);
    }
}