import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Cctv {
        int r;
        int c;
        int cctvNum;

        public Cctv(int r, int c, int cctvNum) {
            this.r = r;
            this.c = c;
            this.cctvNum = cctvNum;
        }

        // 감시 메서드
        public List<int[]> watch(int[] dirList) {
            List<int[]> watchArr = new ArrayList<>();
            for (int dir : dirList) {
                // 최대 N 칸까지 갈 수 있음
                for (int i = 1; i < 8; i++) {
                    int nr = r + dr[dir] * i;
                    int nc = c + dc[dir] * i;
                    // 배열의 경계에 닿으면 더 이상 진행 불가능
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        break;
                    }
                    // 벽에 닿으면 더 이상 진행 불가능
                    if (arr[nr][nc] == 6) {
                        break;
                    }
                    // cctv면 그냥 넘어감
                    if (1 <= arr[nr][nc] && arr[nr][nc] <= 5) {
                        continue;
                    }
                    // 이미 감시중이라면 해당 칸에 + 7을 함
                    if (arr[nr][nc] >= 7) {
                        arr[nr][nc] += 7;
                        watchArr.add(new int[]{nr, nc});
                        continue;
                    }
                    // 감시중인 칸으로 만들고 감시중인 칸을 하나 늘력줌
                    arr[nr][nc] = 7;
                    watchArr.add(new int[]{nr, nc});
                    see += 1;
                }
            }
            return watchArr;
        }
    }

    public static void dfs(int depth) {
        // 모든 cctv의 감시 범위를 고려했다면(depth가 cctv의 수와 같아지면 종료)
        if (depth == cctvList.size()) {
            result = Math.max(result, see);
            return;
        }

        // cctvList 순회

        Cctv cctv = cctvList.get(depth);
        // 감시 범위를 순회하면서 감시 진행 및 재귀호출 + 백트래킹
        for (int[] ints : watchMap.get(cctv.cctvNum)) {
            // 감시 전 현재 see 복사
            int copiedResult = see;
            List<int[]> watched = cctv.watch(ints);
            dfs(depth + 1);
            // 백트래킹
            for (int[] ints1 : watched) {
                int r = ints1[0];
                int c = ints1[1];
                arr[r][c] -= 7;
            }
            see = copiedResult;
        }

    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N;
    static int M;
    static int[][] arr;
    static int see;
    static int result = 0;
    static List<Cctv> cctvList = new ArrayList<>();
    static Map<Integer, int[][]> watchMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        int rock = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st2.nextToken());
                if (1 <= arr[i][j] && arr[i][j] <= 5) {
                    cctvList.add(new Cctv(i, j, arr[i][j]));
                } else if (arr[i][j] == 6) {
                    rock += 1;
                }
            }
        } // 입력 끝

        int[][] tmp1 = {{0}, {1}, {2}, {3}};
        int[][] tmp2 = {{0, 1}, {2, 3}};
        int[][] tmp3 = {{0, 3}, {3, 1}, {1, 2}, {2, 0}};
        int[][] tmp4 = {{2, 0, 3}, {0, 3, 1}, {3, 1, 2}, {1, 2, 0}};
        int[][] tmp5 = {{0, 1, 2, 3}};
        watchMap.put(1, tmp1);
        watchMap.put(2, tmp2);
        watchMap.put(3, tmp3);
        watchMap.put(4, tmp4);
        watchMap.put(5, tmp5);

        dfs(0);
        result = N * M - cctvList.size() - rock - result;
        System.out.println(result);
    }
}