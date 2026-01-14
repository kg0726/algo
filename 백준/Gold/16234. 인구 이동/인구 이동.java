import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    // 상하좌우 델타
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int[][] arr;
    static int L;
    static int R;

    public static boolean bfs(int startR, int startC, boolean[][] isOpen) {
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[]{startR, startC});

        int populationSum = arr[startR][startC];
        List<int[]> openList = new ArrayList<>();
        openList.add(new int[]{startR, startC});
        // 큐가 빌 때 까지 실행
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int r = tmp[0];
            int c = tmp[1];
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                    // 방문했거나, 이미 개방된 곳이라면 다음으로
                    if (isOpen[nr][nc]) {
                        continue;
                    }
                    // 인구수의 차이가 L ~ R 사이인지 확인
                    int needValid = Math.abs(arr[r][c] - arr[nr][nc]);
                    if (L <= needValid && needValid <= R) {
                        // 방문, 개방 처리 및 큐에 삽입
                        isOpen[r][c] = true;
                        isOpen[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                        populationSum += arr[nr][nc];
                        openList.add(new int[]{nr, nc});
                    }
                }
            }
        }
        if (openList.size() == 1) {
            return false;
        }
        average(openList, populationSum);
        return true;
    }

    public static void average(List<int[]> openList, int populationSum) {
        int aver = populationSum / openList.size();
        for (int[] ints : openList) {
            arr[ints[0]][ints[1]] = aver;
        }
    }


    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st2.nextToken());
            }
        } // 입력 끝
        int result = 0;
        while (true) {
            boolean flag = false;
            // 이미 개방이 된 곳을 나타낼 배열 초기화
            boolean[][] isOpen = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 개방이 되지 않았다면 bfs 진행
                    if (!isOpen[i][j]) {
                        if (flag == false) {
                            flag = bfs(i, j, isOpen);
                            continue;
                        }
                        bfs(i, j, isOpen);
                    }
                }
            }
            // 모든 배열의 순회가 완료되었음에도 flag가 false이면 더 이상 연결 불가능
            if (flag == false) {
                break;
            }
            result += 1;
        }
        System.out.println(result);
    }
}